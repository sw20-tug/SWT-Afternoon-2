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
