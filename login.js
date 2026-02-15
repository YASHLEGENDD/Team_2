document.getElementById("loginForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const email = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const errorMsg = document.getElementById("errorMsg");

    // Clear previous error
    errorMsg.innerText = "";

    // Get stored user
    const storedUser = JSON.parse(localStorage.getItem("user"));

    // Check if user exists
    if (!storedUser) {
        errorMsg.style.color = "red";
        errorMsg.innerText = "No user found. Please register first.";
        return;
    }

    // Validate login
    if (email === storedUser.email && password === storedUser.password) {
        errorMsg.style.color = "lightgreen";
        errorMsg.innerText = "Login Successful! Redirecting...";

        setTimeout(() => {

        // Check role and redirect accordingly
        if (storedUser.role === "Employee") {
            window.location.href = "employee.html";
        } 
        else if (storedUser.role === "Manager") {
            window.location.href = "manager.html";
        } 
        else if (storedUser.role === "Admin") {
            window.location.href = "admin.html";
        }

    }, 1000);

    } else {
        errorMsg.style.color = "red";
        errorMsg.innerText = "Invalid Email or Password";
    }
});
