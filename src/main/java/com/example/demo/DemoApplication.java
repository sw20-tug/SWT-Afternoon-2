package com.example.demo;
import java.sql.*;

import com.example.demo.model.Hotel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner init(HotelRepository hotelRepository) {
    return args -> {
//      Stream.of("Burj Khalifa", "Jeben Hotel", "Jos Jebeniji").forEach(name -> {
//        Hotel hotel = new Hotel(name);
//        hotelRepository.save(hotel);
//      });
//      hotelRepository.findAll().forEach(System.out::println);
      System.out.println("U main sam");
      try {
        System.out.println("U main sam");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/swt", "root", "root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from swt.test");

        while (rs.next())
          System.out.println(rs.getInt(1) + "  " + rs.getString("hotel_name"));
        con.close();
      } catch (Exception e) {
        System.out.println(e);
      }
      System.out.println("Connection done");
    };
  }

}
