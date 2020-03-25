import {Component, OnInit, Input} from '@angular/core';
import {Hotel} from './hotel.model';
import {HttpClientService} from "../../service/http-client.service";

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  @Input()
  public hotels: Hotel[];

  constructor(private HttpClientService: HttpClientService) {
  }

  ngOnInit(): void { /*
    this.HttpClientService.findAll().subscribe( hotel => {
      console.log("hotel is", hotel);
      this.hotels = hotel;
    }) */
  }

}
