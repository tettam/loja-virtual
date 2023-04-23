package com.dev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService{

  @Autowired
  private JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String sender;
    
  public String sendEmailText(String emailClient, String emailTitle, String emailMessage){

    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(sender);
      simpleMailMessage.setTo(emailMessage);
      simpleMailMessage.setSubject(emailTitle);
      simpleMailMessage.setText(emailTitle);
      javaMailSender.send(simpleMailMessage);

      return "Email enviado";
    } catch (Exception e) {
        return "Erro ao enviar o email";
    }
  }
}
