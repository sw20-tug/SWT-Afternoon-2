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
    private  String name = new String();
    private float rating;
    private int price_per_night;
    private String location;

    private Hotel(String name, float rating, int price_per_night, String location)
    {
      this.rating = rating;
      this.name = name;
      this.price_per_night = price_per_night;
      this.location = location;
    }
    public Hotel(String name)
    {
      this.name = name;
    }

    public Hotel() {}
}
