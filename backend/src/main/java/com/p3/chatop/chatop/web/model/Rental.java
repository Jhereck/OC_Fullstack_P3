package com.p3.chatop.chatop.web.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RENTALS")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private int surface;
  private int price;
  private String picture;
  private String description;
  private int owner_id;

  @CreationTimestamp
  private Timestamp created_at;

  @UpdateTimestamp
  private Timestamp updated_at;

  public Rental(String name, int surface, int price, String picture, String description, int owner_id) {
    this.name = name;
    this.surface = surface;
    this.price = price;
    this.picture = picture;
    this.description = description;
    this.owner_id = owner_id;

  }

}
