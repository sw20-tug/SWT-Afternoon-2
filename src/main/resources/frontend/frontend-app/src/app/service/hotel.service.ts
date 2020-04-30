import {Injectable} from '@angular/core';
import {Hotel} from "../components/hotel-list/hotel.model";
import {BehaviorSubject} from "rxjs";

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class HotelService {
  private hotels = new BehaviorSubject<Hotel[]>(null);
  hotelList = this.hotels.asObservable();

  constructor() {
  }

  updateHotelList(hotels: Hotel[]) {
    this.hotels.next(hotels);
  }


}
