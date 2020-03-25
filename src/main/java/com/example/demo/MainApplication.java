package com.example.demo;

import com.example.demo.controller.CategoryController;
import com.example.demo.controller.HotelController;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainApplication implements  CommandLineRunner {
  @Autowired
  HotelRepository hr;

  @Autowired
  CategoryRepository cr;

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Starting main application");
/*
    Category category1 = new Category("lifehotel");
    cr.save(category1);
    Hotel hotel1 = new Hotel("Graz Hotel", 5, "100Euros", "Graz", "best hotel", "no image", 5,category1);
    hr.save(hotel1);
    Category category2 = new Category("sexhotel");
    cr.save(category2);
    Hotel hotel2 = new Hotel("Bosna Hotel", 5, "100Euros", "Bosna", "best hotel", "no image", 5,category2);
    hr.save(hotel2);
    Category category3 = new Category("lifehotel");
    cr.save(category3);
    Hotel hotel3 = new Hotel("Wien Hotel", 5, "100Euros", "Wien", "best hotel", "no image", 5,category3);
    hr.save(hotel3);*/

    HotelService hs = new HotelService(hr);
    CategoryService cs = new CategoryService(cr);

    HotelController hc = new HotelController(hs);
    CategoryController cc = new CategoryController(cs);
    hc.printMeHotel();
    cc.printMeCategory();
  }
}
