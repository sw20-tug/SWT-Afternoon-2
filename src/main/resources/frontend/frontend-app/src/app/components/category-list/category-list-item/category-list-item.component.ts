import { Component, OnInit, Input } from '@angular/core';
import { Category } from '../category.model'
import { Hotel } from '../../hotel-list/hotel.model'
import { HttpClientService } from '../../../service/http-client.service';
import { HotelService } from '../../../service/hotel.service';

@Component({
  selector: 'app-category-list-item',
  templateUrl: './category-list-item.component.html',
  styleUrls: ['./category-list-item.component.css']
})
export class CategoryListItemComponent implements OnInit {
  @Input() category: Category;

  sortOptions = [
    {id: 1, name: 'Price'},
    {id: 2, name: 'Rating'},
    {id: 3, name: 'Stars'}
  ];

  selectedSortOption: any;

  ngOnInit(): void {

  }

  constructor(private httpClientService: HttpClientService, private hotelService: HotelService) {}

  sortListItems() {

    // here should be an API call for retrieving sorted hotels
    // we should send this.selectedSortOption (1,2, or 3) and this.category.hotels
    // and recieve sorted hotels for that sort option

    // var sortedHotels = ...
    // this.category.hotels = sortedHotels;
  }

}
