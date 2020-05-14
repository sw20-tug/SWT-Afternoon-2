import { FacilityItem } from '../hotel-detail/facility-item.model';
import { Facilities } from '../hotel-detail/facilities.enum';
import { RatingComment } from '../hotel-detail/rating-comment.model';

export class Hotel {
  public id: number;
  public name: string;
  public description: string;
  public price: string;
  public imagePath: string;
  public stars: number;
  public rate: number;
  public city: string;
  public facilitiesList: FacilityItem[];
  public ratingComments: RatingComment[];

  constructor(id: number, name: string, description: string, price: string, imagePath: string, stars: number, rate: number, city: string) {
    this.id = id;
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
    var mappedHotel = new Hotel(hotel.id, hotel.name, hotel.description, hotel.price, hotel.imagePath, hotel.stars, hotel.rate, hotel.city);
    mappedHotel.facilitiesList = [
      new FacilityItem("Gym", hotel.activityGym, Facilities["Gym"]), new FacilityItem("OpenBar", hotel.activityOpenBar, Facilities["OpenBar"]), new FacilityItem("Running", hotel.activityRunning, Facilities["Running"]),
      new FacilityItem("AirConditioning", hotel.airConditioning, Facilities["AirConditioning"]), new FacilityItem("BeachFront", hotel.beachFront, Facilities["BeachFront"]), new FacilityItem("Fitness", hotel.fitness, Facilities["Fitness"]),
      new FacilityItem("FreeWiFi", hotel.freeWiFi, Facilities["FreeWiFi"]), new FacilityItem("Restaurant", hotel.restaurant, Facilities["Restaurant"]), new FacilityItem("SwimmingPool", hotel.swimmingPool, Facilities["SwimmingPool"]),
      new FacilityItem("Smoking", hotel.smoking, Facilities["Smoking"]), new FacilityItem("Sauna", hotel.sauna, Facilities["Sauna"]), new FacilityItem("Parking", hotel.parking, Facilities["Parking"]), new FacilityItem("Pets", hotel.pets, Facilities["Pets"])
    ]; 
    return mappedHotel;
  }
}
