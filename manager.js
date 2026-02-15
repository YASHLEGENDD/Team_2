
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


    const tableBody = document.getElementById("leaveTableBody");

    let requests = JSON.parse(localStorage.getItem("leaveRequests")) || [];

    function renderTable() {
        tableBody.innerHTML = "";

        requests.forEach((request, index) => {

            if (request.status === "Pending") {

                let row = `
                <tr>
                    <td>${request.employee}</td>
                    <td>${request.type}</td>
                    <td>${request.from}</td>
                    <td>${request.to}</td>
                    <td>
                        <button onclick="approve(${index})">Approve</button>
                        <button onclick="reject(${index})">Reject</button>
                    </td>
                </tr>
                `;

                tableBody.innerHTML += row;
            }
        });
    }

    window.approve = function (index) {
        requests[index].status = "Approved";
        localStorage.setItem("leaveRequests", JSON.stringify(requests));
        alert("Leave Approved!");
        renderTable();
    };

    window.reject = function (index) {
        requests[index].status = "Rejected";
        localStorage.setItem("leaveRequests", JSON.stringify(requests));
        alert("Leave Rejected!");
        renderTable();
    };

    renderTable();

});
