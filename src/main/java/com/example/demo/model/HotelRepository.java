package com.example.demo.model;

import com.example.demo.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
  Hotel findByName(String name);
}
