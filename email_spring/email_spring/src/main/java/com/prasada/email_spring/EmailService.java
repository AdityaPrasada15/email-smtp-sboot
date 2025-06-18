package com.prasada.email_spring;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService (JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @PostConstruct
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("Hello. this is the subject");
        mimeMessageHelper.setText("This is the body.");
        mimeMessageHelper.setTo("adityaprasada97@yahoo.com");
        mimeMessageHelper.setFrom("aditya257661@gmail.com");

        FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\adity\\Pictures\\ram.jpg"));

        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        javaMailSender.send(mimeMessage);

        System.out.println("Mail sent successfully");
    }
}
