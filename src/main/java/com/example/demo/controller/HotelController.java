package com.example.demo.controller;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:4200")
public class HotelController {
  @Autowired
  private HotelService hotelService;
  private Hotel hotel = new Hotel();

  public HotelController(HotelService hotelService)
  {
    this.hotelService = hotelService;
    this.hotel = new Hotel();
  }

  public void printMeHotel(){
    hotel = this.hotelService.getHotelByName("Bosna Hotel");
    System.out.println("Hotel City:" + hotel.getCity());
  }

}
