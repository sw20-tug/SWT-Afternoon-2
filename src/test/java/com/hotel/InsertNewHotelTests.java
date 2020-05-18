package com.hotel;

import com.hotel.model.Category;
import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;
import com.hotel.services.HotelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsertNewHotelTests {

  @LocalServerPort
  int randomServerPort;

  @Autowired
  private HotelService hotelService;

  @Test
  public void checkAddNewHotel() throws Exception {
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

  }

}
