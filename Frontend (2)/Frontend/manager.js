document.addEventListener("DOMContentLoaded", function () {

    const tableBody = document.getElementById("leaveTableBody");
    const BASE_URL = "http://localhost:8080/api/manager";

    // Logout
    const logoutBtn = document.getElementById("logoutBtn");
    if (logoutBtn) {
        logoutBtn.addEventListener("click", function () {
            localStorage.removeItem("user");
            window.location.href = "login.html";
        });
    }

    // Load pending leaves from backend
    function loadPendingLeaves() {
        fetch(`${BASE_URL}/pending`)
            .then(response => response.json())
            .then(data => {
                renderTable(data);
            })
            .catch(error => console.error("Error:", error));
    }

    function renderTable(leaves) {
        tableBody.innerHTML = "";

        leaves.forEach(leave => {

            let row = `
                <tr>
                    <td>${leave.employee.firstName}</td>
                    <td>${leave.leaveType.leaveTypeName}</td>
                    <td>${leave.startDate}</td>
                    <td>${leave.endDate}</td>
                    <td>
                        <button onclick="approve(${leave.leaveRequestId})">Approve</button>
                        <button onclick="reject(${leave.leaveRequestId})">Reject</button>
                    </td>
                </tr>
            `;

            tableBody.innerHTML += row;
        });
    }

    window.approve = function (leaveId) {
        fetch(`${BASE_URL}/approve/${leaveId}`, {
            method: "PUT"
        })
        .then(response => response.json())
        .then(() => {
            alert("Leave Approved!");
            loadPendingLeaves();
        });
    };

    window.reject = function (leaveId) {
        fetch(`${BASE_URL}/reject/${leaveId}`, {
            method: "PUT"
        })
        .then(response => response.json())
        .then(() => {
            alert("Leave Rejected!");
            loadPendingLeaves();
        });
    };

    loadPendingLeaves();
});
