package com.hotel;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;


import com.hotel.controller.Categories;
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

  @Autowired
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
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getName()).isEqualTo(hotels_inside_category.get(0).getName());

  }

  @Test
  public void checkIfFilteredHotelHasFreeWiFi() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getFreeWiFi()).isEqualTo(hotels_inside_category.get(0).getFreeWiFi());

  }

  @Test
  public void checkIfFilteredHotelHasParking() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getParking()).isEqualTo(hotels_inside_category.get(0).getParking());

  }

  @Test
  public void checkIfFilteredHotelHasRestaurant() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getRestaurant()).isEqualTo(hotels_inside_category.get(0).getRestaurant());

  }

  @Test
  public void checkIfFilteredHotelHasPets() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getPets()).isEqualTo(hotels_inside_category.get(0).getPets());

  }

  @Test
  public void checkIfFilteredHotelHasSmoking() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getSmoking()).isEqualTo(hotels_inside_category.get(0).getSmoking());

  }

  @Test
  public void checkIfFilteredHotelHasBeachFront() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getBeachFront()).isEqualTo(hotels_inside_category.get(0).getBeachFront());

  }

  @Test
  public void checkIfFilteredHotelHasAirConditioning() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getAirConditioning()).isEqualTo(hotels_inside_category.get(0).getAirConditioning());

  }

  @Test
  public void checkIfFilteredHotelHasSauna() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getSauna()).isEqualTo(hotels_inside_category.get(0).getSauna());

  }

  @Test
  public void checkIfFilteredHotelHasFitness() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getFitness()).isEqualTo(hotels_inside_category.get(0).getFitness());

  }

  @Test
  public void checkIfFilteredHotelHasActivityGym() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getActivityGym()).isEqualTo(hotels_inside_category.get(0).getActivityGym());

  }

  @Test
  public void checkIfFilteredHotelHasActivityRunning() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getActivityRunning()).isEqualTo(hotels_inside_category.get(0).getActivityRunning());

  }

  @Test
  public void checkIfFilteredHotelHasActivityOpenBar() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getActivityOpenBar()).isEqualTo(hotels_inside_category.get(0).getActivityOpenBar());

  }


  @Test
  public void checkIfFilteredHotelHasSwimmingPool() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getSwimmingPool()).isEqualTo(hotels_inside_category.get(0).getSwimmingPool());

  }

  @Test
  public void checkIfFilteredHotelOtherfilters() throws Exception {
    List<Hotel> hotels_inside_category = new ArrayList<>();
    Hotel hotel1 = new Hotel("Apartamentos Palmera Beach", "Wuzhen", 2, "https://traffickcam.com/images/2017/7/20160624_030441_XJDMC3.jpg", "Hotel 'Apartamentos Palmera Beach' at  Wuzhen with parking place,  without restaurant,  not allowing pets, with swimming pooland etc. is waiting for you :)",
      22, 1, new Category("Adventure"), true, false, false, true, true, true, true, true, false, false, true, false, false, "100", "1001111100", 0);
    hotels_inside_category.add(hotel1);

    when(hotelRepository.getHotelOrderedByPriceASC(2)).thenReturn(hotels_inside_category);
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    assertThat(actual_hotels_inside.get(0).getOtherFilters()).isEqualTo(hotels_inside_category.get(0).getOtherFilters());

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

  // TEST SETTER

  @Test
  public void checkIfSetterName() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setName("Test");
    assertThat(actual_hotels_inside.get(0).getName()).isEqualTo("Test");

  }

  @Test
  public void checkIfSetterRate() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setRate(98);
    assertThat(actual_hotels_inside.get(0).getRate()).isEqualTo(98);

  }

  @Test
  public void checkIfSetterCity() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setCity("Test City");
    assertThat(actual_hotels_inside.get(0).getCity()).isEqualTo("Test City");

  }

  @Test
  public void checkIfSetterDescription() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setDescription("Test Description");
    assertThat(actual_hotels_inside.get(0).getDescription()).isEqualTo("Test Description");

  }

  @Test
  public void checkIfSetterImagePath() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setImage("path/test");
    assertThat(actual_hotels_inside.get(0).getImage()).isEqualTo("path/test");

  }

  @Test
  public void checkIfSetterStars() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setStars(29);
    assertThat(actual_hotels_inside.get(0).getStars()).isEqualTo(29);

  }

  @Test
  public void checkIfSetterParking() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setParking(true);
    assertThat(actual_hotels_inside.get(0).getParking()).isEqualTo(true);

  }
  @Test
  public void checkIfSetterRestaurant() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setRestauran(true);
    assertThat(actual_hotels_inside.get(0).getRestaurant()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterPets() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setPets(true);
    assertThat(actual_hotels_inside.get(0).getPets()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterSmoking() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setSmoking(true);
    assertThat(actual_hotels_inside.get(0).getSmoking()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterSwimingPool() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setSwimmingPool(true);
    assertThat(actual_hotels_inside.get(0).getSwimmingPool()).isEqualTo(true);

  }
  @Test
  public void checkIfSetterBeachFront() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setBeachFront(true);
    assertThat(actual_hotels_inside.get(0).getBeachFront()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterAirCondition() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setAirConditioning(true);
    assertThat(actual_hotels_inside.get(0).getAirConditioning()).isEqualTo(true);

  }
  @Test
  public void checkIfSetterFreeWiFi() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setFreeWiFi(true);
    assertThat(actual_hotels_inside.get(0).getFreeWiFi()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterSauna() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setSauna(true);
    assertThat(actual_hotels_inside.get(0).getSauna()).isEqualTo(true);

  }
  @Test
  public void checkIfSetterFitness() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setFitness(true);
    assertThat(actual_hotels_inside.get(0).getFitness()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterGym() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setActivityGym(true);
    assertThat(actual_hotels_inside.get(0).getActivityGym()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterRunning() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setActivityRunning(true);
    assertThat(actual_hotels_inside.get(0).getActivityRunning()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterOpenBar() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setActivityOpenBar(true);
    assertThat(actual_hotels_inside.get(0).getActivityOpenBar()).isEqualTo(true);

  }

  @Test
  public void checkIfSetterActivity() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    actual_hotels_inside.get(0).setActivity("101");
    assertThat(actual_hotels_inside.get(0).getActivity()).isEqualTo("101");

  }

  @Test
  public void checkIfSetterNameInBD() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    String name = actual_hotels_inside.get(1).getName();
    //Super 8 Bloomington
    actual_hotels_inside.get(1).setName("Test");
    hotelRepository.save(actual_hotels_inside.get(1));

    assertThat(actual_hotels_inside.get(1).getName()).isEqualTo("Test");

  }

  @Test
  public void getRatingNum() throws Exception {
    List<Hotel> actual_hotels_inside = customerService.getHotelByCriteria(2, 1);
    String name = actual_hotels_inside.get(1).getName();
    //Super 8 Bloomington
    actual_hotels_inside.get(1).setRating_num(10);
    //hotelRepository.save(actual_hotels_inside.get(1));
    assertThat(actual_hotels_inside.get(1).getRating_num()).isEqualTo(10);
  }

  @Test
  public void deleteHotel() throws Exception {
    List<Hotel> hotels = hotelRepository.findByName("The Inn at the Tides");
    String name = hotels.size() != 0 ? hotels.get(0).getName() : "";
    //Super 8 Bloomington
    hotelRepository.deleteHotel(name);
    //hotelRepository.save(actual_hotels_inside.get(1));
    assertThat(hotelRepository.findByName(name).size()).isEqualTo(0);
  }
  @Test
  public void applyFilter01() throws Exception {
    int minPrice = 20;
    int maxPrice = 500;
    int minRating = 1;
    int maxRating = 5;
    int stars = 1;
    String[] ac = {"0","0","0"};
    String[] lc = {"Abbiadori","Adler","Athens"};
    Boolean[] otfil ={true,true,false,true,false,true,false,false,true,false};
    Iterable<Categories> categories = this.hotelService.applyAllFiltersAndGetHotels(minPrice,maxPrice,minRating,maxRating,stars,ac,lc,otfil);
    assertThat(categories).isNotNull();
  }
  @Test
  public void applyFilter02() throws Exception {
    int minPrice = 20;
    int maxPrice = 500;
    int minRating = 1;
    int maxRating = 5;
    int stars = 1;
    String[] ac = {"0","0","0"};
    String[] lc = {"0","0","0"};
    Boolean[] otfil ={true,true,false,true,false,true,false,false,true,false};
    Iterable<Categories> categories = this.hotelService.applyAllFiltersAndGetHotels(minPrice,maxPrice,minRating,maxRating,stars,ac,lc,otfil);
    assertThat(categories).isNotNull();
  }
  @Test
  public void applyFilter03() throws Exception {
    int minPrice = 20;
    int maxPrice = 500;
    int minRating = 1;
    int maxRating = 5;
    int stars = 1;
    String[] ac = {"0","0","0"};
    String[] lc = {"0","0","0"};
    Boolean[] otfil ={false,false,false,false,false,false,false,false,false,false};
    Iterable<Categories> categories = this.hotelService.applyAllFiltersAndGetHotels(minPrice,maxPrice,minRating,maxRating,stars,ac,lc,otfil);
    assertThat(categories).isNotNull();
  }
  @Test
  public void applyFilter04() throws Exception {
    int minPrice = 20;
    int maxPrice = 500;
    int minRating = 1;
    int maxRating = 5;
    int stars = 1;
    String[] ac = {"0","0","0"};
    String[] lc = {"Abbiadori","Adler","Athens"};
    Boolean[] otfil ={false,false,false,false,false,false,false,false,false,false};
    Iterable<Categories> categories = this.hotelService.applyAllFiltersAndGetHotels(minPrice,maxPrice,minRating,maxRating,stars,ac,lc,otfil);
    assertThat(categories).isNotNull();
  }
  @Test
  public void applyFilter05() throws Exception {
    int minPrice = 20;
    int maxPrice = 0;
    int minRating = 1;
    int maxRating = 5;
    int stars = 1;
    String[] ac = {"0","Running","0"};
    String[] lc = {"Abbiadori","Adler","Athens"};
    Boolean[] otfil ={true,true,false,true,false,true,false,false,true,false};
    Iterable<Categories> categories = this.hotelService.applyAllFiltersAndGetHotels(minPrice,maxPrice,minRating,maxRating,stars,ac,lc,otfil);
    assertThat(categories).isNotNull();
  }
  @Test
  public void applyFilter06() throws Exception {
    int minPrice = 20;
    int maxPrice = 0;
    int minRating = 1;
    int maxRating = 5;
    int stars = 1;
    String[] ac = {"0","Running","0"};
    String[] lc = {"Abbiadori","Adler","Athens"};
    Boolean[] otfil ={false,false,false,false,false,false,false,false,false,false};
    Iterable<Categories> categories = this.hotelService.applyAllFiltersAndGetHotels(minPrice,maxPrice,minRating,maxRating,stars,ac,lc,otfil);
    assertThat(categories).isNotNull();
  }
}
