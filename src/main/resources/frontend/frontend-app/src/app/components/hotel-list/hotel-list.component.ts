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


  constructor(private HttpClientService: HttpClientService, private HotelService: HotelService) {
    this.HotelService.hotelList.subscribe( hotels => {
      this.hotels = hotels;
    })
/*
    this.HttpClientService.findAll().subscribe( hotel => {
      console.log("hotel is", hotel);
      this.hotels = hotel;
    }) */
  }

  ngOnInit(): void {

  }

}
