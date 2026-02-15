// Wait until the HTML page is fully loaded
document.addEventListener("DOMContentLoaded", function () {

    //logout
    

    // Get the registration form
    const registerForm = document.getElementById("registerForm");

    // Add submit event listener
    registerForm.addEventListener("submit", function (e) {

        // Prevent page refresh
        e.preventDefault();

        // ==============================
        // GET INPUT VALUES
        // ==============================

        let fullname = document.getElementById("fullname").value.trim();
        let email = document.getElementById("email").value.trim();
        let department = document.getElementById("department").value; // dropdown value
        let password = document.getElementById("password").value.trim();
        let confirmPassword = document.getElementById("confirmPassword").value.trim();

        // Get selected role (Admin / Manager / Employee)
        let selectedRole = document.querySelector('input[name="section"]:checked');

        // Get error/message elements
        let roleError = document.getElementById("sectionError");
        let message = document.getElementById("message");

        // ==============================
        // VALIDATIONS
        // ==============================

        // Check if role selected
        if (!selectedRole) {
            roleError.innerText = "Please select a role.";
            return;
        }

        // Clear previous errors
        roleError.innerText = "";
        message.innerText = "";

        // Check password match
        if (password !== confirmPassword) {
            message.style.color = "red";
            message.innerText = "Passwords do not match!";
            return;
        }

        // ==============================
        // CREATE USER OBJECT
        // ==============================

        let user = {
            fullname: fullname,
            email: email,
            password: password,
            role: selectedRole.value,
            department: department
        };

        // ==============================
        // STORE MULTIPLE USERS
        // ==============================

        // Get existing users array OR create empty array
        let users = JSON.parse(localStorage.getItem("users")) || [];

        // Check if email already exists
        let emailExists = users.some(u => u.email === email);

        if (emailExists) {
            message.style.color = "red";
            message.innerText = "Email already registered!";
            return;
        }

        // Add new user to array
        users.push(user);

        // Save updated users array
        localStorage.setItem("users", JSON.stringify(users));

        // Save current user session separately
        localStorage.setItem("user", JSON.stringify(user));

        // ==============================
        // SUCCESS MESSAGE
        // ==============================

        message.style.color = "green";
        message.innerText = "Registration Successful! Redirecting to Login...";

        // Redirect after 1 second
        setTimeout(() => {
            window.location.href = "login.html";
        }, 1000);

    });

});
