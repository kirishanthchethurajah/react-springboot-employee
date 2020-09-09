package com.kira.employeespringboot.Kontroller;

import com.kira.employeespringboot.config.EmailConfig;
import com.kira.employeespringboot.model.FeedBack;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    private EmailConfig emailConfig;

    public FeedBackController(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @PostMapping
    public void sendFeedback(@RequestBody FeedBack feedBack, BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedBack.getEmail());
        mailMessage.setTo("b924dcafef-7680ab@inbox.mailtrap.io");
        mailMessage.setSubject("New Feedback from "+ feedBack.getName());
        mailMessage.setText(feedBack.getFeedback());

        mailSender.send(mailMessage);



    }
}
