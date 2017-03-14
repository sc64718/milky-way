package com.milkyway.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void send(String toEmail, String passCode) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(toEmail);
            //helper.setReplyTo("milkyway@gmail.com");
            helper.setFrom("chatsaurabh4@gmail.com");
            helper.setSubject("Your Milkyway account details");
            helper.setText("Welcome to Milkyway. Your login pin is " +passCode);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        javaMailSender.send(mail);
        //return helper;
    }

}
