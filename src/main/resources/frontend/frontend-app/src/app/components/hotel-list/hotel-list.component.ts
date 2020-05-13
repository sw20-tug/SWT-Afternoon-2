import {Component, OnInit, Input} from '@angular/core';
import {Hotel} from './hotel.model';
import {HttpClientService} from "../../service/http-client.service";
import {HotelService} from "../../service/hotel.service";

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  @Input()
  public hotels: Hotel[];
  @Input()
  public isAdmin: boolean;


  constructor(private HttpClientService: HttpClientService, private HotelService: HotelService) {
    this.HotelService.hotelList.subscribe( hotels => {
      var listOfHotels = new Array<Hotel>();
      if(hotels === null || typeof hotels === 'undefined')
        return;

      hotels.forEach(hotel => {
        listOfHotels.push(Hotel.MapHotel(hotel));
      });
      this.hotels = listOfHotels;
     /////// this.hotels = null;
      console.log('Hotleri', this.hotels);
    })
  }

  ngOnInit(): void {

  }

}
