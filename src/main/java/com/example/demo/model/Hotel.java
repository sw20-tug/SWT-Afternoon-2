package com.example.demo.model;

import javax.persistence.*;

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
  private Category category = new Category();
  public Hotel(String name, int rate, String price, String city, String description, String imagePath, int stars, Category category) {
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

  public String getName(){
    return this.name;
  }
  public int getRate(){
    return this.rate;
  }
  public String getPrice(){
    return this.price;
  }
  public String getCity(){
    return this.city;
  }
  public String getDescription(){
    return this.description;
  }
  public String getImage(){
    return this.imagePath;
  }
  public int getStars(){
    return this.stars;
  }
  public Category getCategory(){
    return this.category;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setRate(int rate){
    this.rate = rate;
  }
  public void setPrice(String price){
    this.price = price;
  }
  public void setCity(String city){
    this.city = city;
  }
  public void setDescription(String description){
    this.description = description;
  }
  public void setImage(String imagePath){
    this.imagePath = imagePath;
  }
  public void setStars(int stars){
    this.stars = stars;
  }
  public void setCategory(Category category){
    this.category = category;
  }

}
