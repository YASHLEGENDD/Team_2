document.addEventListener("DOMContentLoaded", function () {

    const home = document.getElementById("homeLink");
    const login = document.getElementById("loginLink");
    const register = document.getElementById("registerLink");
    const moreInfo = document.getElementById("moreInfoLink");
    const getStarted = document.getElementById("getStartedBtn");

    if (home) {
        home.onclick = function () {
            window.location.href = "home.html";
        };
    }

    if (login) {
        login.onclick = function () {
            window.location.href = "login.html";
        };
    }

    if (register) {
        register.onclick = function () {
            window.location.href = "register.html";
        };
    }

    if (moreInfo) {
        moreInfo.onclick = function () {
            window.location.href = "info.html";
        };
    }

    if (getStarted) {
        getStarted.onclick = function () {
            window.location.href = "register.html";
        };
    }

});
