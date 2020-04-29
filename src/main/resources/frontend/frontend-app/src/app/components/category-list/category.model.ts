import { Hotel } from '../hotel-list/hotel.model';

export class Category {
  public name: string;
  public hotels: Hotel[];

  constructor(name: string, hotels: Hotel[]) {
    this.name = name;
    this.hotels = hotels;
  }

  static MapCategory(category: any) {
      var mappedCategory = new Category(category.categoryName, null);
      var listOfHotels = new Array<Hotel>();
      category.hotelsInsideCategory.forEach(hotel => {
        listOfHotels.push(Hotel.MapHotel(hotel));
      });
      mappedCategory.hotels = listOfHotels;
      return mappedCategory;
    }
}
