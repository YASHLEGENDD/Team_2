document.getElementById("loginForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const email = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const errorMsg = document.getElementById("errorMsg");

    errorMsg.innerText = "";

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            const text = await response.text();
            throw new Error(text || "Invalid email or password");
        }

        const data = await response.json();

        // Save token
        localStorage.setItem("token", data.token);

        // Save role separately (IMPORTANT FIX)
        const cleanRole = data.role.replace("ROLE_", "");
        localStorage.setItem("role", cleanRole);

        // Save user object
        const user = {
            employeeId: data.employeeId,
            fullName: data.fullName,
            email: data.email,
            role: cleanRole
        };

        localStorage.setItem("user", JSON.stringify(user));

        // Redirect based on role
        if (cleanRole === "EMPLOYEE") {
            window.location.href = "employee.html";
        } else if (cleanRole === "ADMIN") {
            window.location.href = "admin.html";
        } else {
            window.location.href = "manager.html";
        }

    } catch (error) {
        errorMsg.innerText = error.message;
    }
});