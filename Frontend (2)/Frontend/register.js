// Wait until HTML page is fully loaded
document.addEventListener("DOMContentLoaded", function () {

    const registerForm = document.getElementById("registerForm");

    registerForm.addEventListener("submit", function (e) {

        e.preventDefault();

        // GET INPUT VALUES

        let fullName = document.getElementById("fullname").value.trim();
        let email = document.getElementById("email").value.trim();
        let password = document.getElementById("password").value.trim();
        let confirmPassword = document.getElementById("confirmPassword").value.trim();

        let selectedRole = document.querySelector('input[name="section"]:checked');

        let roleError = document.getElementById("sectionError");
        let message = document.getElementById("message");

        // VALIDATIONS

        if (!selectedRole) {
            roleError.innerText = "Please select a role.";
            return;
        }

        roleError.innerText = "";
        message.innerText = "";

        if (password !== confirmPassword) {
            message.style.color = "red";
            message.innerText = "Passwords do not match!";
            return;
        }

        // CREATE REQUEST OBJECT

        let user = {
            fullName: fullName,      // must match DTO
            email: email,
            password: password,
            role: selectedRole.value
        };

        // SEND DATA TO SPRING BOOT

        fetch("http://localhost:8080/api/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text); });
            }
            return response.text();
        })
        .then(data => {
            message.style.color = "green";
            message.innerText = "Registration Successful! Redirecting...";

            setTimeout(() => {
                window.location.href = "login.html";
            }, 1000);
        })
        .catch(error => {
            message.style.color = "red";
            message.innerText = "Error: " + error.message;
        });

    });

});
