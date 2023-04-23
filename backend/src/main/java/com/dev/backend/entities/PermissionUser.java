package com.dev.backend.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_permission_user")
public class PermissionUser {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @CreationTimestamp
  private Instant creatDate;
  @UpdateTimestamp
  private Instant updateDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "permission_id")
  private Permission permission;

  public PermissionUser(){}
  public PermissionUser(Long id, Instant creatDate, Instant updateDate) {
    this.id = id;
    this.creatDate = creatDate;
    this.updateDate = updateDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Permission getPermission() {
    return permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((creatDate == null) ? 0 : creatDate.hashCode());
    result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
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
    PermissionUser other = (PermissionUser) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (creatDate == null) {
      if (other.creatDate != null)
        return false;
    } else if (!creatDate.equals(other.creatDate))
      return false;
    if (updateDate == null) {
      if (other.updateDate != null)
        return false;
    } else if (!updateDate.equals(other.updateDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PermissionUser [id=" + id + ", creatDate=" + creatDate + ", updateDate=" + updateDate + "]";
  }
}
