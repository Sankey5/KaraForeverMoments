package com.karaforevermoments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
@ComponentScan("com.karaforevermoments")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        String mailHostname = System.getenv("EMAIL_HOST_NAME");
        String mailPort = System.getenv("EMAIL_PORT");
        String username = System.getenv("EMAIL_USERNAME");
        String password = System.getenv("EMAIL_PASSWORD");

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.debug", "true");
        JavaMailSenderImpl simpleMailSender = new JavaMailSenderImpl();
        simpleMailSender.setHost(mailHostname);
        simpleMailSender.setPort(Integer.parseInt(mailPort));
        simpleMailSender.setUsername(username);
        simpleMailSender.setPassword(password);
        simpleMailSender.setJavaMailProperties(props);
        return simpleMailSender;
    }
}
