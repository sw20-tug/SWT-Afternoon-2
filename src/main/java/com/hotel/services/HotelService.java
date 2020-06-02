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

  public Integer getLastHotelId() {
    return this.hr.findLastId();
  }

  public Hotel getHotelById(Integer id) {
    return this.hr.findHotelById(id);
  }

  public List<Hotel> getHotelByName(String name) {
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
      }
    }
    Collections.sort(hotels);
    return hotels;
  }

  public List<Hotel> getHotelByCriteria(int category_id, int criteria) {
    if (criteria == LOWEST_PRICE) {

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

      List <Hotel> hotels = null;
      List<String>locations_ = new ArrayList<>();
      locations_ = hr.getAllCities();
      if(convertActivitesToString(currentlySelectedActivities).equals("000") && convertOtherFiltersToString(otherFilters).equals("0000000000")){

         hotels = this.hr.applyFiltersWithoutActivityandOtherFilters(minPrice, maxPrice == 0 ? hr.getMaxPrice() + 1 : maxPrice, minRating, maxRating == 0 ? hr.getMaxRating() : maxRating, stars, ((Arrays.asList(currentlySelectedLocations)).get(0).equals("0")) ? locations_ : Arrays.asList(currentlySelectedLocations));
      }
      else if(convertActivitesToString(currentlySelectedActivities).equals("000")){

       hotels = this.hr.applyFiltersWithoutActivity(minPrice, maxPrice == 0 ? hr.getMaxPrice() + 1 : maxPrice, minRating, maxRating == 0 ? hr.getMaxRating() : maxRating, stars, ((Arrays.asList(currentlySelectedLocations)).get(0).equals("0")) ? locations_ : Arrays.asList(currentlySelectedLocations), convertOtherFiltersToString(otherFilters));
      }
      else if(convertOtherFiltersToString(otherFilters).equals("0000000000")){

         hotels = this.hr.applyFiltersWithoutOtherFilters(minPrice, maxPrice == 0 ? hr.getMaxPrice() + 1 : maxPrice, minRating, maxRating == 0 ? hr.getMaxRating() : maxRating, stars, convertActivitesToString(currentlySelectedActivities), ((Arrays.asList(currentlySelectedLocations)).get(0).equals("0")) ? locations_ : Arrays.asList(currentlySelectedLocations));
      }
      else{

        hotels = this.hr.applyFilters(minPrice, maxPrice == 0 ? hr.getMaxPrice() + 1 : maxPrice, minRating, maxRating == 0 ? hr.getMaxRating() : maxRating, stars, convertActivitesToString(currentlySelectedActivities), ((Arrays.asList(currentlySelectedLocations)).get(0).equals("0")) ? locations_ : Arrays.asList(currentlySelectedLocations), convertOtherFiltersToString(otherFilters));
      }

    Categories category_romantic = new Categories("Romantic");
    Categories category_adventure = new Categories("Adventure");
    Categories category_holiday = new Categories("Holiday");
    Categories category_wellness = new Categories("Wellness");
    Categories category_family = new Categories("Family");
    Categories category_camping = new Categories("Camping");

   for(Hotel ht : hotels){
     switch(ht.getCategory().getName()){
       case "Romantic":
       {
         category_romantic.setHotelInsideCategory(ht);
         break;
       }
       case "Adventure":
       {
         category_adventure.setHotelInsideCategory(ht);
         break;
       }
       case "Holiday":
       {
         category_holiday.setHotelInsideCategory(ht);
         break;
       }
       case "Wellness":
       {
         category_wellness.setHotelInsideCategory(ht);
         break;
       }
       case "Family":
       {
         category_family.setHotelInsideCategory(ht);
         break;
       }
       case "Camping":
       {
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

  public void insertNewHotels(String name, String description, String category[], Integer price, Integer rating, Integer stars,
                              String city, String[] activities, Boolean[] otherFilters, String image)
  {
    //generate new id
    Integer new_id = this.hr.findLastId() + 1;
    this.hr.insertNewHotels(new_id, name, description, findCategoryId(category), price, rating, stars, city, convertActivitesToString(activities), convertOtherFiltersToString(otherFilters), image);
  }

  public void editHotel(String name, String description, String category[], Integer price, Integer rating, Integer stars,
                              String city, String[] activities, Boolean[] otherFilters, Integer id)
  {
    this.hr.editHotel(id, name, description, findCategoryId(category), price, rating, stars, city, convertActivitesToString(activities), convertOtherFiltersToString(otherFilters));
  }

  //helper functions
  public String convertActivitesToString(String[] currentlySelectedActivities)
  {
    StringBuilder activity = new StringBuilder();

    //we have three activites and need to initialise it
    for(int i = 0; i < NUMBER_OF_ACTIVITIES; i++)
      activity.append(0);

    for(String activity_ : currentlySelectedActivities)
    {
      if(activity_.equals("Fitness")) {
        activity.replace(0, 1, "1");
      }
      else if(activity_.equals("Running")) {
        activity.replace(1, 2, "1");
      }
      else if(activity_.equals("Open Bar")) {
        activity.replace(2, 3, "1");

      }
    }

    return  activity.toString();
  }

  public Hotel getHotelById(int hotelId)
  {
    return this.hr.findHotelById(hotelId);
  }

  public void deleteHotel(String hotel_name)
  {
    this.hr.deleteHotel(hotel_name);
  }

  public String convertOtherFiltersToString(Boolean[] otherFilters)
  {
    StringBuilder otherFilters_ = new StringBuilder();
    for(boolean filter : otherFilters){
      otherFilters_.append(filter ? "1" : "0");
    }
    return otherFilters_.toString();
  }

  public void changeRating (Integer new_rate, long id) {
    Hotel hotel = this.hr.findHotelById((int) id);
    if (hotel.getRating_num() == 0)
    {
      this.hr.changeRating(new_rate, id);
    }
    else
    {
      int old_rate = hotel.getRate();
      int rate = (hotel.getRating_num() * old_rate  + new_rate) / (hotel.getRating_num() + 1);
      this.hr.changeRating(rate, id);
    }

  }

  public int findCategoryId(String selectedCategory[]) {

    for(String category : selectedCategory)
    {
      switch(category){
        case "Romantic":
        {
          return 1;
        }
        case "Adventure":
        {
          return 2;
        }
        case "Holiday":
        {
          return 3;
        }
        case "Wellness":
        {
          return 4;
        }
        case "Family":
        {
          return 5;
        }
        case "Camping":
        {
          return 6;
        }

      }
    }

    //should never been reached
    return 0;
  }

}
