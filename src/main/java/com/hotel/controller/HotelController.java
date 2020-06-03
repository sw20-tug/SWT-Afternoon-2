package com.hotel.controller;

import com.hotel.model.Comment;
import com.hotel.model.Hotel;
import com.hotel.repository.CommentRepository;
import com.hotel.repository.HotelRepository;
import com.hotel.services.CommentService;
import com.hotel.services.CustomerService;
import com.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HotelController {

  @Autowired
  private HotelService hotelService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private HotelRepository hotelRepository;
  @Autowired
  private CommentRepository commentRepository;

  private Hotel hotel = new Hotel();

  @Autowired
  private CustomerService customerService;

  private static final int DEFAULT = 0;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
    this.hotel = new Hotel();
  }


  //---Endpoints---//
  @GetMapping(path = "/hotels")
  public @ResponseBody
  Iterable<Hotel> getAllHotels() {
    return hotelService.getHotels();
  }

  @GetMapping(path = "/hotel")
  public @ResponseBody
  Iterable<Hotel> getHotelWithinPriceRange(@RequestParam String price) {

    return hotelService.getHotelWithinPriceRange(price);
  }

  @GetMapping(path = "/activities=")
  public @ResponseBody
  Iterable<Hotel> getActivities(@RequestParam String[] activities) {
    return hotelService.getHotelsByActivities(activities);

  }

  @GetMapping(path = "/criteria")
  public @ResponseBody
  List<Hotel> getHotelByCriteria(@RequestParam String category_id, @RequestParam String criteria_id) {
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

  @GetMapping(path="/getCategories")
  public @ResponseBody List<Categories> getFilledCategories() {
    return this.hotelService.getCategoriesWithHotels();
  }

  @PostMapping(path="/addNewHotels")
  public @ResponseBody ResponseEntity<String> AddNewHotels(@RequestParam String name, @RequestParam String description,
                                                           @RequestParam String category[],
                                                           @RequestParam String price, @RequestParam String rating,
                                                           @RequestParam String stars, @RequestParam String city,
                                                           @RequestParam String[] currentlySelectedActivities, @RequestParam Boolean[] otherFilters,
                                                           @RequestParam String imageURL) {
    this.hotelService.insertNewHotels(name, description, category, Integer.parseInt(price), Integer.parseInt(rating),
      Integer.parseInt(stars), city, currentlySelectedActivities, otherFilters, imageURL);
    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
  }

  @PostMapping(path="/addNewComment")
  public @ResponseBody ResponseEntity<String> AddNewComment(@RequestParam String comm_text, @RequestParam String user_name,
                                                           @RequestParam String rate, @RequestParam String hotel_id) {
    this.commentService.insertNewComment(comm_text, user_name, Integer.parseInt(rate), Long.parseLong(hotel_id));
    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
  }

  @GetMapping(path="/hotelDetail")
   public @ResponseBody Hotel getHotelById(@RequestParam String id) {
    return this.hotelService.getHotelById(Integer.parseInt(id));
  }

  @PostMapping(path="/changeRating")
  public @ResponseBody ResponseEntity<String>  changeRating(@RequestParam String new_rate, @RequestParam String id) {

    this.hotelService.changeRating(Integer.parseInt(new_rate), Long.parseLong(id));
    return new ResponseEntity<String>("POST Response", HttpStatus.OK);

  }

  @GetMapping(path = "/commentHotel")
  public @ResponseBody List<Comment> getCommentList(@RequestParam String id){
    //TODO: check for input param
    return this.commentService.getCommentsByHotelID(Long.parseLong(id));
  }

  @PostMapping(path="/deleteHotel")
  public @ResponseBody ResponseEntity<String> deleteHotel(@RequestParam String hotel_name) {
    this.hotelService.deleteHotel(hotel_name);
    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
  }

  @PostMapping(path="/editHotels")
  public @ResponseBody ResponseEntity<String> editHotel(@RequestParam String name, @RequestParam String description,
                                                        @RequestParam String category[],
                                                        @RequestParam String price, @RequestParam String rating,
                                                        @RequestParam String stars, @RequestParam String city,
                                                        @RequestParam String[] currentlySelectedActivities,
                                                        @RequestParam Boolean[] otherFilters, @RequestParam String id) {

    this.hotelService.editHotel(name, description, category, Integer.parseInt(price), Integer.parseInt(rating),
      Integer.parseInt(stars), city, currentlySelectedActivities, otherFilters, Integer.parseInt(id));
    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
  }

}
