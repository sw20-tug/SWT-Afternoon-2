package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.model.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilteringOperation {

  @Autowired
  private HotelService hotelService;
  public FilteringOperation(HotelService hotelService)
  {
      this.hotelService = hotelService;
  }

  //-----Filtering----//
  private void determineFilters(String[] filters)
  {
    List<Filters> applied_filters = new ArrayList<Filters>();

    for(String filter_iterator: filters)
    {
      switch(filter_iterator) {
        case "price":
          System.out.println("price");
          applied_filters.add(Filters.PRICE);
          break;
        case "parking":
          System.out.println("parking");
          break;
        case "restaurant":
          System.out.println("restaurant");
          break;
        case "non_smoking_rooms":
          System.out.println("non_smoking_rooms");
          break;
        case "pets_allowed":
          System.out.println("pets_allowed");
          break;
        case "swimming_pool":
          System.out.println("swimming_pool");
          break;
        case "beach_front":
          System.out.println("beach_front");
          break;
        case "air_conditioning":
          System.out.println("air_conditioning");
          break;
        case "free_wifi":
          System.out.println("free_wifi");
          break;
        case "sauna":
          System.out.println("sauna");
          break;
        case "fitness":
          System.out.println("fitness");
          break;
        default:
          System.out.println("There is no such filter");
      }
    }

  }



  public Iterable<Hotel> filter_getHotelWithinPriceRange(String price)
  {
    List<Hotel> hotels_within_price_range = new ArrayList<Hotel>();
    for(Hotel hotel: hotelService.getHotels())
    {

      if(hotel.getPrice() <= Integer.parseInt(price))
      {
        hotels_within_price_range.add(hotel);
        System.out.println("hotel name is: " + hotel.getName() + " and price is " + hotel.getPrice());
      }
    }

    Collections.sort(hotels_within_price_range);
    //    for(Hotel hotel_sorted: hotels_within_price_range)
    //      System.out.println("hotel is: " + hotel_sorted.getName() + " and price is " + hotel_sorted.getPrice());

    Iterable<Hotel> hotels__ = hotels_within_price_range;
    for(Hotel it: hotels__)
      System.out.println(it.getName() + " " + it.getPrice());

    return hotels__;
  }

}
