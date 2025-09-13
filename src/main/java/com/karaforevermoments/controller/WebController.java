package com.karaforevermoments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    EmailService emailService;
    int i = 0;

    @GetMapping
    public String getHomePage(Model model) {
        return "index";
    }

    @PostMapping("email")
    public String sendEmail(@RequestParam("service-category") String service,
                            @RequestParam("session-size") String size,
                            @RequestParam("first-name") String name,
                            @RequestParam("email") String email, Model model) {
        try {
            if (i % 2 == 0) {
                throw new MailAuthenticationException("test");
            }
            //this.emailService.sendEmail(service, size, name, email);
            model.addAttribute("failed", false);
        } catch (MailException e) {
            System.out.println("Mail exception: " + e.getMessage());
            model.addAttribute("failed", true);
        }
        i++;
        return "email-response";
    }
}
