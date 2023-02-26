package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.EmailSenderPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

// @Service
public class EmailServicePersistenceAdapter implements EmailSenderPort {
    @Autowired
    private JavaMailSender mailSender;

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void sendEmail(String to, String message) {

        logger.info("function send email is call on this email ", to);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(to);
            helper.setSubject("Register Account AL");
            helper.setFrom("test@email.com");
            mailSender.send(mimeMessage);

            logger.warn("Sent email to User '{}'", to);

        } catch (MessagingException e) {

            logger.error("Failed to send email for: " + to + "\n" + e);
            throw new IllegalArgumentException("Failed to send email for: " + to);
        }

    }

}
