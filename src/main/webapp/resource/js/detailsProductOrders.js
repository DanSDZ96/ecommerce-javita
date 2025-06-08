function toggleDetails(index) {
    const section = document.getElementById("details-" + index);
    const button = section.previousElementSibling.querySelector(".toggle-button");
    const isHidden = section.style.display === "none";

    section.style.display = isHidden ? "flex" : "none";
    button.innerHTML = isHidden ? "&#x25B6;" : "&#x25BC;"; // ▶︎ / ▼
}
