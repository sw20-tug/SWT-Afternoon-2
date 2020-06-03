import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {HttpClientService} from "../../service/http-client.service";
import {HotelService} from "../../service/hotel.service";
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { Category } from '../category-list/category.model';
import { OtherFilters } from './other-filter.model';
import { FiltersModel } from './filters.model';
import {TranslateService} from "@ngx-translate/core";
import {InjectorService} from "../../service/injector.service";
import {Hotel} from "../hotel-list/hotel.model";
import {CookieService} from "ngx-cookie-service";


@Component({
  selector: 'filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css'],
  providers: [NgbRatingConfig]
})

export class FiltersComponent {
  @Output()
  filteredCategories: EventEmitter<Category[]> = new EventEmitter();
  @Input()
  public isAdmin: boolean = this.cookieService.get("isAdmin") === "true" ? true : false;
  private selectedItems: Map<string, Array<any>>;
  private _dropdownSettings: IDropdownSettings;
  public activities: any;
  public locations: any;
  public selectedActivities: any[] = [];

  public minPrice: number;
  public maxPrice: number;
  public minRating: number;
  public maxRating: number;
  public starsFilter: number = 0;
  public currentlySelectedActivities: any;
  public currentlySelectedLocations: any;
  public otherFilters: OtherFilters;
  public get dropdownSettings() {
    return this._dropdownSettings;
  }

  @Input() value;
  @Input() enabled = true;
  model = {
    left: true,
    middle: false,
    right: false
  };

  sortHotelsByPrice($event) {
    this.HttpClientService.getHotelWithinPriceRange(this.value).subscribe(hotels => {

      //this.HotelService.updateHotelList(hotels);
    })
  }

  constructor(private HttpClientService: HttpClientService, private HotelService: HotelService,
              private readonly translateService: TranslateService, private cookieService: CookieService) {

    this.instantiateDropdownSettings();
    this.HotelService.translateAsObs.subscribe(trigger => {
      if(trigger) {
        this.activities = [this.translateService.instant('FITNESS'), this.translateService.instant('RUNNING'), this.translateService.instant('OPEN BAR')];
      } else {
        this.activities = [this.translateService.instant('Fitness'), this.translateService.instant('Running'), this.translateService.instant('Open Bar')];

      }
    });
    this.locations = ["Graz", "Vienna", "Salzburg", "Paris", "Dubai", "Munich", "Berlin", "Stuttgart", "Hamburg", "Frankfurt", "Madrid", "Barcelona", "Rome", "Venice", "Milan", "London", "Amsterdam", "Florence", "Prague", "Istanbul", "Sarajevo", "Zagreb", "Athens", "Maldives", "Phuket", "Ljubljana", "Belgrade", "Budapest"];
    this.selectedActivities = [];
    this.otherFilters = new OtherFilters();
  }


  private instantiateDropdownSettings() {


    this.HotelService.translateAsObs.subscribe(trigger => {
      if(trigger) {
        this._dropdownSettings = {
          singleSelection: false,
          idField: 'item_id',
          textField: 'name',
          selectAllText: this.translateService.instant('SELECT'),
          unSelectAllText: this.translateService.instant('UNSELECT'),
          itemsShowLimit: 14,
          allowSearchFilter: true
        };
      } else {
        this._dropdownSettings = {
          singleSelection: false,
          idField: 'item_id',
          textField: 'name',
          selectAllText: 'Select All',
          unSelectAllText: 'Unselect All',
          itemsShowLimit: 14,
          allowSearchFilter: true
        };
      }
      console.log('Trigger?', trigger);

    });
  }

  public selectActivities() {

    this.selectedActivities.push(this.currentlySelectedActivities);
  }

  public applyAllFilters() {
    // object filled with all filters from frontend
    var filterModel = new FiltersModel(
      this.minPrice,
      this.maxPrice,
      this.minRating,
      this.maxRating,
      this.starsFilter,
      this.currentlySelectedActivities,
      this.currentlySelectedLocations,
      this.otherFilters);

    // create list of booleans in order to send it into controller
    // if not true, filters can be undefined OR false/unchecked
    var allFiltersIntoList = [this.otherFilters.parkingFilter === true ? true : false, this.otherFilters.restaurantFilter  === true ? true : false,
      this.otherFilters.petsAllowedFilter  === true ? true : false, this.otherFilters.nonsmokingRoomsFilter  === true ? true : false,
      this.otherFilters.swimmingPoolFilter  === true ? true : false, this.otherFilters.beachfrontFilter  === true ? true : false,
      this.otherFilters.airConditioningFilter  === true ? true : false, this.otherFilters.freeWifiFilter  === true ? true : false,
      this.otherFilters.saunaFilter  === true ? true : false, this.otherFilters.fitnessFilter  === true ? true : false]

    var areFiltersReset = allFiltersIntoList.every(x => !x);

    this.HttpClientService.getFilteredHotels(this.minPrice, this.maxPrice, this.minRating, this.maxRating,
      this.starsFilter,
      this.currentlySelectedActivities,
      this.currentlySelectedLocations, allFiltersIntoList).subscribe( categories => {
        var listOfCategories = new Array<Category>();
        categories.forEach(category => {
          var newCategory = Category.MapCategory(category);
          if(newCategory.hotels !== null && newCategory.hotels.length > 0){
            // reset to initial display of categories
            if(areFiltersReset) {
              newCategory.hotels = newCategory.hotels.slice(0,5);
            }
            listOfCategories.push(newCategory);
          }
        });
        this.filteredCategories.emit(listOfCategories);
    });
  }

}

