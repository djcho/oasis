package com.oasis.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String emailAddress, String title, String content) throws MessagingException;
}
