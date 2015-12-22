package com.theironyard;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import java.util.Properties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Created by benjamindrake on 12/21/15.
 */
@Configurable
public class WeDayConfig {
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("weday22@gmail.com");
        mailSender.setPassword("uwotm8123");
        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.debug", "true");
        return mailSender;
    }
}