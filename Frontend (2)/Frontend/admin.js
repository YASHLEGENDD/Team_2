document.addEventListener("DOMContentLoaded", () => {

    const BASE_URL = "http://localhost:8080/api/admin";
    const token = localStorage.getItem("token");

    // AUTH CHECK

    if (!token) {
        redirectToLogin("Session expired. Please login again.");
        return;
    }

    function redirectToLogin(message) {
        alert(message);
        localStorage.clear();
        window.location.href = "login.html";
    }

    // GENERIC API CALL FUNCTION

    async function apiCall(endpoint, method = "GET", body = null) {
        try {
            const response = await fetch(`${BASE_URL}${endpoint}`, {
                method: method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: body ? JSON.stringify(body) : null
            });

            if (response.status === 401 || response.status === 403) {
                redirectToLogin("Unauthorized access.");
                return null;
            }

            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }

            return await response.json();

        } catch (error) {
            console.error("API Error:", error);
            alert("Something went wrong. Check console.");
            return null;
        }
    }

    // LOGOUT

    const logoutBtn = document.getElementById("logoutBtn");
    if (logoutBtn) {
        logoutBtn.addEventListener("click", () => {
            localStorage.clear();
            window.location.href = "login.html";
        });
    }

    // LOAD EMPLOYEES

    async function loadEmployees() {
        const data = await apiCall("/employees");

        if (!data) return;

        const tableBody = document.getElementById("employeeTableBody");
        if (!tableBody) return;

        tableBody.innerHTML = "";

        data.forEach(emp => {
            const row = `
                <tr>
                    <td>${emp.fullName}</td>
                    <td>${emp.department?.name || "N/A"}</td>
                    <td><span class="active">Active</span></td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });

        // Update dashboard card
        const cards = document.querySelectorAll(".card h2");
        if (cards.length > 0) {
            cards[0].innerText = data.length;
        }
    }

    loadEmployees();

    // CREATE DEPARTMENT

    const departmentForm = document.getElementById("departmentForm");
    if (departmentForm) {
        departmentForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const deptName = document.getElementById("departmentName").value;

            const result = await apiCall("/departments", "POST", {
                name: deptName
            });

            if (result) {
                alert("Department created successfully!");
                departmentForm.reset();
            }
        });
    }

    // CREATE EMPLOYEE

    const employeeForm = document.getElementById("employeeForm");
    if (employeeForm) {
        employeeForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const fullName = document.getElementById("empName").value;
            const email = document.getElementById("empEmail").value;
            const departmentId = document.getElementById("empDepartment").value;

            const result = await apiCall("/employees", "POST", {
                fullName,
                email,
                department: {
                    id: departmentId
                }
            });

            if (result) {
                alert("Employee created successfully!");
                employeeForm.reset();
                loadEmployees();
            }
        });
    }

    // CREATE LEAVE TYPE

    const leaveTypeForm = document.getElementById("leaveTypeForm");
    if (leaveTypeForm) {
        leaveTypeForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const leaveName = document.getElementById("leaveName").value;
            const leaveDays = document.getElementById("leaveDays").value;

            const result = await apiCall("/leave-types", "POST", {
                name: leaveName,
                totalDays: leaveDays
            });

            if (result) {
                alert("Leave type created successfully!");
                leaveTypeForm.reset();
            }
        });
    }

});
