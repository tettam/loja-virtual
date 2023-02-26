package com.dev.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.User;
import com.dev.backend.repository.UserRepository;

@Service
public class UserManageService {
  
  @Autowired
  private UserRepository userRepository;  // Necessário para fazer busca via email

  private EmailService emailService;

  //Salvar User
  public String cod(String email){
  
    User user = userRepository.findByEmail(email);
    user.setCodRecoveryPassword(getCodRecoveryPassword(user.getId()));
    user.setDataValidatorCod(Instant.now());
    userRepository.saveAndFlush(user);
    emailService.sendEmailText(user.getEmail(), "Recuperação de senha", "Olá, o seu código para recuperação de senha é : " + user.getCodRecoveryPassword());

      return "Código Enviado!";
  }

  private String getCodRecoveryPassword(Long id){ //Cod único
    DateFormat format = new SimpleDateFormat("ddMMyyyHHmmssmm");//formatando data
    return format.format(new Date()) + id;
  }
}
