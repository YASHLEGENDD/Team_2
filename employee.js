// =========================================
// WAIT UNTIL PAGE LOADS
// =========================================
document.addEventListener("DOMContentLoaded", function () {

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
    // CHECK LOGIN
    // =========================================

    // Get logged in user from localStorage
    let loggedUser = JSON.parse(localStorage.getItem("user"));

    // If no user found → redirect to login
    if (!loggedUser) {
        alert("Please login first!");
        window.location.href = "login.html";
        return;
    }

    // =========================================
    // AUTO FILL PROFILE DETAILS
    // =========================================

    const profileForm = document.getElementById("profileForm");
    const profileInputs = profileForm.querySelectorAll("input");

    // Auto fill Name & Email
    profileInputs[0].value = loggedUser.fullname || "";
    profileInputs[1].value = loggedUser.email || "";
    profileInputs[2].value = loggedUser.post || "";
    profileInputs[3].value = loggedUser.department || "";



    // =========================================
    // UPDATE PROFILE FUNCTION
    // =========================================

    profileForm.addEventListener("submit", function (e) {

        e.preventDefault();

        // Update user object with new values
        loggedUser.fullname = profileInputs[0].value;
        loggedUser.email = profileInputs[1].value;
        loggedUser.post = profileInputs[2].value;
        loggedUser.department = profileInputs[3].value;

        // Save updated user back to localStorage
        localStorage.setItem("user", JSON.stringify(loggedUser));

        alert("Profile Updated Successfully!");
    });



    // =========================================
    // LEAVE REQUEST SYSTEM
    // =========================================

    const leaveForm = document.getElementById("leaveForm");
    const leaveStatus = document.querySelector(".status");

    leaveForm.addEventListener("submit", function (e) {

        e.preventDefault();

        const leaveType = leaveForm.querySelector("select").value;
        const fromDate = leaveForm.querySelectorAll("input")[0].value;
        const toDate = leaveForm.querySelectorAll("input")[1].value;
        const reason = leaveForm.querySelector("textarea").value;

        // Validation check
        if (!fromDate || !toDate || !reason) {
            alert("Please fill all leave details.");
            return;
        }

        // =========================================
        // IMPORTANT: ADD ROLE & DEPARTMENT
        // This is needed for Manager/Admin approval system
        // =========================================

        let leaveRequest = {
            employee: loggedUser.fullname,
            role: loggedUser.role,              // Employee or Manager
            department: loggedUser.department,  // Department info
            type: leaveType,
            from: fromDate,
            to: toDate,
            reason: reason,
            status: "Pending"
        };

        // Get existing leave requests
        let allRequests = JSON.parse(localStorage.getItem("leaveRequests")) || [];

        // Add new leave request
        allRequests.push(leaveRequest);

        // Save back to localStorage
        localStorage.setItem("leaveRequests", JSON.stringify(allRequests));

        // Update UI status
        leaveStatus.innerText = "Pending Approval";
        leaveStatus.className = "status pending";

        alert("Leave request sent successfully!");

        leaveForm.reset();
    });

});
