package com.hotel.repository;

import com.hotel.model.Hotel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
  Hotel findByName(String name);


  List <Hotel> findByPrice(int price);

  List <Hotel> findByRate(int rate);

  List <Hotel> findByCity(String city);

  List <Hotel> findByDescription(String Description);

  List <Hotel> findByImagePath(String imagePath);

  List <Hotel> findByStars(int stars);

  List <Hotel> findByParking(boolean parking);

  List <Hotel> findByRestaurant(boolean restaurant);

  List <Hotel> findByPets(boolean pets);

  List <Hotel> findBySmoking(boolean smoking);

  List <Hotel> findBySwimmingPool(boolean swimming);

  List <Hotel> findByBeachFront(boolean beachFront);

  List <Hotel> findByAirConditioning(boolean airConditioning);

  List <Hotel> findByFreeWiFi(boolean freeWifi);

  List <Hotel> findBySauna(boolean sauna);

  List <Hotel> findByFitness(boolean fitness);

  List <Hotel> findByActivity(String activity);

  List <Hotel> findByOtherFilters(String otherFilters);

  @Query(
    value = "SELECT id FROM swt.hotel ORDER BY id DESC LIMIT 1",
    nativeQuery = true)
  Integer findLastId();


  @Transactional
  @Modifying
  @Query(
    value = "INSERT INTO swt.hotel(id, activity, activity_gym, activity_openbar, activity_running, air_conditioning, beach_front, city, description, fitness, free_wi_fi, image_path, name, other_filters, parking, pets, price, rate, restaurant, sauna, smoking, stars, swimming_pool, category_id) VALUES (:id, :activity, b'1', b'0', b'1', b'0', b'1', :city, :description, b'0', b'1', :image, :name, :otherFilters, b'1', b'1', :price, :rating, b'0', b'0', b'1', :stars, b'1', :category)",
    nativeQuery = true)
  void  insertNewHotels(@Param("id") int id,  @Param("name") String name, @Param("description") String description, @Param("category") int category, @Param("price") int price, @Param("rating")int rating, @Param("stars")int stars,
                        @Param("city")String city, @Param("activity")String activity, @Param("otherFilters")String otherFilters, @Param("image")String image);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = :categoryId ORDER BY RAND() limit 5",
    nativeQuery = true)
  List <Hotel> findByCategoryId(@Param("categoryId")int categoryId);

  @Query("FROM Hotel WHERE price >= :minPrice AND price <= :maxPrice AND rate >= :minRating AND rate <= :maxRating " +
    "AND stars = :stars AND activity = :activity AND city IN (:locations) AND otherFilters = :otherFilters")
    List <Hotel> applyFilters(@Param("minPrice") int minPrice, @Param("maxPrice")int maxPrice, @Param("minRating")int minRating,
                            @Param("maxRating") int maxRating,
                            @Param("stars") int stars, @Param("activity")String activity, @Param("locations")List<String> locations, @Param("otherFilters") String otherFilters);
  @Query("FROM Hotel WHERE activity = :activity")
  List <Hotel> findHotelsByActivities(@Param("activity")String activity);

  @Query(
    value = "SELECT * FROM hotel WHERE id = :hotelId",
    nativeQuery = true)
  Hotel findHotelById(@Param("hotelId")int hotelId);

  @Transactional
  @Modifying
  @Query(
    value = "DELETE  FROM hotel WHERE name = :hotel_name",
    nativeQuery = true)
  void deleteHotel(@Param("hotel_name") String hotel_name);

  @Override
  List<Hotel> findAll();

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY price ASC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByPriceASC(int category_id);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY price DESC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByPriceDESC(int category_id);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY rate ASC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByRateASC(int category_id);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY rate DESC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByRateDESC(int category_id);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY stars DESC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByStarsASC(int category_id);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY stars ASC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByStarsDESC(int category_id);
}
