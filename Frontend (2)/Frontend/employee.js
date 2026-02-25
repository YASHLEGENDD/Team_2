document.addEventListener("DOMContentLoaded", function () {

    const token = localStorage.getItem("token");

    if (!token) {
        window.location.href = "login.html";
        return;
    }

    // ================= LOGOUT =================
    document.getElementById("logoutBtn")?.addEventListener("click", () => {
        localStorage.clear();
        window.location.href = "login.html";
    });

    // ================= LOAD MY LEAVES =================
    function loadMyLeaves() {

        fetch("http://localhost:8080/api/employee/my-leaves", {
            headers: {
                "Authorization": "Bearer " + token
            }
        })
        .then(res => {
            if (!res.ok) throw new Error("Failed to fetch leaves");
            return res.json();
        })
        .then(data => {

            const statusEl = document.querySelector(".status");

            if (!statusEl) return;

            if (data.length > 0) {
                const last = data[data.length - 1];

                statusEl.innerText = last.status;
                statusEl.className = "status " + last.status.toLowerCase();
            } else {
                statusEl.innerText = "No Leave Requests";
                statusEl.className = "status pending";
            }
        })
        .catch(err => {
            console.error("Error loading leaves:", err);
        });
    }

    loadMyLeaves();

    // ================= APPLY LEAVE =================
    document.getElementById("leaveForm")?.addEventListener("submit", function (e) {

        e.preventDefault();

        const leaveTypeId = Number(
            document.getElementById("leaveTypeSelect").value
        );

      const startDate = document.getElementById("startDate").value;
const endDate = document.getElementById("endDate").value;
const reason = document.getElementById("reason").value;

        fetch("http://localhost:8080/api/employee/apply-leave", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify({
                leaveType: { leaveTypeId: leaveTypeId },
                startDate: startDate,
                endDate: endDate,
                reason: reason
            })
        })
        .then(res => {
            if (!res.ok) throw new Error("Apply failed");
            return res.json();
        })
        .then(() => {
            alert("Leave applied successfully!");
            this.reset();
            loadMyLeaves();
        })
        .catch(err => {
            console.error("Leave apply error:", err);
            alert("Leave request failed");
        });
    });

});