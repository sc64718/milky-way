package com.milkyway.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void send(String toEmail) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(toEmail);
            helper.setReplyTo("milkyway@gmail.com");
            helper.setFrom("milkyway@gmail.com");
            helper.setSubject("Test Email");
            helper.setText("Welcome to Milkyway");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        javaMailSender.send(mail);
        //return helper;
    }

}
