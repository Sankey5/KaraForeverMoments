const topCard = document.querySelectorAll(".top-card");

function coverCard(overlayElement) {
    overlayElement.classList.add("slide-in");
    overlayElement.classList.remove("slide-out");
}

function revealCards(overlayElement) {
    overlayElement.classList.remove("slide-in");
    overlayElement.classList.add("slide-out");
}

topCard.forEach( card => {
    card.addEventListener('click', (e) => {
        let overlay = e.currentTarget.querySelector(".overlay");
        if (overlay.classList.contains("slide-in")) {
            revealCards(overlay);
        } else {
            coverCard(overlay);
        }
    })
});