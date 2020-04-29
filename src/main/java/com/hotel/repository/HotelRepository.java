package com.hotel.repository;

import com.hotel.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    value = "SELECT * FROM hotel WHERE category_id = :categoryId limit 5",
    nativeQuery = true)
  List <Hotel> findByCategoryId(@Param("categoryId")int categoryId);
/*
  @Query("SELECT e FROM Employee e WHERE e.dept = :dept AND "
    + "(SELECT COUNT(DISTINCT e2.salary) FROM Employee e2 "
    + "WHERE e.salary < e2.salary AND e2.dept = :dept) < :topSalNum "
    + "ORDER BY e.salary DESC")
  List<Employee> findByDeptTopNSalaries(@Param("topSalNum") long topSalaryNum, @Param("dept") String dept);
*/
  @Query("FROM Hotel WHERE price >= :minPrice AND price <= :maxPrice AND rate >= :minRating AND rate <= :maxRating " +
    "AND stars = :stars AND activity = :activity AND city IN (:locations) AND otherFilters = :otherFilters")
    List <Hotel> applyFilters(@Param("minPrice") int minPrice, @Param("maxPrice")int maxPrice, @Param("minRating")int minRating,
                            @Param("maxRating") int maxRating,
                            @Param("stars") int stars, @Param("activity")String activity, @Param("locations")List<String> locations, @Param("otherFilters") String otherFilters);
  @Query("FROM Hotel WHERE activity = :activity")
  List <Hotel> findHotelsByActivities(@Param("activity")String activity);

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
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY stars ASC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByStarsASC(int category_id);

  @Query(
    value = "SELECT * FROM hotel WHERE category_id = ?1 ORDER BY stars DESC limit 5",
    nativeQuery = true)
  List<Hotel> getHotelOrderedByStarsDESC(int category_id);
}
