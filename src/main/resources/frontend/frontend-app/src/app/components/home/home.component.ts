import { Component, OnInit } from '@angular/core';
import { Category } from '../category-list/category.model';
import { Hotel } from '../hotel-list/hotel.model';
import {HttpClientService} from "../../service/http-client.service";

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

  constructor(private HttpClientService: HttpClientService) { }

  ngOnInit(): void {
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
    });
  }

  ngOnChanges(text: any){
    console.log(text);
  }

  searchHeaderEvent($event) {
    this.searchText = $event;
    if(this.searchText ==='' || typeof this.searchText === 'undefined'){
      this.categories = this.allCategories;
      return;
    }

    this.categories = this.allCategories.filter(x => x.hotels !== null && typeof x.hotels !== 'undefined');
    this.categories = this.categories.filter(x => {
      x.hotels = x.hotels.filter(y => y.name.toLowerCase().includes(this.searchText));
      return x.hotels.length > 0;
    });
    this.initializeAllCategories();
  }

  clearSearchEvent($event){
    this.searchText = "";
    this.initializeAllCategories();
  }
}
