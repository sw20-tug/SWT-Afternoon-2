package com.example.demo.controller;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelRepository;
import com.example.demo.model.HotelService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class HotelController {

  @Autowired
  private HotelService hotelService;
  @Autowired
  private HotelRepository hotelRepository;
  private Hotel hotel = new Hotel();
  private FilteringOperation filteringOperation;

  public HotelController(HotelService hotelService)
  {
    this.hotelService = hotelService;
    this.hotel = new Hotel();
    this.filteringOperation = new FilteringOperation(hotelService);
  }


  //--for testing---//
  public void printMeHotel(){
    hotel = this.hotelService.getHotelByName("Bosna Hotel");
    System.out.println("hotel price is: " + hotel.getPrice());
    System.out.println("Hotel City:" + hotel.getCity());


  //    Iterable<Hotel> hotels__ = filter_getHotelWithinPriceRange("80");;
  //    for(Hotel it: hotels__)
  //      System.out.println("test" + it.getName() + " " + it.getPrice());


  }

  //---Endpoints---//

  @GetMapping(path="/hotels")
  public @ResponseBody Iterable<Hotel> getAllHotels() {
    System.out.println("All Hotels ");
    return hotelRepository.findAll();
  }

  @GetMapping(path="/hotel")
  public @ResponseBody Iterable<Hotel> getHotelWithinPriceRange(@RequestParam String price) {
    System.out.println("price is " + price);
    System.out.println("Hotels within price ");
    return filteringOperation.filter_getHotelWithinPriceRange(price);
  }

  @GetMapping(path="/activities=")
  public @ResponseBody Iterable<Hotel> getActivities(@RequestParam String[] activities) {
    System.out.println("Hotels according activites ");
    //TODO
    return hotelRepository.findAll();
  }

}
