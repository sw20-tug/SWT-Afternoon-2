package com.example.demo;

import com.example.demo.controller.CategoryController;
import com.example.demo.controller.HotelController;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@SpringBootApplication
public class MainApplication implements  CommandLineRunner {
  @Autowired
  HotelRepository hr;

  @Autowired
  CategoryRepository cr;

  private CategoryController cc;
  private CategoryService cs;
  private HotelController hc;
  private HotelService hs;

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Starting main application");
//    Category category1 = new Category("Romatic");
//    cr.save(category1);
//    Category category2 = new Category("Adventure");
//    cr.save(category2);
//    Category category3 = new Category("Holiday");
//    cr.save(category3);
//    Category category4 = new Category("Wellness");
//    cr.save(category4);
//    Category category5 = new Category("Family");
//    cr.save(category5);
//    Category category6 = new Category("Camping");
//    cr.save(category6);


    this.hs = new HotelService(hr);
    this.cs = new CategoryService(cr);

    this.hc = new HotelController(hs);
    this.cc = new CategoryController(cs);

//    fill_db();
//    hc.printMeHotel();
//    cc.printMeCategory();
  }

  public void fill_db()
  {
    String username = "enterYourUserName";
    String csvFile = "C:\\Users\\"+ username +"\\SWT-Afternoon-2\\import.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";


    try {

      br = new BufferedReader(new FileReader(csvFile));
      int counter = 0;
      while ((line = br.readLine()) != null) {

        // use comma as separator
        String[] hotels = line.split(cvsSplitBy);
        if (counter == 0)
        {
          counter++;
          continue;
        }

        long temp = Long.valueOf(hotels[17]);
        Category category_new = this.cs.getCategoryByID(temp);
        boolean parking = hotels[7].equals("1") ? true : false;
        boolean restaurant = hotels[8].equals("1") ? true : false;
        boolean pets = hotels[9].equals("1") ? true : false;
        boolean smoking = hotels[10].equals("1") ? true : false;
        boolean swimmingPool = hotels[11].equals("1") ? true : false;
        boolean beachFront = hotels[12].equals("1") ? true : false;
        boolean airConditioning = hotels[13].equals("1") ? true : false;
        boolean freeWifi = hotels[14].equals("1") ? true : false;
        boolean sauna = hotels[15].equals("1") ? true : false;
        boolean fitness = hotels[16].equals("1") ? true : false;
        int rate = Integer.valueOf(hotels[2]);
        int price = Integer.valueOf(hotels[5]);
        int stars = Integer.valueOf(hotels[6]);



        Hotel hotel = new Hotel(hotels[0], hotels[1], rate, hotels[3], hotels[4], price,
         stars, category_new, parking,  restaurant,  pets,  smoking,  swimmingPool,  beachFront,
         airConditioning,  freeWifi,  sauna,  fitness);
        hr.save(hotel);

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }



//    Category category1 = new Category("lifehotel");
//    cr.save(category1);
//    Hotel hotel1 = new Hotel("Graz Hotel", 5, 100, "Graz", "best hotel", "no image", 5,category1);
//    hr.save(hotel1);
  }
}
