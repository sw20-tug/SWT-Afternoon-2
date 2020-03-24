package com.example.demo.model;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name = new String();
  private int rate;
  private String price;
  private String city;
  private String description;
  private String imagePath;
  private int stars;


  private Hotel(String name, int rate, String price, String city, String description, String imagePath, int stars) {
    this.rate = rate;
    this.name = name;
    this.price = price;
    this.city = city;
    this.description = description;
    this.imagePath = imagePath;
    this.stars = stars;
  }

  public Hotel(String name) {
    this.name = name;
  }

  public Hotel() {
  }
}
