package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.services.CustomerService;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  private CustomerService customerService;

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
    return hotelService.getHotels();
  }

  @GetMapping(path = "/hotel")
  public @ResponseBody
  Iterable<Hotel> getHotelWithinPriceRange(@RequestParam String price) {
    System.out.println("price is " + price);
    System.out.println("Hotels within price ");
    return hotelService.getHotelWithinPriceRange(price);
  }

  @GetMapping(path = "/activities=")
  public @ResponseBody
  Iterable<Hotel> getActivities(@RequestParam String[] activities) {
    System.out.println("Hotels according activites ");
    return hotelService.getHotelsByActivities(activities);

  }

  @GetMapping(path = "/criteria")
  public @ResponseBody
  List<Hotel> getHotelByCriteria(@RequestParam String category_id, @RequestParam String criteria_id) {
    System.out.println("category_id : "+ category_id);

  //  System.out.println("Hotel with criteria is ?" + customerInput.getHotelByCriteria(1));
    return hotelService.getHotelByCriteria(Integer.parseInt(category_id), Integer.parseInt(criteria_id));

  }



  @GetMapping(path="/apply")
  public @ResponseBody Iterable<Categories> getHotelsThroughAllFilters(@RequestParam String minPrice, @RequestParam String maxPrice,
                                                                       @RequestParam String minRating, @RequestParam String maxRating,
                                                                       @RequestParam String starsFilter,
                                                                       @RequestParam String[] currentlySelectedActivities,
                                                                       @RequestParam String[] currentlySelectedLocations,
                                                                       @RequestParam Boolean[] otherFilters) {
    return this.hotelService.applyAllFiltersAndGetHotels(Integer.parseInt(minPrice),Integer.parseInt(maxPrice),Integer.parseInt(minRating),Integer.parseInt(maxRating),
                                                  Integer.parseInt(starsFilter),currentlySelectedActivities,currentlySelectedLocations,otherFilters);
  }

}
