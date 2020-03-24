package com.example.demo;

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
      Stream.of("Burj Khalifa", "Jeben Hotel", "Jos Jebeniji").forEach(name -> {
        Hotel hotel = new Hotel(name);
        hotelRepository.save(hotel);
      });
      hotelRepository.findAll().forEach(System.out::println);

    };
  }

}
