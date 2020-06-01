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
public class DeleteHotelTests {

  @LocalServerPort
  int randomServerPort;

  @Autowired
  private HotelService hotelService;

  @Test
  public void checkDeleteNewHotelAdventure() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Hotel new_hotel = new Hotel("test_hotel", "Graz", 2, "new_image.jpg", "HotelDescription",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);

    final String baseUrl = "http://localhost:" + randomServerPort + "/addNewHotels?name=" + new_hotel.getName() + "&description=" + new_hotel.getDescription() +
      "&category=Romantic" +
      "&price=" + new_hotel.getPrice() + "&rating=" + new_hotel.getRate() + "&stars=" + new_hotel.getStars() + "&city=" + new_hotel.getCity() +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&imageURL=" + new_hotel.getImage();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify existing Hotel
    Hotel new_inserted_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());

    assertThat(new_inserted_hotel.getName()).isEqualTo(new_hotel.getName());
    assertThat(new_inserted_hotel.getDescription()).isEqualTo(new_hotel.getDescription());
    assertThat(new_inserted_hotel.getPrice()).isEqualTo(new_hotel.getPrice());
    assertThat(new_inserted_hotel.getRate()).isEqualTo(new_hotel.getRate());
    assertThat(new_inserted_hotel.getStars()).isEqualTo(new_hotel.getStars());
    assertThat(new_inserted_hotel.getCity()).isEqualTo(new_hotel.getCity());
    assertThat(new_inserted_hotel.getImage()).isEqualTo(new_hotel.getImage());


    final String deleteUrl = "http://localhost:" + randomServerPort + "/deleteHotel?hotel_name=" + new_hotel.getName();
    URI uri_delete = new URI(deleteUrl);

    HttpHeaders headers_delete = new HttpHeaders();
    headers_delete.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity_delete = new HttpEntity<String>(headers);
    ResponseEntity<String> result_delete = restTemplate.postForEntity(uri_delete, entity_delete, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result_delete.getStatusCodeValue());


    //Verify deleted Hotel
    Hotel deleted_hotel = this.hotelService.getHotelById(new_hotel.getId().intValue());
    assertThat(deleted_hotel).isNull();


  }

  @Test
  public void checkDeleteNewHotelRomantic() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Hotel new_hotel = new Hotel("test_hotel2", "Vienna", 10, "new_image_test.jpg", "HotelDescription",
      500, 1, new Category("Romantic"), false, false, false, true, true, true, false, true, false, false, true, false, false, "100", "1001111100", 0);

    final String baseUrl = "http://localhost:" + randomServerPort + "/addNewHotels?name=" + new_hotel.getName() + "&description=" + new_hotel.getDescription() +
      "&category=Romantic" +
      "&price=" + new_hotel.getPrice() + "&rating=" + new_hotel.getRate() + "&stars=" + new_hotel.getStars() + "&city=" + new_hotel.getCity() +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&imageURL=" + new_hotel.getImage();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify existing Hotel
    Hotel new_inserted_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());

    assertThat(new_inserted_hotel.getName()).isEqualTo(new_hotel.getName());
    assertThat(new_inserted_hotel.getDescription()).isEqualTo(new_hotel.getDescription());
    assertThat(new_inserted_hotel.getPrice()).isEqualTo(new_hotel.getPrice());
    assertThat(new_inserted_hotel.getRate()).isEqualTo(new_hotel.getRate());
    assertThat(new_inserted_hotel.getStars()).isEqualTo(new_hotel.getStars());
    assertThat(new_inserted_hotel.getCity()).isEqualTo(new_hotel.getCity());
    assertThat(new_inserted_hotel.getImage()).isEqualTo(new_hotel.getImage());


    final String deleteUrl = "http://localhost:" + randomServerPort + "/deleteHotel?hotel_name=" + new_hotel.getName();
    URI uri_delete = new URI(deleteUrl);

    HttpHeaders headers_delete = new HttpHeaders();
    headers_delete.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity_delete = new HttpEntity<String>(headers);
    ResponseEntity<String> result_delete = restTemplate.postForEntity(uri_delete, entity_delete, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result_delete.getStatusCodeValue());


    //Verify deleted Hotel
    Hotel deleted_hotel = this.hotelService.getHotelById(new_hotel.getId().intValue());
    assertThat(deleted_hotel).isNull();


  }

  @Test
  public void checkDeleteNewHotelWellnes() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Hotel new_hotel = new Hotel("test_hotel3", "Salzburg", 5, "new_image_image.jpg", "HotelDescription",
      100, 1, new Category("Wellness"), true, false, false, false, true, true, false, true, false, false, true, true, false, "100", "1001111100", 0);

    final String baseUrl = "http://localhost:" + randomServerPort + "/addNewHotels?name=" + new_hotel.getName() + "&description=" + new_hotel.getDescription() +
      "&category=Romantic" +
      "&price=" + new_hotel.getPrice() + "&rating=" + new_hotel.getRate() + "&stars=" + new_hotel.getStars() + "&city=" + new_hotel.getCity() +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&imageURL=" + new_hotel.getImage();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify existing Hotel
    Hotel new_inserted_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());

    assertThat(new_inserted_hotel.getName()).isEqualTo(new_hotel.getName());
    assertThat(new_inserted_hotel.getDescription()).isEqualTo(new_hotel.getDescription());
    assertThat(new_inserted_hotel.getPrice()).isEqualTo(new_hotel.getPrice());
    assertThat(new_inserted_hotel.getRate()).isEqualTo(new_hotel.getRate());
    assertThat(new_inserted_hotel.getStars()).isEqualTo(new_hotel.getStars());
    assertThat(new_inserted_hotel.getCity()).isEqualTo(new_hotel.getCity());
    assertThat(new_inserted_hotel.getImage()).isEqualTo(new_hotel.getImage());


    final String deleteUrl = "http://localhost:" + randomServerPort + "/deleteHotel?hotel_name=" + new_hotel.getName();
    URI uri_delete = new URI(deleteUrl);

    HttpHeaders headers_delete = new HttpHeaders();
    headers_delete.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity_delete = new HttpEntity<String>(headers);
    ResponseEntity<String> result_delete = restTemplate.postForEntity(uri_delete, entity_delete, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result_delete.getStatusCodeValue());


    //Verify deleted Hotel
    Hotel deleted_hotel = this.hotelService.getHotelById(new_hotel.getId().intValue());
    assertThat(deleted_hotel).isNull();


  }

  @Test
  public void checkDeleteNewHotelCamping() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Hotel new_hotel = new Hotel("test_hotel4", "Salzburg", 5, "new_image_image_image.jpg", "HotelDescription",
      42, 1, new Category("Camping"), true, false, false, false, true, true, false, true, false, false, true, true, false, "100", "1001111100", 0);

    final String baseUrl = "http://localhost:" + randomServerPort + "/addNewHotels?name=" + new_hotel.getName() + "&description=" + new_hotel.getDescription() +
      "&category=Romantic" +
      "&price=" + new_hotel.getPrice() + "&rating=" + new_hotel.getRate() + "&stars=" + new_hotel.getStars() + "&city=" + new_hotel.getCity() +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&imageURL=" + new_hotel.getImage();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify existing Hotel
    Hotel new_inserted_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());

    assertThat(new_inserted_hotel.getName()).isEqualTo(new_hotel.getName());
    assertThat(new_inserted_hotel.getDescription()).isEqualTo(new_hotel.getDescription());
    assertThat(new_inserted_hotel.getPrice()).isEqualTo(new_hotel.getPrice());
    assertThat(new_inserted_hotel.getRate()).isEqualTo(new_hotel.getRate());
    assertThat(new_inserted_hotel.getStars()).isEqualTo(new_hotel.getStars());
    assertThat(new_inserted_hotel.getCity()).isEqualTo(new_hotel.getCity());
    assertThat(new_inserted_hotel.getImage()).isEqualTo(new_hotel.getImage());


    final String deleteUrl = "http://localhost:" + randomServerPort + "/deleteHotel?hotel_name=" + new_hotel.getName();
    URI uri_delete = new URI(deleteUrl);

    HttpHeaders headers_delete = new HttpHeaders();
    headers_delete.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity_delete = new HttpEntity<String>(headers);
    ResponseEntity<String> result_delete = restTemplate.postForEntity(uri_delete, entity_delete, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result_delete.getStatusCodeValue());


    //Verify deleted Hotel
    Hotel deleted_hotel = this.hotelService.getHotelById(new_hotel.getId().intValue());
    assertThat(deleted_hotel).isNull();


  }

  @Test
  public void checkDeleteNewHotelHoliday() throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Hotel new_hotel = new Hotel("test_hotel5", "Salzburg", 5, "new_image_image_image.jpg", "HotelDescription",
      333, 1, new Category("Holiday"), true, false, false, true, true, true, false, true, false, false, true, true, false, "100", "1001111100", 0);

    final String baseUrl = "http://localhost:" + randomServerPort + "/addNewHotels?name=" + new_hotel.getName() + "&description=" + new_hotel.getDescription() +
      "&category=Romantic" +
      "&price=" + new_hotel.getPrice() + "&rating=" + new_hotel.getRate() + "&stars=" + new_hotel.getStars() + "&city=" + new_hotel.getCity() +
      "&currentlySelectedActivities=" + "&otherFilters=" +
      "&imageURL=" + new_hotel.getImage();
    URI uri = new URI(baseUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());

    //Verify existing Hotel
    Hotel new_inserted_hotel = this.hotelService.getHotelById(this.hotelService.getLastHotelId());

    assertThat(new_inserted_hotel.getName()).isEqualTo(new_hotel.getName());
    assertThat(new_inserted_hotel.getDescription()).isEqualTo(new_hotel.getDescription());
    assertThat(new_inserted_hotel.getPrice()).isEqualTo(new_hotel.getPrice());
    assertThat(new_inserted_hotel.getRate()).isEqualTo(new_hotel.getRate());
    assertThat(new_inserted_hotel.getStars()).isEqualTo(new_hotel.getStars());
    assertThat(new_inserted_hotel.getCity()).isEqualTo(new_hotel.getCity());
    assertThat(new_inserted_hotel.getImage()).isEqualTo(new_hotel.getImage());


    final String deleteUrl = "http://localhost:" + randomServerPort + "/deleteHotel?hotel_name=" + new_hotel.getName();
    URI uri_delete = new URI(deleteUrl);

    HttpHeaders headers_delete = new HttpHeaders();
    headers_delete.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity_delete = new HttpEntity<String>(headers);
    ResponseEntity<String> result_delete = restTemplate.postForEntity(uri_delete, entity_delete, String.class);
    //Verify request succeed
    Assert.assertEquals(200, result_delete.getStatusCodeValue());


    //Verify deleted Hotel
    Hotel deleted_hotel = this.hotelService.getHotelById(new_hotel.getId().intValue());
    assertThat(deleted_hotel).isNull();


  }

}
