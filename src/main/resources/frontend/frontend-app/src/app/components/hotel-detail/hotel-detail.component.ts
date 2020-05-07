import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpClientService} from "../../service/http-client.service";
import { Hotel } from '../hotel-list/hotel.model';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrls: ['./hotel-detail.component.css']
})
export class HotelDetailComponent implements OnInit {
  public hotel: Hotel;
  constructor(private route: ActivatedRoute, private HttpClientService: HttpClientService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.HttpClientService.getHotelById(params.id).subscribe(hotel => {
        this.hotel = Hotel.MapHotel(hotel);
        console.log(this.hotel);
      })
    })
  }
}
