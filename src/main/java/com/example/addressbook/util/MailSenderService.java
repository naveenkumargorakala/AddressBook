package com.example.addressbook.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderService {

    @Autowired
   private JavaMailSender mailSender;

    //send mail to specific mail_id
    public void sendMail(String mail, String subject,String data ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nicenaveengorakala@gmail.com");
        message.setTo(mail);
        message.setSubject(subject);
        message.setText(data);
        mailSender.send(message);
        System.out.println("mail sent to "+mail);
    }
}
