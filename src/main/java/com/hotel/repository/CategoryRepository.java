package com.hotel.repository;

import com.hotel.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
  Category findByName(String name);
  Category findById(long id);
}
