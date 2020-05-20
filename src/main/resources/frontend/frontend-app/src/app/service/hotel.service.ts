import {Injectable} from '@angular/core';
import {Hotel} from "../components/hotel-list/hotel.model";
import {BehaviorSubject} from "rxjs";

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class HotelService {
  private hotels = new BehaviorSubject<Hotel[]>(null);
  private translate = new BehaviorSubject<boolean>(false);
  hotelList = this.hotels.asObservable();
  translateAsObs = this.translate.asObservable();

  constructor() {
  }

  updateHotelList(hotels: Hotel[]) {
    this.hotels.next(hotels);
  }

  translateHotels(translater: boolean) {
    this.translate.next(translater);
  }


}
