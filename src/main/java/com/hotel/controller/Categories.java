package com.hotel.controller;

import com.hotel.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class Categories {

  public String categoryName;

  public List <Hotel> hotelsInsideCategory = new ArrayList<Hotel>();
  public Categories(String name) {this.categoryName = name;}

  public void setHotelInsideCategory(Hotel hotelInsideCategory) {
    this.hotelsInsideCategory.add(hotelInsideCategory);
  }
  public List <Hotel> getHotelsInsideCategory(){
    return this.hotelsInsideCategory;
  }

  public void setHotelsInsideCategory(List<Hotel> hotelsInsideCategory) {
    this.hotelsInsideCategory.addAll(hotelsInsideCategory);
  }
}
