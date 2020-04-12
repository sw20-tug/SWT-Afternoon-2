package com.example.demo.model;

import com.example.demo.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
  Hotel findByName(String name);

  Hotel findByPrice(int price);

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
