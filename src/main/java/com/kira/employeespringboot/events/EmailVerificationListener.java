/*
package com.kira.employeespringboot.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

@Service
@RequiredArgsConstructor
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final JavaMailSender mailSender;
    private final VerificationService verificationService;
    @Override
    public void onApplicationEvent(UserRegistrationEvent userRegistrationEvent) {
        String userName = userRegistrationEvent.getUser().getUserName();
        String verificationId= verificationService.createVerification(userName);
        String email = userRegistrationEvent.getUser().getEmail();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("Account verification");
        mailMessage.setSubject("Click enter");
        mailMessage.setTo();
        mailSender.send(mailMessage);

    }
}
*/
