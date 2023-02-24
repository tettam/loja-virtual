package com.dev.backend.dto;

import org.springframework.beans.BeanUtils;

import com.dev.backend.entities.City;
import com.dev.backend.entities.User;


public class UserClientRequestDTO {

  private String name;
  private String cpf;
  private String email;
  private String adress;
  private String zipCode;

  private City city;

  public UserClientRequestDTO(){}
  public UserClientRequestDTO(String name, String cpf, String email, String adress, String zipCode) {
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.adress = adress;
    this.zipCode = zipCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  //Converter UserDTO para User
  public User convertUser(UserClientRequestDTO userClienteDTO){ //Faz uma cópia de todas as própriedades da entidade UserDTO para User.
    User user = new User();
    BeanUtils.copyProperties(userClienteDTO, user); 
    return user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((adress == null) ? 0 : adress.hashCode());
    result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserClientRequestDTO other = (UserClientRequestDTO) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (cpf == null) {
      if (other.cpf != null)
        return false;
    } else if (!cpf.equals(other.cpf))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (adress == null) {
      if (other.adress != null)
        return false;
    } else if (!adress.equals(other.adress))
      return false;
    if (zipCode == null) {
      if (other.zipCode != null)
        return false;
    } else if (!zipCode.equals(other.zipCode))
      return false;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "UserClientRequestDTO [name=" + name + ", cpf=" + cpf + ", email=" + email + ", adress=" + adress
        + ", zipCode=" + zipCode + ", city=" + city + "]";
  }
}
