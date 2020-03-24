package com.example.demo.controller;

import com.example.demo.HotelRepository;
import com.example.demo.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class HotelController {
  @Autowired
  private HotelRepository hotel_repository;

  @GetMapping("/hotels")
  public List<Hotel> getUsers() {

    return (List<Hotel>) hotel_repository.findAll();
  }

  @PostMapping("/hotels")
  void addHotel(@RequestBody Hotel hotel) {
    hotel_repository.save(hotel);
  }

}
