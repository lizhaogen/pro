package com.qf.v2.email.service.impl;

import com.lzg.email.api.IEmailService;
import com.qf.v2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    TemplateEngine engine;

    @Value("${mail.from}")
    private String mailFrom;

    @Override
    public void sendMail(String to, String subject, User user) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(mailFrom);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);

            Context context = new Context();
            context.setVariable("username",user.getUsername());
            String text = engine.process("mailtl", context);
            mimeMessageHelper.setText(text,true);
            sender.send(message);

        } catch(Exception e){
            //定时任务  重复发三次邮件，

        }

    }
}
