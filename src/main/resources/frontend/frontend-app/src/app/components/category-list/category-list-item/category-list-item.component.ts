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
    {id: 1, name: 'Price Lowest'},
    {id: 2, name: 'Price Highest'},
    {id: 3, name: 'Rating Lowest'},
    {id: 4, name: 'Rating Highest'},
    {id: 5, name: 'Stars Lowest'},
    {id: 6, name: 'Stars Highest'}
  ];

  selectedSortOption: any;

  ngOnInit(): void {

  }

  constructor(private httpClientService: HttpClientService, private hotelService: HotelService) {}

  sortListItems($event) {
  console.log("$event is ? ", $event.id);
   this.httpClientService.sortByCriteria($event.id).subscribe(hotels => {
     console.log("Hotels?", hotels);
     this.category.hotels = hotels;
   });

    // here should be an API call for retrieving sorted hotels
    // we should send this.selectedSortOption (1,2, or 3) and this.category.hotels
    // and recieve sorted hotels for that sort option

    // var sortedHotels = ...
    // this.category.hotels = sortedHotels;
  }

}
