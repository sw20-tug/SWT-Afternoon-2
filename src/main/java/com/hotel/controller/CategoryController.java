package com.hotel.controller;
import com.hotel.model.Category;
import com.hotel.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  private Category category;

  public CategoryController(CategoryService categoryService)
  {
    this.categoryService = categoryService;
    this.category = new Category();
  }
}
