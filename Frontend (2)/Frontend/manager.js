document.addEventListener("DOMContentLoaded", function () {

    const tableBody = document.getElementById("leaveTableBody");
    const token = localStorage.getItem("token");

    const BASE_URL = "http://localhost:8080/api/leave";

    // 🔐 If no token → redirect
    if (!token) {
        alert("Session expired. Please login again.");
        window.location.href = "login.html";
        return;
    }

    // ================= LOGOUT =================
    document.getElementById("logoutBtn")?.addEventListener("click", function () {
        localStorage.clear();
        window.location.href = "login.html";
    });

    // ================= LOAD PENDING =================
    function loadPendingLeaves() {

        fetch(`${BASE_URL}/manager/pending`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Unauthorized or no data");
            }
            return response.json();
        })
        .then(data => {
            renderTable(data);
        })
        .catch(error => {
            console.error("Error loading leaves:", error);
            alert("Failed to load leave requests");
        });
    }

    function renderTable(leaves) {

        tableBody.innerHTML = "";

        if (!leaves || leaves.length === 0) {
            tableBody.innerHTML = "<tr><td colspan='5'>No pending requests</td></tr>";
            return;
        }

        leaves.forEach(leave => {

            let row = `
                <tr>
                    <td>${leave.employee.user.fullName}</td>
                    <td>${leave.leaveType.leaveName}</td>
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

    // ================= APPROVE =================
    window.approve = function (leaveId) {

        fetch(`${BASE_URL}/${leaveId}/approve`, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + token
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Approve failed");
            }
            return response.json();
        })
        .then(() => {
            alert("Leave Approved!");
            loadPendingLeaves();
        })
        .catch(err => {
            console.error(err);
            alert("Approval failed");
        });
    };

    // ================= REJECT =================
    window.reject = function (leaveId) {

        fetch(`${BASE_URL}/${leaveId}/reject`, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + token
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Reject failed");
            }
            return response.json();
        })
        .then(() => {
            alert("Leave Rejected!");
            loadPendingLeaves();
        })
        .catch(err => {
            console.error(err);
            alert("Rejection failed");
        });
    };

    loadPendingLeaves();
});