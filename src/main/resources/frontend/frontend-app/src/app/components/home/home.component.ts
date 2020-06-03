import { Component, OnInit } from '@angular/core';
import { Category } from '../category-list/category.model';
import { Hotel } from '../hotel-list/hotel.model';
import {HttpClientService} from "../../service/http-client.service";
import {TranslateService} from "@ngx-translate/core";
import {HotelService} from "../../service/hotel.service";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'Hotels';
  public allCategories: Category[] = [];
  public categories: Category[] = [];
  public searchText: string = '';
  public isAdmin: boolean  = this.cookieService.get("isAdmin") === "true";

  constructor(private HttpClientService: HttpClientService, public translate: TranslateService, private hotelService: HotelService, private cookieService: CookieService) {

  }

  ngOnInit(): void {
    //this.translate.setDefaultLang('bs');
    //this.translate.use('bs');
    this.initializeAllCategories();
  }

  initializeAllCategories(){
    this.HttpClientService.getCategories().subscribe(categories => {
      var listOfCategories = new Array<Category>();
      categories.forEach(category => {
         listOfCategories.push(Category.MapCategory(category));
      });
      this.allCategories = listOfCategories;
      this.categories = this.allCategories;
      this.hotelService.translateAsObs.subscribe(trigger => {
        this.categories[0].name = this.translate.instant('ROMANTIC');
        this.categories[1].name = this.translate.instant('ADVENTURE');
        this.categories[2].name = this.translate.instant('HOLIDAY');
        this.categories[4].name = this.translate.instant('FAMILY');
        this.categories[5].name = this.translate.instant('CAMPING');
      });

    });
  }

  ngOnChanges(text: any){

  }

  searchHeaderEvent($event) {
    this.searchText = $event;
    if(this.searchText ==='' || typeof this.searchText === 'undefined'){
      this.initializeAllCategories();
      this.categories = this.allCategories;
      return;
    }

    this.categories = this.allCategories.filter(x => x.hotels !== null && typeof x.hotels !== 'undefined');
    this.categories = this.categories.filter(x => {
      x.hotels = x.hotels.filter(y => y.name.toLowerCase().includes(this.searchText));
      return x.hotels.length > 0;
    });
  }
  loginHeaderEvent($event) {
    this.isAdmin = $event;
  }

  clearSearchEvent($event){
    this.searchText = "";
    this.initializeAllCategories();
  }
}
