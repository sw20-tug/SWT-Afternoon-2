import { HotelDetails } from './hotel-details.model';

export class Hotel {
  public name: string;
  public description: string;
  public price: string;
  public imagePath: string;
  public stars: number;
  public rate: number;
  public city: string;
  public hotelDetails: HotelDetails;

  constructor(name: string, description: string, price: string, imagePath: string, stars: number, rate: number, city: string) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.imagePath = imagePath;
    this.stars = stars;
    this.rate = rate;
    this.city = city;
  }

  getRating() {
    if (this.rate <= 10 && this.rate >= 8) {
      return "Excellent";
    } else if (this.rate < 8 && this.rate >= 5) {
      return "Good"
    } else return "Poor";
  }

  static MapHotel(hotel: any) {
    var mappedHotel = new Hotel(hotel.name, hotel.description, hotel.price, hotel.imagePath, hotel.stars, hotel.rate, hotel.city);
    var hotelDetails = new HotelDetails(hotel.activityGym, hotel.activityOpenBar, hotel.activityRunning, hotel.airConditioning,
                                        hotel.beachFront, hotel.fitness, hotel.freeWiFi, hotel.restaurant, hotel.swimmingPool,
                                        hotel.smoking, hotel.sauna, hotel.parking, hotel.pets);
    mappedHotel.hotelDetails = hotelDetails;
    return mappedHotel;
  }
}
