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
  public initialCategories: Category[];
  @Input()
  public searchText: string;
  @Input()
  public isAdmin: boolean = false;
  @Output()
  public clearSearchEvent = new EventEmitter<boolean>();
  selectedCategoryFilters: Array<number>;

  constructor() { }

  ngOnInit(): void {
    this.selectedCategoryFilters = new Array<number>();
  }

  filteredCategories(filteredCategoryList: any[]){
    this.categories = filteredCategoryList;
    this.initialCategories = filteredCategoryList;
  }

  onCategoryClick(index: number){
    if(!this.selectedCategoryFilters.includes(index)) {
      this.selectedCategoryFilters.push(index);
    }
    else {
      var indexOfSelectedCategory =  this.selectedCategoryFilters.indexOf(index);
      this.selectedCategoryFilters.splice(indexOfSelectedCategory, 1);
    }
    if(!this.selectedCategoryFilters.length){
      this.categories = this.initialCategories;
    }
    else {
      this.categories = this.initialCategories.filter(x => this.selectedCategoryFilters.includes(this.initialCategories.indexOf(x)));
    }
  }

  clearSearch(){
    this.searchText = "";
    this.clearSearchEvent.emit(true);
    this.selectedCategoryFilters = new Array<number>();
  }
}
