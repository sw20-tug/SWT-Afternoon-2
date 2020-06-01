package com.hotel.services;

import com.hotel.model.Category;
import com.hotel.repository.CategoryRepository;
import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class CustomerService {
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
  public CustomerService()
  {
    this.min_price_per_night = null;
    this.max_price_per_night = null;
    this.min_customer_rating = null;
    this.max_customer_rating = null;
    this.customer_stars = null;
    this.activities = null;
    this.locations = null;
    this.other_filters = null;
  }

  public CustomerService(Integer min_price_per_night_, Integer max_price_per_night_,
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


  public List<Hotel> getHotelByCriteria(int category_id, int criteria) {
    if (criteria == CustomerService.LOWEST_PRICE) {
      return hr.getHotelOrderedByPriceASC(category_id);
    } else if (criteria == CustomerService.HIGHEST_PRICE) {
      return hr.getHotelOrderedByPriceDESC(category_id);
    } else if (criteria == CustomerService.LOWEST_RATING) {
      return hr.getHotelOrderedByRateASC(category_id);
    } else if (criteria == CustomerService.HIGHEST_RATING) {
      return hr.getHotelOrderedByRateDESC(category_id);
    } else if (criteria == CustomerService.MOST_STARS) {
      return hr.getHotelOrderedByStarsASC(category_id);
    } else if (criteria == CustomerService.LEAST_STARS) {
      return hr.getHotelOrderedByStarsDESC(category_id);
    }
    return null;
  }
  /*
  public Iterable<Categories> applyAllFilters(HotelService hotelService) {
    List<Categories> filteredHotelsInsideCategories = new ArrayList<Categories>();

    Categories category_romantic = new Categories();
    //example how to fill category with hotels
    category_romantic.setHotel_inside_category(hotelService.getHotels());

    Categories category_adventure = new Categories();
    Categories category_holiday = new Categories();
    Categories category_wellness = new Categories();
    Categories category_family = new Categories();
    Categories category_camping = new Categories();

    //TODO filtering

    filteredHotelsInsideCategories.add(category_romantic);
    filteredHotelsInsideCategories.add(category_adventure);
    filteredHotelsInsideCategories.add(category_holiday);
    filteredHotelsInsideCategories.add(category_wellness);
    filteredHotelsInsideCategories.add(category_family);
    filteredHotelsInsideCategories.add(category_camping);

    return filteredHotelsInsideCategories;
  }*/

}
