document.addEventListener("DOMContentLoaded", function () {

    //
    // LOGOUT BUTTON CODE
const logoutBtn = document.getElementById("logoutBtn");

if (logoutBtn) {
    logoutBtn.addEventListener("click", function () {

        // Remove logged user
        localStorage.removeItem("user");

        // Go to login page
        window.location.href = "login.html";
    });
}

//
    let users = JSON.parse(localStorage.getItem("users")) || [];
    let savedDepartments = JSON.parse(localStorage.getItem("customDepartments")) || [];

    const container = document.querySelector(".department-cards");
    container.innerHTML = "";

    let departments = {};

    // Create departments from users
    users.forEach(user => {

        if (!user.department) return;

        if (!departments[user.department]) {
            departments[user.department] = {
                employees: 0,
                manager: "Not Assigned"
            };
        }

        if (user.role === "Employee") {
            departments[user.department].employees++;
        }

        if (user.role === "Manager") {
            departments[user.department].manager = user.fullname;
        }
    });

    // Add manually added departments
    savedDepartments.forEach(dept => {
        if (!departments[dept]) {
            departments[dept] = {
                employees: 0,
                manager: "Not Assigned"
            };
        }
    });

    // Render cards
    for (let dept in departments) {

        let card = document.createElement("div");
        card.className = "department-card";

        card.innerHTML = `
            <h2>${dept}</h2>
            <p><strong>Employees:</strong> ${departments[dept].employees}</p>
            <p><strong>Manager:</strong> ${departments[dept].manager}</p>
        `;

        container.appendChild(card);
    }

    // Add department button
    const addBtn = document.getElementById("addDeptBtn");

    addBtn.addEventListener("click", function () {

        let newDept = prompt("Enter new department name:");

        if (!newDept || newDept.trim() === "") return;

        newDept = newDept.trim();

        if (departments[newDept]) {
            alert("Department already exists!");
            return;
        }

        savedDepartments.push(newDept);
        localStorage.setItem("customDepartments", JSON.stringify(savedDepartments));

        alert("Department added successfully!");
        location.reload();
    });

});
