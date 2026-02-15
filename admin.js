// =========================================
// WAIT UNTIL PAGE LOADS
// =========================================
document.addEventListener("DOMContentLoaded", function () {

    //logout
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


    // =========================================
    // GET DATA FROM LOCAL STORAGE
    // =========================================

    let users = JSON.parse(localStorage.getItem("users")) || [];
    let requests = JSON.parse(localStorage.getItem("leaveRequests")) || [];

    let today = new Date();

    let onLeaveCount = 0;
    let pendingCount = 0;

    // =========================================
    // COUNT LEAVE STATUS (FOR DASHBOARD CARDS)
    // =========================================

    requests.forEach(req => {

        // Count pending requests (only employee ones)
        if (req.status === "Pending" && req.role === "Employee") {
            pendingCount++;
        }

        // Count employees currently on leave
        if (req.status === "Approved" && req.role === "Employee") {

            let from = new Date(req.from);
            let to = new Date(req.to);

            if (today >= from && today <= to) {
                onLeaveCount++;
            }
        }
    });

    // =========================================
    // COUNT TOTAL EMPLOYEES ONLY
    // =========================================

    let employeeUsers = users.filter(user => user.role === "Employee");
    let employeeCount = employeeUsers.length;

    // =========================================
    // UPDATE DASHBOARD CARDS
    // =========================================

    const cards = document.querySelectorAll(".card h2");

    if (cards.length >= 3) {
        cards[0].innerText = employeeCount;  // Total Employees
        cards[1].innerText = onLeaveCount;   // On Leave
        cards[2].innerText = pendingCount;   // Pending Requests
    }

    // =========================================
    // DYNAMIC EMPLOYEE TABLE
    // =========================================

    const tableBody = document.getElementById("employeeTableBody");

    if (tableBody) {

        tableBody.innerHTML = "";

        employeeUsers.forEach(user => {

            let status = "Active";
            let statusClass = "active";

            // Check if employee is currently on leave
            requests.forEach(req => {

                if (
                    req.employee === user.fullname &&
                    req.status === "Approved" &&
                    req.role === "Employee"
                ) {
                    let from = new Date(req.from);
                    let to = new Date(req.to);

                    if (today >= from && today <= to) {
                        status = "On Leave";
                        statusClass = "leave";
                    }
                }
            });

            let row = `
                <tr>
                    <td>${user.fullname}</td>
                    <td>${user.department || "N/A"}</td>
                    <td><span class="${statusClass}">${status}</span></td>
                </tr>
            `;

            tableBody.innerHTML += row;
        });
    }

    // =========================================
    // MANAGER LEAVE REQUEST SECTION
    // =========================================

    const managerLeaveBody = document.getElementById("managerLeaveBody");

    if (managerLeaveBody) {

        // Filter only manager pending requests
        let managerRequests = requests.filter(req =>
            req.role === "Manager" && req.status === "Pending"
        );

        managerLeaveBody.innerHTML = "";

        managerRequests.forEach((req, index) => {

            let row = `
                <tr>
                    <td>${req.employee}</td>
                    <td>${req.department || "N/A"}</td>
                    <td>${req.from}</td>
                    <td>${req.to}</td>
                    <td>
                        <button onclick="approveManager(${index})">Approve</button>
                        <button onclick="rejectManager(${index})">Reject</button>
                    </td>
                </tr>
            `;

            managerLeaveBody.innerHTML += row;
        });

        // Approve Manager Leave
        window.approveManager = function(index) {

            let managerRequests = requests.filter(req =>
                req.role === "Manager" && req.status === "Pending"
            );

            let selectedRequest = managerRequests[index];

            let realIndex = requests.findIndex(r =>
                r.employee === selectedRequest.employee &&
                r.from === selectedRequest.from &&
                r.role === "Manager"
            );

            if (realIndex !== -1) {
                requests[realIndex].status = "Approved";
                localStorage.setItem("leaveRequests", JSON.stringify(requests));
                alert("Manager leave approved!");
                location.reload();
            }
        };

        // Reject Manager Leave
        window.rejectManager = function(index) {

            let managerRequests = requests.filter(req =>
                req.role === "Manager" && req.status === "Pending"
            );

            let selectedRequest = managerRequests[index];

            let realIndex = requests.findIndex(r =>
                r.employee === selectedRequest.employee &&
                r.from === selectedRequest.from &&
                r.role === "Manager"
            );

            if (realIndex !== -1) {
                requests[realIndex].status = "Rejected";
                localStorage.setItem("leaveRequests", JSON.stringify(requests));
                alert("Manager leave rejected!");
                location.reload();
            }
        };
    }

    // =========================================
    // DEPARTMENT BUTTON REDIRECT
    // =========================================

    const departmentBtn = document.getElementById("departmentBtn");

    if (departmentBtn) {
        departmentBtn.addEventListener("click", function () {
            window.location.href = "dept.html";
        });
    }

});
