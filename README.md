# Overview

The project uses HTML 5, Tailwind CSS 4, Vanilla javascript, and Java SpringBoot 3.5.4 to serve a photography website. 

The HTML and CSS are to provide structure and appeal to the website while the Javascript provides the functionality for the user
to move around the site more easily and provide email functionality. Finally, SpringBoot offers a backend to send emails
to the business owner and answer inquiries.

# Installation 

Installation required the CLI installation of Tailwind CSS. A guide was followed to integrate the CLI installation into 
Thymeleaf and ultimately SpringBoot. The link to the guide is: https://wanderlytics.me/posts/3/how-to-add-tailwind-css-in-spring-boot-application.
This instructs us to install npm in order to install tailwind. Then it instructs us to implement tailwind into our Thymeleaf
index page.

# Features

This site has two main features: 

1. Service Selection
2. Contact Form Emailing

## Service Selection

Selecting a service option automatically selects options in the Contact Me section and 
automatically scrolls to the contact me section, serving as a means to drive traffic to reach out about KaraForeverMoments services.
The 

## Contact Form Emailing

The contact form sends the data to SpringBoot, which transforms the contact form data into an email which is sent to the
business owner. This is a far superior option to mailto: directives as it removes friction for the user who doesn't have a
local mail client installed. This allows for increased traffic from interested customers who would be dissuaded from contacting
due to technical limitations.