import { Hotel } from '../hotel-list/hotel.model';

export class Category {
  public name: string;
  public hotels: Hotel[];

  constructor(name: string, hotels: Hotel[]) {
    this.name = name;
    this.hotels = hotels;
  }
}
