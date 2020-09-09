package com.kira.employeespringboot.Kontroller;

import com.kira.employeespringboot.dto.MailRequest;
import com.kira.employeespringboot.dto.MailResponse;
import com.kira.employeespringboot.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody  MailRequest request){
        Map<String,Object> model = new HashMap<>();
        model.put("Name",request.getName());
        model.put("location","Dortmund");
        return emailService.sendEmail(request,model);
    }


}
