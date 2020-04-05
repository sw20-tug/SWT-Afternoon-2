package com.example.demo.controller;

import com.example.demo.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotels {

  private List<Hotel> hotels = new ArrayList<Hotel>();
  public Hotels(List<Hotel> hotels_) {
    this.hotels = hotels_;
  }
}
