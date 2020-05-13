import {Component, OnInit, Input} from '@angular/core';
import {Category} from '../category.model'
import {Hotel} from '../../hotel-list/hotel.model'
import {HttpClientService} from '../../../service/http-client.service';
import {HotelService} from '../../../service/hotel.service';
import {SortByPipe} from "../../../sort-by-pipe.pipe";

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

  sortOptions = [
    {id: 1, name: 'Price Lowest'},
    {id: 2, name: 'Price Highest'},
    {id: 3, name: 'Rating Lowest'},
    {id: 4, name: 'Rating Highest'},
    {id: 5, name: 'Stars Lowest'},
    {id: 6, name: 'Stars Highest'}
  ];

  selectedSortOption: any;

  ngOnInit(): void {
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
