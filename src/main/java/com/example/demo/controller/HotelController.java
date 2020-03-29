package com.example.demo.controller;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelRepository;
import com.example.demo.model.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;


@RestController
@CrossOrigin("http://localhost:4200")
public class HotelController {


  @Autowired
  private HotelService hotelService;
  @Autowired
  private HotelRepository hotelRepository;
  private Hotel hotel = new Hotel();

  public HotelController(HotelService hotelService)
  {
    this.hotelService = hotelService;
    this.hotel = new Hotel();
  }

  public void printMeHotel(){
    hotel = this.hotelService.getHotelByName("Bosna Hotel");
    System.out.println("hotel price is: " + hotel.getPrice());
    System.out.println("Hotel City:" + hotel.getCity());


    //List<Hotel> hotels_within_price_range = filter_getHotelWithinPriceRange("80");

  }

  @GetMapping(path="/hotels")
  public @ResponseBody Iterable<Hotel> getAllHotels() {
    System.out.println("Objects are ");
    return hotelRepository.findAll();
  }

  @GetMapping(path="/hotel?price=")
  public @ResponseBody Iterable<Hotel> getHotelWithinPriceRange(@RequestParam String price) {
    System.out.println("Objects are ");

    //List<Hotel> hotels_within_price_range = filter_getHotelWithinPriceRange(price);
    //Iterable<Hotel> return_hotels_within_price_range = hotels_within_price_range;

    return filter_getHotelWithinPriceRange(price);
  }

  //-----Filtering----//
  private Iterable<Hotel> filter_getHotelWithinPriceRange(String price)
  {
    int hotel_price = Integer.parseInt(price);
    List<Hotel> hotels_within_price_range = new ArrayList<Hotel>();
    for(Hotel hotel: hotelService.getHotels())
    {

      if(hotel.getPrice() <= hotel_price)
      {
        hotels_within_price_range.add(hotel);
        System.out.println("hotel name is: " + hotel.getName() + " and price is " + hotel.getPrice());
      }
    }

    Collections.sort(hotels_within_price_range);
    for(Hotel hotel_sorted: hotels_within_price_range)
      System.out.println("hotel is: " + hotel_sorted.getName() + " and price is " + hotel_sorted.getPrice());

    Iterable<Hotel> hotels__ = hotels_within_price_range;
    for(Hotel it: hotels__)
      System.out.println(it.getName() + " " + it.getPrice());


    return hotels__;
  }

}
