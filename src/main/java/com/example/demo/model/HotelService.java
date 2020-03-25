package com.example.demo.model;
import com.example.demo.model.HotelRepository;
import com.example.demo.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService{
  @Autowired
  HotelRepository hr;

  public HotelService(HotelRepository hr)
  {
    this.hr = hr;
  }

  public Hotel getHotelByName(String name) {
    return this.hr.findByName(name);
  }
}
