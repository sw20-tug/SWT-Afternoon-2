package com.example.demo.model;

import com.example.demo.model.*;
import com.example.demo.controller.Categories;
import com.example.demo.controller.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerInput {
  private enum Sort {BY_PRICE, BY_DISTANCE_FROM_CENTER, BY_RATING}

  private static final int LOWEST_PRICE = 1;
  private static final int HIGHEST_PRICE = 2;
  private static final int LOWEST_RATING = 3;
  private static final int HIGHEST_RATING = 4;
  private static final int MOST_STARS = 6;
  private static final int LEAST_STARS = 5;

  @Autowired
  HotelRepository hr;

  @Autowired
  CategoryRepository cr;

  private Sort default_sort;

  //user input parameters
  private Integer min_price_per_night = null;
  private Integer max_price_per_night = null;
  private Integer min_customer_rating = null;
  private Integer max_customer_rating = null;
  private Integer customer_stars = null;
  private String[] activities = null;
  private String[] locations = null;
  private Boolean[] other_filters = null;

  //reset all parameters
  public CustomerInput()
  {
    default_sort = Sort.BY_PRICE;
    this.min_price_per_night = null;
    this.max_price_per_night = null;
    this.min_customer_rating = null;
    this.max_customer_rating = null;
    this.customer_stars = null;
    this.activities = null;
    this.locations = null;
    this.other_filters = null;
  }

  public CustomerInput(Integer min_price_per_night_, Integer max_price_per_night_,
                       Integer min_customer_rating_, Integer max_customer_rating_, Integer customer_stars_,
                       String[] activities_, String[] locations_, Boolean[] other_filters_)
  {
    this.min_price_per_night = min_price_per_night_;
    this.max_price_per_night = max_price_per_night_;
    this.min_customer_rating = min_customer_rating_;
    this.max_customer_rating = max_customer_rating_;
    this.customer_stars = customer_stars_;
    this.activities = activities_;
    this.locations = locations_;
    this.other_filters = other_filters_;
  }

  public Iterable<Hotel> filter_getHotelWithinPriceRange(String price, HotelService hotelService_) {

    List<Category> categories = new ArrayList<Category>();

    List<Hotel> hotels_within_price_range = new ArrayList<Hotel>();
    for (Hotel hotel : hotelService_.getHotels()) {

      if (hotel.getPrice() <= Integer.parseInt(price)) {
        hotels_within_price_range.add(hotel);
        System.out.println("hotel name is: " + hotel.getName() + " and price is " + hotel.getPrice());
      }
    }

    Collections.sort(hotels_within_price_range);
    //    for(Hotel hotel_sorted: hotels_within_price_range)
    //      System.out.println("hotel is: " + hotel_sorted.getName() + " and price is " + hotel_sorted.getPrice());

    Iterable<Hotel> hotels__ = hotels_within_price_range;
    for (Hotel it : hotels__)
      System.out.println(it.getName() + " " + it.getPrice());

    return hotels__;
  }

  public List<Hotel> getHotelByCriteria(int criteria) {
    if (criteria == CustomerInput.LOWEST_PRICE) {
      return hr.findAllOrderByPriceAsc();
    } else if (criteria == CustomerInput.HIGHEST_PRICE) {
      return hr.findAllOrderByPriceDesc();
    } else if (criteria == CustomerInput.LOWEST_RATING) {
      return hr.findAllOrderByRateAsc();
    } else if (criteria == CustomerInput.HIGHEST_RATING) {
      return hr.findAllOrderByRateDesc();
    } else if (criteria == CustomerInput.MOST_STARS) {
      return hr.findAllOrderByStarsDesc();
    } else if (criteria == CustomerInput.LEAST_STARS) {
      return hr.findAllOrderByStarsAsc();
    }
    return null;
  }

  public Iterable<Categories> applyAllFilters(HotelService hotelService) {
    List<Categories> categories = new ArrayList<Categories>();

    List<Hotel> all_hotels = hotelService.getHotels();
    Categories category_1 = new Categories(all_hotels);
    categories.add(category_1);

    List<Hotel> hotels_within_price_range = new ArrayList<Hotel>();
    Iterable<Hotel> found_hotels = hotels_within_price_range;

      return categories;
  }

}
