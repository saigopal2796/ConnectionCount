package com.example.demo.manager;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class Sendemailservice {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	public void sendMail(String to, String subject, String from, String body) throws MessagingException {
	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);//true indicates multipart message
	    helper.setSubject(subject);
	    helper.setTo(InternetAddress.parse(to));
	    helper.setText(body, true);//true indicates body is html
	    helper.setFrom("kiran.mudili@epsoftinc.com"); //set sender email and get it from application properties
	    //helper.addAttachment("filename", new ClassPathResource("\\static\\path")); //You can add email attachment 
	    javaMailSender.send(message);
	}
	
	
	
	

}
