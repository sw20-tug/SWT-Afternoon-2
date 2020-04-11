package com.example.demo.controller;
import com.example.demo.model.*;

import com.example.demo.model.Category;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelRepository;
import com.example.demo.model.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HotelController {

  @Autowired
  private HotelService hotelService;
  @Autowired
  private HotelRepository hotelRepository;
  private Hotel hotel = new Hotel();
  private CustomerInput customerInput;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
    this.hotel = new Hotel();
    this.customerInput = new CustomerInput(hotelService);
  }


  //--for testing---//
  public void printMeHotel() {
    hotel = this.hotelService.getHotelByName("Bosna Hotel");
    System.out.println("hotel price is: " + hotel.getPrice());
    System.out.println("Hotel City:" + hotel.getCity());
    //    Iterable<Hotel> hotels__ = filter_getHotelWithinPriceRange("80");;
    //    for(Hotel it: hotels__)
    //      System.out.println("test" + it.getName() + " " + it.getPrice());

    //    Iterable<Hotel> hotels__ = filter_getHotelWithinPriceRange("80");;
    //    for(Hotel it: hotels__)
    //      System.out.println("test" + it.getName() + " " + it.getPrice());
  }

  //---Endpoints---//
  @GetMapping(path = "/hotels")
  public @ResponseBody
  Iterable<Hotel> getAllHotels() {
    System.out.println("All Hotels ");
    return hotelRepository.findAll();
  }

  @GetMapping(path = "/hotel")
  public @ResponseBody
  Iterable<Hotel> getHotelWithinPriceRange(@RequestParam String price) {
    System.out.println("price is " + price);
    System.out.println("Hotels within price ");
    return customerInput.filter_getHotelWithinPriceRange(price);
  }

  @GetMapping(path = "/activities=")
  public @ResponseBody
  Iterable<Hotel> getActivities(@RequestParam String[] activities) {
    System.out.println("Hotels according activites ");
    //TODO
    return hotelRepository.findAll();
  }

  @GetMapping(path = "/criteria")
  public @ResponseBody
  List<Hotel> getHotelByCriteria(@RequestParam String criteria) {
    System.out.println("doso ?"+ criteria);
  //  System.out.println("Hotel with criteria is ?" + customerInput.getHotelByCriteria(1));
    return customerInput.getHotelByCriteria(Integer.parseInt(criteria));
  }



  @GetMapping(path="/apply")
  public @ResponseBody Iterable<Categories> getHotelsThroughAllFilters(@RequestParam String minPrice, @RequestParam String maxPrice,
                                                                       @RequestParam String minRating, @RequestParam String maxRating,
                                                                       @RequestParam String starsFilter,
                                                                       @RequestParam String[] currentlySelectedActivities,
                                                                       @RequestParam String[] currentlySelectedLocations,
                                                                       @RequestParam Boolean[] otherFilters)
  {
    System.out.println("Sorting through all filters ");
    System.out.println("print me all parameters: ");
    System.out.println("min_price_per_night: " +  minPrice);
    System.out.println("max_price_per_night: " +  maxPrice);
    System.out.println("min_customer_rating: " +  minRating);
    System.out.println("max_customer_rating: " +  maxRating);
    System.out.println("customer_stars: " +  starsFilter);

    System.out.println("activities: ");
    for(String activities_it: currentlySelectedActivities )
      System.out.println(activities_it);

    System.out.println("locations: ");
    for(String locations_it: currentlySelectedLocations)
      System.out.println(locations_it);

    System.out.println("other_filters: ");
    for(Boolean other_filters_it: otherFilters)
      System.out.println(other_filters_it);

    customerInput = new CustomerInput(Integer.parseInt(minPrice), Integer.parseInt(maxPrice), Integer.parseInt(minRating),
      Integer.parseInt(maxRating), Integer.parseInt(starsFilter),
      currentlySelectedActivities, currentlySelectedLocations, otherFilters);

    return customerInput.applyAllFilters();
  }

}
