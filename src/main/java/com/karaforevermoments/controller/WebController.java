package com.karaforevermoments.controller;

import com.karaforevermoments.data.Picture;
import com.karaforevermoments.data.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private EmailService emailService;
    private final List<Picture> carouselPictures;
    private final List<Service> servicePictures;
    int i = 0;

    public WebController() {
        this.carouselPictures = new LinkedList<>();
        this.carouselPictures.add(new Picture("/images/slider/denver-couple.jpg", "A couple soon to kiss in front of Union Station in Denver"));
        this.carouselPictures.add(new Picture("/images/slider/maternity.jpg", "A husband holding the belly of his pregnant wife near Red Rocks, Colorado"));
        this.carouselPictures.add(new Picture("/images/slider/denver-model.jpg", "A lady smiling with a large brown coat on, a white turtle neck, and black gloves"));
        this.carouselPictures.add(new Picture("/images/slider/maternity-2.jpg", "A husband and wife touching foreheads, while the husband holds the belly of his pregnant wife"));

        this.servicePictures = new LinkedList<>();
        this.servicePictures.add(new Service("Model", new Picture("/images/ModelService.jpg", "A model in a white dress, about to pull back her hair")));
        this.servicePictures.add(new Service("Couples", new Picture("/images/CouplesService.jpg", "A couple back to back smiling at the camera with the sun setting behind them")));
        this.servicePictures.add(new Service("Maternity", new Picture("/images/MaternityService.jpg", "An expecting mother holding a tree in front of the red rocks of Colorado")));
        this.servicePictures.add(new Service("Family", new Picture("/images/FamilyService.jpg", "A family of four, the parents standing behind their two boys.")));
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("pictures", this.carouselPictures);
        model.addAttribute("services", this.servicePictures);
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
