package com.p3.chatop.chatop.web.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int rental_id;
  private int user_id;
  private String message;

  @CreationTimestamp
  private Timestamp created_at;

  @UpdateTimestamp
  private Timestamp updated_at;
}
