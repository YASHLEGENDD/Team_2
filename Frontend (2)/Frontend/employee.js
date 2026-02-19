document.addEventListener("DOMContentLoaded", function () {

    const token = localStorage.getItem("token");
    const user = JSON.parse(localStorage.getItem("user"));

    if (!token || !user) {
        window.location.href = "login.html";
        return;
    }

    document.getElementById("logoutBtn")?.addEventListener("click", () => {
        localStorage.clear();
        window.location.href = "login.html";
    });

    function loadMyLeaves() {

        fetch(`http://localhost:8080/api/employee/leaves/${user.employeeId}`, {
            headers: { "Authorization": "Bearer " + token }
        })
        .then(res => res.json())
        .then(data => {

            const statusEl = document.querySelector(".status");

            if (data.length > 0) {
                const last = data[data.length - 1];
                statusEl.innerText = last.status;
                statusEl.className =
                        "status " + last.status.toLowerCase();
            }
        });
    }

    loadMyLeaves();

    document.getElementById("leaveForm")?.addEventListener("submit", function (e) {

        e.preventDefault();

        const leaveTypeId =
                this.querySelector("select").value;

        const startDate =
                this.querySelectorAll("input")[0].value;

        const endDate =
                this.querySelectorAll("input")[1].value;

        const reason =
                this.querySelector("textarea").value;

        fetch("http://localhost:8080/api/employee/apply-leave", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify({
                employee: { employeeId: user.employeeId },
                leaveType: { leaveTypeId: leaveTypeId },
                startDate,
                endDate,
                reason
            })
        })
        .then(res => {
            if (!res.ok) throw new Error();
            alert("Leave applied successfully!");
            this.reset();
            loadMyLeaves();
        })
        .catch(() => alert("Leave request failed"));
    });
});
