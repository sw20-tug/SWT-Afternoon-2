package com.hotel;

import com.hotel.model.Category;
import com.hotel.model.Hotel;
import com.hotel.services.HotelService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EditHotelTests {

  @LocalServerPort
  int randomServerPort;

  @Autowired
  private HotelService hotelService;

  @Test
  public void checkEditHotelAdventure() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Hotel already_existing_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());


    final String baseUrl = "http://localhost:" + randomServerPort + "/editHotels?name=" + "new_hotel_name" + "&description=" + "new_hotel_description"+
      "&category=Adventure" +
      "&price=" + already_existing_hotel.getPrice() + "&rating=" + already_existing_hotel.getRate() + "&stars=" + already_existing_hotel.getStars() + "&city=" + "new_hotel_city" +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&id=" + already_existing_hotel.getId();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify edited Hotel
    Hotel edited_hotel = this.hotelService.getHotelById(already_existing_hotel.getId().intValue());
    assertThat(edited_hotel.getName()).isNotEqualTo(already_existing_hotel.getName());
    assertThat(edited_hotel.getDescription()).isNotEqualTo(already_existing_hotel.getDescription());
    assertThat(edited_hotel.getCity()).isNotEqualTo(already_existing_hotel.getCity());
  }


  @Test
  public void checkEditHotelCamping() throws Exception {
    RestTemplate restTemplate = new RestTemplate();


    Hotel already_existing_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());


    final String baseUrl = "http://localhost:" + randomServerPort + "/editHotels?name=" + "new_hotel_name2" + "&description=" + "new_hotel_description2"+
      "&category=Camping" +
      "&price=" + already_existing_hotel.getPrice() + "&rating=" + already_existing_hotel.getRate() + "&stars=" + already_existing_hotel.getStars() + "&city=" + "new_hotel_city2" +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&id=" + already_existing_hotel.getId();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify edited Hotel
    Hotel edited_hotel = this.hotelService.getHotelById(already_existing_hotel.getId().intValue());
    assertThat(edited_hotel.getName()).isNotEqualTo(already_existing_hotel.getName());
    assertThat(edited_hotel.getDescription()).isNotEqualTo(already_existing_hotel.getDescription());
    assertThat(edited_hotel.getCity()).isNotEqualTo(already_existing_hotel.getCity());
  }

  @Test
  public void checkEditHotelWellnes() throws Exception {
    RestTemplate restTemplate = new RestTemplate();


    Hotel already_existing_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());


    final String baseUrl = "http://localhost:" + randomServerPort + "/editHotels?name=" + "new_hotel_name3" + "&description=" + "new_hotel_description3"+
      "&category=Wellness" +
      "&price=" + already_existing_hotel.getPrice() + "&rating=" + already_existing_hotel.getRate() + "&stars=" + already_existing_hotel.getStars() + "&city=" + "new_hotel_city3" +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&id=" + already_existing_hotel.getId();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify edited Hotel
    Hotel edited_hotel = this.hotelService.getHotelById(already_existing_hotel.getId().intValue());
    assertThat(edited_hotel.getName()).isNotEqualTo(already_existing_hotel.getName());
    assertThat(edited_hotel.getDescription()).isNotEqualTo(already_existing_hotel.getDescription());
    assertThat(edited_hotel.getCity()).isNotEqualTo(already_existing_hotel.getCity());
  }

  @Test
  public void checkEditHotelRomantic() throws Exception {
    RestTemplate restTemplate = new RestTemplate();


    Hotel already_existing_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());


    final String baseUrl = "http://localhost:" + randomServerPort + "/editHotels?name=" + "new_hotel_name4" + "&description=" + "new_hotel_description4"+
      "&category=Romantic" +
      "&price=" + already_existing_hotel.getPrice() + "&rating=" + already_existing_hotel.getRate() + "&stars=" + already_existing_hotel.getStars() + "&city=" + "new_hotel_city4" +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&id=" + already_existing_hotel.getId();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify edited Hotel
    Hotel edited_hotel = this.hotelService.getHotelById(already_existing_hotel.getId().intValue());
    assertThat(edited_hotel.getName()).isNotEqualTo(already_existing_hotel.getName());
    assertThat(edited_hotel.getDescription()).isNotEqualTo(already_existing_hotel.getDescription());
    assertThat(edited_hotel.getCity()).isNotEqualTo(already_existing_hotel.getCity());

  }

  @Test
  public void checkEditHotelHoliday() throws Exception {
    RestTemplate restTemplate = new RestTemplate();


    Hotel already_existing_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());


    final String baseUrl = "http://localhost:" + randomServerPort + "/editHotels?name=" + "new_hotel_name5" + "&description=" + "new_hotel_description5"+
      "&category=Holiday" +
      "&price=" + already_existing_hotel.getPrice() + "&rating=" + already_existing_hotel.getRate() + "&stars=" + already_existing_hotel.getStars() + "&city=" + "new_hotel_city5" +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&id=" + already_existing_hotel.getId();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify edited Hotel
    Hotel edited_hotel = this.hotelService.getHotelById(already_existing_hotel.getId().intValue());
    assertThat(edited_hotel.getName()).isNotEqualTo(already_existing_hotel.getName());
    assertThat(edited_hotel.getDescription()).isNotEqualTo(already_existing_hotel.getDescription());
    assertThat(edited_hotel.getCity()).isNotEqualTo(already_existing_hotel.getCity());
  }

}
