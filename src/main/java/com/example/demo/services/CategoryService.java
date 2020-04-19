package com.example.demo.services;
import com.example.demo.model.Hotel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService{
  @Autowired
  CategoryRepository cr;

  public CategoryService(CategoryRepository cr)
  {
    this.cr = cr;
  }

  public Category getCategoryByName(String name) {
    return this.cr.findByName(name);
  }

  public Category getCategoryByID(long id) {
    return this.cr.findById(id);
  }
}
