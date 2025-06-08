document.addEventListener("DOMContentLoaded", function () {
    const toggleCheckbox = document.getElementById('toggleThemeCheckbox');
    const body = document.body;

    // Imposta lo stato iniziale
    const currentTheme = localStorage.getItem('theme');
    if (currentTheme === 'dark') {
        body.classList.add('dark-mode');
        toggleCheckbox.checked = true;
    }

    toggleCheckbox.addEventListener('change', () => {
        if (toggleCheckbox.checked) {
            body.classList.add('dark-mode');
            localStorage.setItem('theme', 'dark');
        } else {
            body.classList.remove('dark-mode');
            localStorage.setItem('theme', 'light');
        }
    });
});