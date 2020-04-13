package com.example.demo.controller;

import com.example.demo.model.Hotel;

import java.util.List;

public class Categories {

  public String categoryName;

  public List <Hotel> hotels_inside_category;
  public Categories(String name) {this.categoryName = name;}

  public void setHotelInsideCategory(Hotel hotel_inside_category) {
    this.hotels_inside_category.add(hotel_inside_category);
  }
  public List <Hotel> getHotelsInsideCategory(){
    return this.hotels_inside_category;
  }
}
