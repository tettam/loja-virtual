package com.dev.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
  @Autowired
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

  public String changePassword(User user){
    User userDatabase = userRepository.findByEmailAndCodRecoveryPassword(user.getEmail() , user.getCodRecoveryPassword());
    if(userDatabase != null) {
      Instant now = Instant.now();
      Instant dataValidatorCod = userDatabase.getDataValidatorCod();
      Duration duration = Duration.between(dataValidatorCod, now);
      long diff = duration.toSeconds();
      //Date dif = new Date(new Date().getTime() - user.getDataValidatorCod());
      if(diff < 900){
        //Spring Security é necessário para criptografar senha
        userDatabase.setPassword(user.getPassword());
        userDatabase.setCodRecoveryPassword(null);
        userRepository.saveAndFlush(userDatabase);
        return "Senha alterada com sucesso!";
      } else {
        return "Tempo expirado, solicite um onvo código";
      }
    } else {
      return "Email ou código não encontrado";
    }

    
  }

  private String getCodRecoveryPassword(Long id){ //Cod único
    DateFormat format = new SimpleDateFormat("ddMMyyyHHmmssmm");//formatando data
    return format.format(new Date()) + id;
  }
}
