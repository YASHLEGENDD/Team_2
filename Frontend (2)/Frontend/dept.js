document.addEventListener("DOMContentLoaded", function () {

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Unauthorized! Please login again.");
        window.location.href = "login.html";
        return;
    }

    // LOGOUT
    const logoutBtn = document.getElementById("logoutBtn");

    if (logoutBtn) {
        logoutBtn.addEventListener("click", function () {
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            window.location.href = "login.html";
        });
    }

    const container = document.querySelector(".department-cards");

    // LOAD DEPARTMENTS + EMPLOYEES

    function loadDepartments() {

        Promise.all([
            fetch("http://localhost:8080/api/admin/departments", {
                headers: {
                    "Authorization": "Bearer " + token
                }
            }),
            fetch("http://localhost:8080/api/admin/employees", {
                headers: {
                    "Authorization": "Bearer " + token
                }
            })
        ])
        .then(async ([deptRes, empRes]) => {

            if (!deptRes.ok || !empRes.ok) {
                throw new Error("Failed to fetch data");
            }

            const departments = await deptRes.json();
            const employees = await empRes.json();

            renderDepartments(departments, employees);
        })
        .catch(error => {
            console.error(error);
            alert("Error loading departments");
        });
    }

    function renderDepartments(departments, employees) {

        container.innerHTML = "";

        departments.forEach(dept => {

            const deptEmployees = employees.filter(emp =>
                emp.department && emp.department.id === dept.id
            );

            const manager = deptEmployees.find(emp =>
                emp.role === "MANAGER"
            );

            const employeeCount = deptEmployees.filter(emp =>
                emp.role === "EMPLOYEE"
            ).length;

            let card = document.createElement("div");
            card.className = "department-card";

            card.innerHTML = `
                <h2>${dept.name}</h2>
                <p><strong>Employees:</strong> ${employeeCount}</p>
                <p><strong>Manager:</strong> ${manager ? manager.fullName : "Not Assigned"}</p>
            `;

            container.appendChild(card);
        });
    }

    loadDepartments();

    // ADD DEPARTMENT

    const addBtn = document.getElementById("addDeptBtn");

    if (addBtn) {
        addBtn.addEventListener("click", function () {

            let newDept = prompt("Enter new department name:");

            if (!newDept || newDept.trim() === "") return;

            fetch("http://localhost:8080/api/admin/departments", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                },
                body: JSON.stringify({
                    name: newDept.trim()
                })
            })
            .then(response => response.json())
            .then(data => {
                alert("Department added successfully!");
                loadDepartments();
            })
            .catch(error => {
                console.error(error);
                alert("Error creating department");
            });
        });
    }

});
