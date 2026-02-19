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

        // ✅ Handle non-JSON responses safely
        if (!response.ok) {
            const text = await response.text();
            throw new Error(text || "Invalid email or password");
        }

        const data = await response.json();

        localStorage.setItem("token", data.token);

        const user = {
            employeeId: data.employeeId, // ✅ FIXED
            fullName: data.fullName,
            email: data.email,
            role: data.role
        };

        localStorage.setItem("user", JSON.stringify(user));

        if (data.role === "EMPLOYEE") {
            window.location.href = "employee.html";
        } else if (data.role === "ADMIN") {
            window.location.href = "admin.html";
        } else {
            window.location.href = "manager.html";
        }

    } catch (error) {
        errorMsg.innerText = error.message;
    }
});
