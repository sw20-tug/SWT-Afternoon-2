package com.hotel.services;
import com.hotel.controller.Categories;
import com.hotel.repository.HotelRepository;
import com.hotel.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class HotelService {

  private static final int LOWEST_PRICE = 1;
  private static final int HIGHEST_PRICE = 2;
  private static final int LOWEST_RATING = 3;
  private static final int HIGHEST_RATING = 4;
  private static final int MOST_STARS = 6;
  private static final int LEAST_STARS = 5;

  private static final int NUMBER_OF_ACTIVITIES = 3;

  @Autowired
  public HotelRepository hr;

  public HotelService(HotelRepository hr) {
    this.hr = hr;
  }

  public Hotel getHotelByName(String name) {
    return this.hr.findByName(name);
  }

  public List <Hotel> getHotelsByPrice(int price) {
    return this.hr.findByPrice(price);
  }

  public List <Hotel> getHotelsByCity(String city){return this.hr.findByCity(city);}

  public List <Hotel> getHotelsByDescription(String description){return this.hr.findByDescription(description);}

  public List <Hotel> getHotelsByStars(int stars){return this.hr.findByStars(stars);}

  public List <Hotel> getHotelsByParking(boolean parking ){return this.hr.findByParking(parking);}

  public List <Hotel> getHotelsByRestaurant(boolean restaurant){return this.hr.findByRestaurant(restaurant);}

  public List <Hotel> getHotelsByPets(boolean pets){return this.hr.findByPets(pets);}

  public List <Hotel> getHotelsBySmoking(boolean smoking){return this.hr.findBySmoking(smoking);}

  public List <Hotel> getHotelsBySwimmingPool(boolean swimmingPool){return this.hr.findBySwimmingPool(swimmingPool);}

  public List <Hotel> getHotelsByBeachFront(boolean beachFront){return this.hr.findByBeachFront(beachFront);}

  public List <Hotel> getHotelsByAirConditioning(boolean airConditioning){return this.hr.findByAirConditioning(airConditioning);}

  public List <Hotel> getHotelsByFreeWifi(boolean freeWifi){return this.hr.findByFreeWiFi(freeWifi);}

  public List <Hotel> getHotelsBySauna(boolean sauna){return this.hr.findBySauna(sauna);}

  public List <Hotel> getHotelsByFitness(boolean fitness){return this.hr.findByFitness(fitness);}

  public List <Hotel> getHotelsByActivity(String activity){return this.hr.findByActivity(activity);}

  public List <Hotel> getHotelsByOtherFilters(String otherFilters){return this.hr.findByOtherFilters(otherFilters);}

  public List<Hotel> getHotels() {
    return this.hr.findAll();
  }

  public Iterable<Hotel> getHotelWithinPriceRange(String price) {
    List<Hotel> hotels = new ArrayList<Hotel>();

    for (Hotel hotel : this.getHotels()) {

      if (hotel.getPrice() <= Integer.parseInt(price)) {
        hotels.add(hotel);
        System.out.println("hotel name is: " + hotel.getName() + " and price is " + hotel.getPrice());
      }
    }
    Collections.sort(hotels);
    return hotels;
  }

  public List<Hotel> getHotelByCriteria(int category_id, int criteria) {
    if (criteria == LOWEST_PRICE) {
      System.out.println("WSize is "+ hr.getHotelOrderedByPriceASC(category_id));
      return hr.getHotelOrderedByPriceASC(category_id);
    } else if (criteria == HIGHEST_PRICE) {
      return hr.getHotelOrderedByPriceDESC(category_id);
    } else if (criteria == LOWEST_RATING) {
      return hr.getHotelOrderedByRateASC(category_id);
    } else if (criteria == HIGHEST_RATING) {
      return hr.getHotelOrderedByRateDESC(category_id);
    } else if (criteria == MOST_STARS) {
      return hr.getHotelOrderedByStarsASC(category_id);
    } else if (criteria == LEAST_STARS) {
      return hr.getHotelOrderedByStarsDESC(category_id);
    }
    return null;
  }

  public List<Hotel> getHotelsByActivities(String[] activities){
    StringBuilder activity = new StringBuilder();
    for(String activity_ : activities)
    {
      switch(activity_){
        case "GYM":
        {
          activity.append("0");
          break;
        }
        case "RUNNING":
        {
          activity.append("1");
          break;
        }
        case "OPEN_BAR":
        {
          activity.append("2");
          break;
        }
      }
    }
    return this.hr.findHotelsByActivities(activity.toString());
  }

  public Iterable<Categories> applyAllFiltersAndGetHotels(int minPrice, int maxPrice, int minRating, int maxRating, int stars,
                                                          String[] currentlySelectedActivities, String[] currentlySelectedLocations, Boolean[] otherFilters) {
    StringBuilder activity = new StringBuilder();

    //we have three activites and need to initialise it
    for(int i = 0; i < NUMBER_OF_ACTIVITIES; i++)
    {
      activity.append(0);
    }
    System.out.println("initial activitiy" + activity.toString());


    StringBuilder otherFilters_ = new StringBuilder();
    List<String> locations = Arrays.asList(currentlySelectedLocations);

    Categories category_romantic = new Categories("Romantic");
    Categories category_adventure = new Categories("Adventure");
    Categories category_holiday = new Categories("Holiday");
    Categories category_wellness = new Categories("Wellness");
    Categories category_family = new Categories("Family");
    Categories category_camping = new Categories("Camping");


    for(boolean filter : otherFilters){
       otherFilters_.append(filter ? "1" : "0");
     }

     for(String activity_ : currentlySelectedActivities)
     {
       switch(activity_){
         case "Gym":
         {
           activity.replace(0, 1, "1");
           break;
         }
         case "Running":
         {
           activity.replace(1, 2, "1");
           break;
         }
         case "Open bar":
         {
           activity.replace(2, 3, "1");
           break;
         }
       }
     }



    System.out.println("minPrice " + minPrice);
    System.out.println("maxPrice " + maxPrice);
    System.out.println("minRating " + minRating);
    System.out.println("maxRating " + maxRating);
    System.out.println("starts " + stars);
    System.out.println("activitiy " + activity.toString());

    for(String location: locations)
      System.out.println("location " + location);

    System.out.println("otherfilters " + otherFilters_.toString());

    List <Hotel> hotels = this.hr.applyFilters(minPrice,maxPrice,minRating,maxRating,stars,activity.toString(),locations,otherFilters_.toString());

    System.out.println("found hotels: ");
    for(Hotel it: hotels)
      System.out.println(it.getName());



     for(Hotel ht : hotels){
       System.out.println("hotel is: " + ht.getName());
       switch(ht.getCategory().getName()){
         case "Romantic":
         {
           System.out.println("romantic");
           category_romantic.setHotelInsideCategory(ht);
           break;
         }
         case "Adventure":
         {
           System.out.println("adventure");
           category_adventure.setHotelInsideCategory(ht);
           break;
         }
         case "Holiday":
         {
           System.out.println("holiday");
           category_holiday.setHotelInsideCategory(ht);
           break;
         }
         case "Wellness":
         {
           System.out.println("wellness");
           category_wellness.setHotelInsideCategory(ht);
           break;
         }
         case "Family":
         {
           System.out.println("family");
           category_family.setHotelInsideCategory(ht);
           break;
         }
         case "Camping":
         {
           System.out.println("camping");
           category_camping.setHotelInsideCategory(ht);
           break;
         }
       }
     }

    List<Categories> hotelsInsideCategories = new ArrayList<Categories>();

    hotelsInsideCategories.add(category_romantic);
    hotelsInsideCategories.add(category_adventure);
    hotelsInsideCategories.add(category_holiday);
    hotelsInsideCategories.add(category_wellness);
    hotelsInsideCategories.add(category_family);
    hotelsInsideCategories.add(category_camping);

    return hotelsInsideCategories;
  }

  public List<Categories> getCategoriesWithHotels() {
    Categories categoryRomantic = new Categories("Romantic");
    Categories categoryAdventure = new Categories("Adventure");
    Categories categoryHoliday = new Categories("Holiday");
    Categories categoryWellness = new Categories("Wellness");
    Categories categoryFamily = new Categories("Family");
    Categories categoryCamping = new Categories("Camping");

    categoryRomantic.setHotelsInsideCategory(this.hr.findByCategoryId(1));
    categoryAdventure.setHotelsInsideCategory(this.hr.findByCategoryId(2));
    categoryHoliday.setHotelsInsideCategory(this.hr.findByCategoryId(3));
    categoryWellness.setHotelsInsideCategory(this.hr.findByCategoryId(4));
    categoryFamily.setHotelsInsideCategory(this.hr.findByCategoryId(5));
    categoryCamping.setHotelsInsideCategory(this.hr.findByCategoryId(6));

    List<Categories> allCategories = new ArrayList<Categories>();
    allCategories.add(categoryRomantic);
    allCategories.add(categoryAdventure);
    allCategories.add(categoryHoliday);
    allCategories.add(categoryWellness);
    allCategories.add(categoryFamily);
    allCategories.add(categoryCamping);

    return allCategories;
  }
}
