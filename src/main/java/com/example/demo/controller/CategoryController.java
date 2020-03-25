package com.example.demo.controller;
import com.example.demo.model.Category;
import com.example.demo.model.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.awt.geom.AreaOp;

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
  public void printMeCategory(){
    this.category = this.categoryService.getCategoryByName("sexhotel");
    System.out.println("Category name:" + category.getName());
  }
}
