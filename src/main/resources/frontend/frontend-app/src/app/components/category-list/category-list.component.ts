import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Category } from './category.model'
import { Hotel } from '../hotel-list/hotel.model';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  @Input()
  public categories: Category[];
  @Input()
  public searchText: string;
  @Input()
  public isAdmin: boolean = false;
  @Output()
  public clearSearchEvent = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {

  }

  filteredCategories(filteredCategoryList: any[]){
    this.categories = filteredCategoryList;
    console.log('cat', this.categories);
  }

  onCategoryClick(index: number){

  }

  clearSearch(){
    this.searchText = "";
    this.clearSearchEvent.emit(true);
  }
}
