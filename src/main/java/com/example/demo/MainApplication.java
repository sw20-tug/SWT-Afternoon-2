package com.example.demo;

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
  /*  Categories category = new Categories("lifehotel");
    cr.save(category);
    Hotel hotel = new Hotel("Bosna Hotel", 5, "100Euros", "Bosna", "best hotel", "no image", 5,category);
    hr.save(hotel);
    System.out.println("Inserting Hotel and Categorie");*/
    System.out.println("Starting main application");
  }
}
