import {Component, OnInit} from '@angular/core';
import {Hotel} from './hotel.model';
import {HttpClientService} from "../../service/http-client.service";

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  hotels: Hotel[];

  constructor(private HttpClientService: HttpClientService) {
  }

  ngOnInit(): void { /*
    this.HttpClientService.findAll().subscribe( hotel => {
      console.log("hotel is", hotel);
      this.hotels = hotel;
    }) */
    // mocked until DB is populated
    this.hotels = [new Hotel("Ibis Graz", "The Ibis Graz is conveniently located at Graz train station. The city center (Graz old town) is only a 5 min. journey from the hotel via the reasonably-priced trams and buses.", "100", "assets/img/hotel1.jpg", 4.0, 10.0, "Graz"),
      new Hotel("Mercure Graz City", "The exclusive 4-star Mercure Graz City Hotel is located in the center of Graz-the City of Design-near the old town, the farmers' market, the Schlossberg and the Graz conference center.", "200", "assets/img/hotel2.jpg", 3.75, 8.0, "Graz"),
      new Hotel("Hotel Gollner", "Set in a prime location of Graz, Hotel Gollner puts everything the city has to offer just outside your doorstep offering a variety of facilities and services.", "300", "assets/img/hotel3.jpg", 2.5, 1.0, "Graz")
    ];
  }

}
