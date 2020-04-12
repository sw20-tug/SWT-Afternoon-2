package com.example.demo.model;

import com.example.demo.model.Hotel;
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

  @Query("FROM Hotel ORDER BY price ASC")
  List<Hotel> findAllOrderByPriceAsc();

  @Query("FROM Hotel ORDER BY price DESC ")
  List<Hotel> findAllOrderByPriceDesc();

  @Query("FROM Hotel ORDER BY rate ASC")
  List<Hotel> findAllOrderByRateAsc();

  @Query("FROM Hotel ORDER BY rate DESC ")
  List<Hotel> findAllOrderByRateDesc();

  @Query("FROM Hotel ORDER BY stars ASC")
  List<Hotel> findAllOrderByStarsAsc();

  @Query("FROM Hotel ORDER BY stars DESC ")
  List<Hotel> findAllOrderByStarsDesc();
}
