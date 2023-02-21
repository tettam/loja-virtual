package com.dev.backend.entities;

import java.time.Instant;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_city")
public class City {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Instant creatDate;
  private Instant updateDate;


  @ManyToOne
  @JoinColumn(name = "id.State")
  private State state;

  public City(){}
  public City(Long id, String name, Instant creatDate, Instant updateDate, State state) {
    this.id = id;
    this.name = name;
    this.creatDate = creatDate;
    this.updateDate = updateDate;
    this.state = state;
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

  public State getState() {
    return state;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    City other = (City) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "City [id=" + id + ", name=" + name + ", creatDate=" + creatDate + ", updateDate=" + updateDate + ", state="
        + state + "]";
  }
}
