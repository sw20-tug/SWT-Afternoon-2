package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.HotelRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  @Column
  private int rate;

  @Column
  private String price;

  @Column
  private String city;

  @Column
  private String description;

  @Column
  private String imagePath;

  @Column
  private int stars;

  @ManyToOne
  private Categories category;

  public Hotel(String name, int rate, String price, String city, String description, String imagePath, int stars,Categories category) {
    this.rate = rate;
    this.name = name;
    this.price = price;
    this.city = city;
    this.description = description;
    this.imagePath = imagePath;
    this.stars = stars;
    this.category = category;
  }

  public Hotel(String name) {
    this.name = name;
  }
  public Hotel() {
  }
}
