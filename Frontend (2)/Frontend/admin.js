// ===============================
// 🔐 ADMIN PAGE PROTECTION
// ===============================

const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

// If no token → go to login
if (!token) {
    alert("Please login first.");
    window.location.href = "login.html";
}

// If not ADMIN → block access
if (role !== "ADMIN") {
    alert("Unauthorized access.");
    localStorage.clear();
    window.location.href = "login.html";
}

// ===============================
// 🚀 MAIN LOGIC
// ===============================

document.addEventListener("DOMContentLoaded", () => {

    const BASE_URL = "http://localhost:8080/api/admin";

    // ===============================
    // 🔁 REDIRECT FUNCTION
    // ===============================
    function redirectToLogin(message) {
        alert(message);
        localStorage.clear();
        window.location.href = "login.html";
    }

    // ===============================
    // 🌐 GENERIC API CALL FUNCTION
    // ===============================
    async function apiCall(endpoint, method = "GET", body = null) {

        try {
            const response = await fetch(`${BASE_URL}${endpoint}`, {
                method: method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: body ? JSON.stringify(body) : null
            });

            // If unauthorized → logout
            if (response.status === 401 || response.status === 403) {
                redirectToLogin("Session expired or unauthorized.");
                return null;
            }

            if (!response.ok) {
                throw new Error(`Error ${response.status}`);
            }

            return await response.json();

        } catch (error) {
            console.error("API Error:", error);
            alert("Something went wrong. Check console.");
            return null;
        }
    }

    // ===============================
    // 🔓 LOGOUT
    // ===============================
    const logoutBtn = document.getElementById("logoutBtn");

    if (logoutBtn) {
        logoutBtn.addEventListener("click", () => {
            localStorage.clear();
            window.location.href = "login.html";
        });
    }

    // ===============================
    // 👥 LOAD EMPLOYEES
    // ===============================
    async function loadEmployees() {

        const data = await apiCall("/employees");

        if (!data) return;

        const tableBody = document.getElementById("employeeTableBody");
        if (!tableBody) return;

        tableBody.innerHTML = "";

        data.forEach(emp => {
            const row = `
                <tr>
                    <td>${emp.fullName || "N/A"}</td>
                    <td>${emp.department?.name || "N/A"}</td>
                    <td><span class="active">Active</span></td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });

        // Update dashboard count
        const cards = document.querySelectorAll(".card h2");
        if (cards.length > 0) {
            cards[0].innerText = data.length;
        }
    }

    loadEmployees();

});