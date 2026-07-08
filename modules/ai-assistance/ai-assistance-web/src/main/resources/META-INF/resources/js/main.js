document.addEventListener("DOMContentLoaded", function () {
    const chips = document.querySelectorAll(".suggestion-chip");
    const input = document.querySelector(".chat-input");

    chips.forEach(function (chip) {
        chip.addEventListener("click", function () {
            if (input) {
                input.value = chip.textContent.trim();
                input.focus();
            }
        });
    });
});