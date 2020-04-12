package com.example.demo.controller;
import com.example.demo.model.*;

import com.example.demo.model.Category;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelRepository;
import com.example.demo.model.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;

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

  @Autowired
  private CustomerInput customerInput;

  private static final int DEFAULT = 0;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
    this.hotel = new Hotel();
  }


  //--for testing---//
  public void printMeHotel() {

    //    Iterable<Hotel> hotels__ = filter_getHotelWithinPriceRange("80");;
    //    for(Hotel it: hotels__)
    //      System.out.println("test" + it.getName() + " " + it.getPrice());

    //    Iterable<Hotel> hotels__ = filter_getHotelWithinPriceRange("80");;
    //    for(Hotel it: hotels__)
    //      System.out.println("test" + it.getName() + " " + it.getPrice());
    //    Iterable<Categories> cat = customerInput.applyAllFilters(hotelService);
    //    for(Categories cat_it: cat)
    //      for(Hotel hotel_it: cat_it.hotel_inside_category)
    //        System.out.println("hotel: " + hotel_it.getName());
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
    return customerInput.filter_getHotelWithinPriceRange(price, hotelService);
  }

  @GetMapping(path = "/activities=")
  public @ResponseBody
  Iterable<Hotel> getActivities(@RequestParam String[] activities) {
    System.out.println("Hotels according activites ");
    return hotelRepository.findAll();
  }

  @GetMapping(path = "/criteria")
  public @ResponseBody
  List<Hotel> getHotelByCriteria(@RequestParam String category_id) {
    System.out.println("category_id : "+ category_id);
    return customerInput.getHotelByCriteria(Integer.parseInt(category_id));
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
    //System.out.println(currentlySelectedLocations.length);
    for(String locations_it: currentlySelectedLocations)
      System.out.println(locations_it);

    System.out.println("other_filters: ");
    for(Boolean other_filters_it: otherFilters)
      System.out.println(other_filters_it);

    try {
      customerInput = new CustomerInput(Integer.parseInt(minPrice), Integer.parseInt(maxPrice), Integer.parseInt(minRating),
        Integer.parseInt(maxRating), Integer.parseInt(starsFilter),
        currentlySelectedActivities, currentlySelectedLocations, otherFilters);
    }
    catch (Exception e)
    {
      System.out.println("customer input can not be created");
      e.printStackTrace();
    }

    return customerInput.applyAllFilters(this.hotelService);
  }

}
