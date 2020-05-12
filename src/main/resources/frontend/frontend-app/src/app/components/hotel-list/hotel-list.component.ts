import {Component, OnInit, Input} from '@angular/core';
import {Hotel} from './hotel.model';
import {HttpClientService} from "../../service/http-client.service";
import {HotelService} from "../../service/hotel.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  @Input()
  public hotels: Hotel[];


  constructor(private HttpClientService: HttpClientService, private HotelService: HotelService, private router: Router) {
    this.HotelService.hotelList.subscribe( hotels => {
      var listOfHotels = new Array<Hotel>();
      if(hotels === null || typeof hotels === 'undefined')
        return;

      hotels.forEach(hotel => {
        listOfHotels.push(Hotel.MapHotel(hotel));
      });
      this.hotels = listOfHotels;
     /////// this.hotels = null;
      console.log('Hotels', this.hotels);
    })
  }

  ngOnInit(): void {

  }  

  openHotelDetailPage(id: number){
    this.router.navigate(['/hotel-detail', id]);
  }
}
