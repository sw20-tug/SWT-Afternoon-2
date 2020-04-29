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
  private categoryID: number;

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
    if(this.category.name == 'Romantic')
    {
      this.categoryID = 1;
    }
    if(this.category.name == 'Adventure')
    {
      this.categoryID = 2;
    }
    if(this.category.name == 'Holiday')
    {
      this.categoryID = 3;
    }
    if(this.category.name == 'Wellness')
    {
      this.categoryID = 4;
    }
    if(this.category.name == 'Family')
    {
      this.categoryID = 5;
    }
    if(this.category.name == 'Camping')
    {
      this.categoryID = 6;
    }
  }
  constructor(private httpClientService: HttpClientService, private hotelService: HotelService) {

  }

  sortListItems($event) {
    this.httpClientService.sortByCriteria(this.categoryID, $event.id).subscribe(hotels => {    
      var listOfHotels = new Array<Hotel>();
      hotels.forEach(hotel => {
       listOfHotels.push(Hotel.MapHotel(hotel));
      });
      this.category.hotels = listOfHotels;
   });

  }

}
