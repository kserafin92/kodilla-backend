package com.kodilla.backendapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTest {

    private final JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);
    private final EmailService emailService = new EmailService(mailSender);

    @Test
    void testSendEmail() {
        emailService.sendEmail("test@example.com", "Subject", "Email body");
        Mockito.verify(mailSender, Mockito.times(1)).send(Mockito.any(SimpleMailMessage.class));
    }
}
