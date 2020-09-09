package com.kira.employeespringboot.service;

import com.kira.employeespringboot.dto.MailRequest;
import com.kira.employeespringboot.dto.MailResponse;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
    private JavaMailSender sender;
    private Configuration configuration;

    public EmailService(JavaMailSender sender, Configuration configuration) {
        this.sender = sender;
        this.configuration = configuration;
    }

    public MailResponse sendEmail(MailRequest request, Map<String, Object> model){
        MailResponse response = new MailResponse();
        MimeMessage message= sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template t = configuration.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);

            helper.setTo(request.getTo());
            helper.setText(html,true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);
            response.setMessage("mail Send to"+request.getTo());
            response.setStatus(Boolean.TRUE);
        } catch (MessagingException |IOException | TemplateException e) {
            response.setMessage("mail Sending failed "+e.getMessage());
            response.setStatus(Boolean.FALSE);
            e.printStackTrace();
        }
    return response;
    }


}
