import {Component, OnInit, Input} from '@angular/core';
import {Category} from '../category.model'
import {Hotel} from '../../hotel-list/hotel.model'
import {HttpClientService} from '../../../service/http-client.service';
import {HotelService} from '../../../service/hotel.service';
import {SortByPipe} from "../../../sort-by-pipe.pipe";
import {InjectorService} from "../../../service/injector.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-category-list-item',
  templateUrl: './category-list-item.component.html',
  styleUrls: ['./category-list-item.component.css']
})
export class CategoryListItemComponent implements OnInit {
  @Input() category: Category;
  @Input() isAdmin: boolean;
  private categoryID: number;
  private temp_hotels: Hotel[];
  private translateService;
  public sortOptions;


  selectedSortOption: any;

  ngOnInit(): void {
    this.translateService = InjectorService.injector.get(TranslateService);
    this.sortOptions = [
      {id: 1, name: this.translateService.instant('PRICE LOWEST')},
      {id: 2, name: this.translateService.instant('PRICE HIGHEST')},
      {id: 3, name: this.translateService.instant('RATING LOWEST')},
      {id: 4, name: this.translateService.instant('RATING HIGHEST')},
      {id: 5, name: this.translateService.instant('STARS LOWEST')},
      {id: 6, name: this.translateService.instant('STARS HIGHEST')}
    ];
    this.hotelService.translateAsObs.subscribe(trigger => {
      this.sortOptions = [
        {id: 1, name: this.translateService.instant('PRICE LOWEST')},
        {id: 2, name: this.translateService.instant('PRICE HIGHEST')},
        {id: 3, name: this.translateService.instant('RATING LOWEST')},
        {id: 4, name: this.translateService.instant('RATING HIGHEST')},
        {id: 5, name: this.translateService.instant('STARS LOWEST')},
        {id: 6, name: this.translateService.instant('STARS HIGHEST')}
      ];
    });

    this.determineCategoryID();
  }

  private determineCategoryID() {
    if (this.category != null && this.category.name == 'Romantic') {
      this.categoryID = 1;
    }
    if (this.category != null && this.category.name == 'Adventure') {
      this.categoryID = 2;
    }
    if (this.category != null && this.category.name == 'Holiday') {
      this.categoryID = 3;
    }
    if (this.category != null && this.category.name == 'Wellness') {
      this.categoryID = 4;
    }
    if (this.category != null && this.category.name == 'Family') {
      this.categoryID = 5;
    }
    if (this.category != null && this.category.name == 'Camping') {
      this.categoryID = 6;
    }
  }

  constructor(private httpClientService: HttpClientService, private hotelService: HotelService,
              private readonly sortByPipe: SortByPipe) {
    console.log('??????????????', this.category);
  }

  sortListItems($event) {
    this.temp_hotels = this.category.hotels;
    if (!$event) {
      this.category.hotels = this.temp_hotels;
      return;
    }
    if ($event.id == 1)
      this.category.hotels = this.sortByPipe.transform(this.category.hotels, 'asc', 'price');
    if ($event.id == 2)
      this.category.hotels = this.sortByPipe.transform(this.category.hotels, 'desc', 'price');
    if ($event.id == 3)
      this.category.hotels = this.sortByPipe.transform(this.category.hotels, 'asc', 'rate');
    if ($event.id == 4)
      this.category.hotels = this.sortByPipe.transform(this.category.hotels, 'desc', 'rate');
    if ($event.id == 5)
      this.category.hotels = this.sortByPipe.transform(this.category.hotels, 'asc', 'stars');
    if ($event.id == 6)
      this.category.hotels = this.sortByPipe.transform(this.category.hotels, 'desc', 'stars');
  }

}
