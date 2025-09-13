package com.karaforevermoments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private final SimpleMailMessage templateMessage;
    private final String recipientEmail;

    public EmailService() {
        String mailSenderName = System.getenv("EMAIL_SENDER_NAME");
        //this.mailSender = this.getJavaMailSender();
        this.recipientEmail = System.getenv("EMAIL_RECIPIENT");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailSenderName);
        msg.setSubject("New Customer Message!");
        this.templateMessage = msg;
    }

    public void sendEmail(String service, String size, String name, String email) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(this.recipientEmail);
        msg.setText("New customer!\nName: " + name
                + "\nEmail: " + email
                + "\nService: " + service
                + "\nShoot size: " + size);
        this.mailSender.send(msg);
    }
}
