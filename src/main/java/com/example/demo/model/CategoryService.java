package com.example.demo.model;
import com.example.demo.model.CategoryRepository;
import com.example.demo.model.Category;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
