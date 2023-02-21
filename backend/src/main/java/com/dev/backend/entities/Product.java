package com.dev.backend.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private Double costPrice;
  private Double salePrice;

  @CreationTimestamp
  private Instant creatDate;

  @UpdateTimestamp
  private Instant updateDate;


  @ManyToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public Product(){}
  public Product(Long id, String name, String description, Double costPrice, Double salePrice, Instant creatDate, Instant updateDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.costPrice = costPrice;
    this.salePrice = salePrice;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(Double costPrice) {
    this.costPrice = costPrice;
  }

  public Double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
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
    result = prime * result + ((costPrice == null) ? 0 : costPrice.hashCode());
    result = prime * result + ((salePrice == null) ? 0 : salePrice.hashCode());
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
    Product other = (Product) obj;
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
    if (costPrice == null) {
      if (other.costPrice != null)
        return false;
    } else if (!costPrice.equals(other.costPrice))
      return false;
    if (salePrice == null) {
      if (other.salePrice != null)
        return false;
    } else if (!salePrice.equals(other.salePrice))
      return false;
    return true;
  }

  
}
