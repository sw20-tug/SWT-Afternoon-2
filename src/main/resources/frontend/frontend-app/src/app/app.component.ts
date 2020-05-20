import { Component } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {HotelService} from "./service/hotel.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private  translate: TranslateService, private hotelService: HotelService) {
    this.translate.setDefaultLang('en');
    this.translate.use('en');
    this.hotelService.translateHotels(true);
  }
}

