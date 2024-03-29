package com.dev.backend.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "O nome do usuário é obrigatório")
  @Size(min = 2, max = 70, message = "O minímo de 2 caracteres")
  private String name;
  @CPF(message = "CPF inválido")
  private String cpf;
  @Email(message = "Email inválido")
  private String email;
  private String codRecoveryPassword;
  @Temporal(TemporalType.TIMESTAMP)
  private Instant dataValidatorCod;
  private String password;
  private String address;
  private String zipCode;
  @CreationTimestamp
  private Instant creatDate;
  @UpdateTimestamp
  private Instant updateDate;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //Remove do banco em sí e do que foi relacionado.
  private  List<PermissionUser> permissionUser = new ArrayList<>();

  public User(){}
  public User(Long id, String name, String cpf, String email, String password, String address, String zipCode,
      Instant creatDate, Instant updateDate) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.password = password;
    this.address = address;
    this.zipCode = zipCode;
    this.creatDate = creatDate;
    this.updateDate = updateDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Instant getCreatDate() {
    return creatDate;
  }

  public void setCreatDate(Instant creatDate) {
    this.creatDate = creatDate;
  }

  public Instant getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Instant updateDate) {
    this.updateDate = updateDate;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public String getCodRecoveryPassword() {
    return codRecoveryPassword;
  }

  public void setCodRecoveryPassword(String codRecoveryPassword) {
    this.codRecoveryPassword = codRecoveryPassword;
  }

  public Instant getDataValidatorCod() {
    return dataValidatorCod;
  }
  
  public void setDataValidatorCod(Instant dataValidatorCod) {
    this.dataValidatorCod = dataValidatorCod;
  }
  public List<PermissionUser> getPermissionUser() {
    return permissionUser;
  }
  // public void setPermissionUser(List<PermissionUser> listPermissionUser) {
  //   for (PermissionUser obj : listPermissionUser) {
  //     obj.setUser(this);
  //   }
  //   this.permissionUser = listPermissionUser;
  // }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
    User other = (User) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
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
    return true;
  }
 
}
