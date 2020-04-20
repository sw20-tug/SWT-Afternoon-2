package com.hotel;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.hotel.controller.CategoryController;
import com.hotel.controller.HotelController;
import com.hotel.model.Category;
import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;
import com.hotel.services.CustomerService;
import com.hotel.services.HotelService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainApplicationTests {

  @Autowired
  CategoryController categoryController;
  HotelController hotelController;
  HotelService hotelService;

  @LocalServerPort
  private int port;

  @Mock
  private HotelRepository hotelRepository;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private CustomerService customerService;

  @LocalServerPort
  int randomServerPort;


  @Test
  public void getAllHotelsShouldReturnAllHotels() throws Exception {

    RestTemplate restTemplate = new RestTemplate();

    final String baseUrl = "http://localhost:" + randomServerPort + "/hotels";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void checkIfFilteredHotelsContainSpecifiedHotel() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100");
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getName()).isEqualTo(hotels_inside_category.get(0).getName());

  }

  @Test
  public void checkIfFilteredHotelHasFreeWiFi() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100");
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getFreeWiFi()).isEqualTo(hotels_inside_category.get(0).getFreeWiFi());

  }


  @Test
  public void checkApplyFiltersEndPointWithStatus200() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    final String baseUrl = "http://localhost:" + randomServerPort + "/apply?minPrice=10"  + "&maxPrice=100" +
      "&minRating=2" + "&maxRating=5"  + "&starsFilter=3"  +
      "&currentlySelectedActivities=" +
      "&currentlySelectedLocations=" + "&otherFilters=";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

  }

  @Test
  public void checkSortBySomeCriteriaWithStatus200() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    final String baseUrl = "http://localhost:" + randomServerPort + "/criteria?category_id=2" + "&criteria_id=1";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

  }

}
