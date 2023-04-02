package com.oasis.service.impl;

import com.oasis.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String emailAddress, String title, String content) throws MessagingException {
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(String.valueOf(javaMailSender.getUsername()));
        helper.setTo(emailAddress);
        helper.setSubject(title);
        helper.setText(content, true);

        javaMailSender.send(message);
    }
}
