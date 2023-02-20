package com.dev.backend.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_state")
public class State {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String acronym;

  @Temporal(TemporalType.TIMESTAMP)
  private Instant creatDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Instant updateDate;


  public State(){}
  public State(Long id, String name, String acronym, Instant creatDate, Instant updateDate) {
    this.id = id;
    this.name = name;
    this.acronym = acronym;
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
  public String getAcronym() {
    return acronym;
  }
  public void setAcronym(String acronym) {
    this.acronym = acronym;
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
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((acronym == null) ? 0 : acronym.hashCode());
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
    State other = (State) obj;
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
    if (acronym == null) {
      if (other.acronym != null)
        return false;
    } else if (!acronym.equals(other.acronym))
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
    return "State [id=" + id + ", name=" + name + ", acronym=" + acronym + ", creatDate=" + creatDate + ", updateDate="
        + updateDate + "]";
  }
  
  
  

  
}
