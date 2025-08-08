const topCard = document.querySelectorAll(".top-card");
const serviceOption = document.querySelectorAll(".service-option");
const serviceCategory = document.querySelector("#service-category");
const sessionSize = document.querySelector("#session-size");

function coverCard(overlayElement, imageElement) {
    overlayElement.classList.add("slide-in");
    overlayElement.classList.remove("slide-out");
    imageElement.classList.add("opaque")
}

function revealCards(overlayElement, imageElement) {
    overlayElement.classList.remove("slide-in");
    overlayElement.classList.add("slide-out");
    imageElement.classList.remove("opaque")
}

topCard.forEach( card => {
    card.addEventListener('click', (e) => {
        toggleTopCard(e.currentTarget);
    })
});

serviceOption.forEach((option) => {
    option.addEventListener('click', (e) => {
        e.stopImmediatePropagation();
        let currTopCard = e.currentTarget.closest(".top-card");
        let service = currTopCard.id.split("-")[0]
        let selectedCategoryOption = Array.from(serviceCategory.options).filter(element => {
            return element.text.toLowerCase().includes(service.toLowerCase());
        })[0];
        serviceCategory.value = selectedCategoryOption.text;

        let sessionLength = e.currentTarget.querySelector("h1").textContent;
        let selectedSessionLengthOption = Array.from(sessionSize.options).filter(element => {
            return element.text.split(" ")[0].toLowerCase().includes(sessionLength.toLowerCase());
        })[0];
        sessionSize.value = selectedSessionLengthOption.text;

        const contactMe = document.getElementById("contact-me");
        contactMe.scrollIntoView({
            behavior: "smooth",
            block: "start",
            inline: "start"
        });

        let topCard = e.currentTarget.closest(".top-card");
        toggleTopCard(topCard);
    })
});

function toggleTopCard(topCardElement) {
    let overlay = topCardElement.querySelector(".overlay");
    let img = topCardElement.querySelector("img");
    if (overlay.classList.contains("slide-in")) {
        revealCards(overlay, img);
    } else {
        coverCard(overlay, img);
    }
}