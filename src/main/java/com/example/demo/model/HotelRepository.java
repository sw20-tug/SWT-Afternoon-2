package com.example.demo.model;

import com.example.demo.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
  Hotel findByName(String name);
  Hotel findByPrice(int price);

  @Override
  List<Hotel> findAll();
}
