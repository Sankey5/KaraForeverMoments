const topCard = document.querySelectorAll(".top-card");
const serviceOption = document.querySelectorAll(".service-option");
const serviceCategory = document.querySelector("#service-category");
const sessionSize = document.querySelector("#session-size");

function coverCard(overlayElement, imageElement, serviceTitle) {
    overlayElement.classList.add("slide-in");
    overlayElement.classList.remove("slide-out");
    imageElement.classList.add("opaque");
    serviceTitle.classList.add("reveal-title");
}

function revealCards(overlayElement, imageElement, serviceTitle) {
    overlayElement.classList.remove("slide-in");
    overlayElement.classList.add("slide-out");
    imageElement.classList.remove("opaque");
    serviceTitle.classList.remove("reveal-title");
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
            return element.text.split(" ")[0].toLowerCase().includes(sessionLength.split(" ")[0].toLowerCase());
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
    let serviceTitle = topCardElement.querySelector('.service-title');
    if (overlay.classList.contains("slide-in")) {
        revealCards(overlay, img, serviceTitle);
    } else {
        coverCard(overlay, img, serviceTitle);
    }
}

// carousel logic

const carouselElem = document.querySelector("#carousel");
const pictures = carouselElem.querySelectorAll(".slide-container>img");
let currentPictureIndex = 0;
const carouselSize = pictures.length;

function getCurrentPictureIndex() {
    for (i = 0; i < carouselSize; i++) {
        let pic = pictures[i];
        if (pic.classList.contains("active")) {
            return i;
        }
    }
    return -1;
}

function swapPictureClasses(oldPicContainer, newPicContainer) {
    oldPicContainer.classList.remove("active");
    newPicContainer.classList.add("active");
}

function changeSlider() {
    currentPictureIndex = getCurrentPictureIndex();
    if (currentPictureIndex == -1) {
        console.warn("Unable to get current carousel picture");
    }
    let nextPictureIndex = (currentPictureIndex + 1) % carouselSize;
    let oldPic = pictures[currentPictureIndex];
    let newPic = pictures[nextPictureIndex];

    // Fallback for no transition API
    if(!document.startViewTransition) {
        swapPictureClasses(oldPic, newPic);
        return;
    }

    const transition = document.startViewTransition(() => {
        swapPictureClasses(oldPic, newPic);
    });
}

letIntervalFunction = setInterval(changeSlider, 7000);
