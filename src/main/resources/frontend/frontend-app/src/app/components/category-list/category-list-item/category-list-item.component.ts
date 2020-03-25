import { Component, OnInit, Input } from '@angular/core';
import { Category } from '../category.model'
import { Hotel } from '../../hotel-list/hotel.model'

@Component({
  selector: 'app-category-list-item',
  templateUrl: './category-list-item.component.html',
  styleUrls: ['./category-list-item.component.css']
})
export class CategoryListItemComponent implements OnInit {
  @Input() category: Category;
  constructor() { }

  ngOnInit(): void {

  }

}
