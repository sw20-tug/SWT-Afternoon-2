import {Component, Input} from '@angular/core';
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {HttpClientService} from "../../service/http-client.service";
import {HotelListComponent} from "../hotel-list/hotel-list.component";
import {HotelService} from "../../service/hotel.service";
import {Hotel} from "../hotel-list/hotel.model";
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css'],
  providers: [NgbRatingConfig]
})

export class FiltersComponent {
  private selectedItems: Map<string, Array<any>>;
  private _dropdownSettings: IDropdownSettings;
  public activities: any;
  public currentlySelectedActivities: any;
  public selectedActivities: any[];
  public locations: any;
  public currentlySelectedLocations: any;
  public selectedLocations: any[];
  public ratingFilterValue: number = 0;

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
      console.log(hotels)
      //this.HotelService.updateHotelList(hotels);
    })
  }

  constructor(private HttpClientService: HttpClientService, private HotelService: HotelService) {

    this._dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'Unselect All',
      itemsShowLimit: 14,
      allowSearchFilter: true
    };
    this.activities = ["Gym", "Running", "Open bar"];
    this.locations = ["Graz", "Vienna", "Salzburg"];
    this.selectedActivities = [];

  }

  public selectActivities() {
    this.selectedActivities.push(this.currentlySelectedActivities);
  }

  public sortHotelsByActivity() {
    this.HttpClientService.getHotelWithActivities(this.selectedActivities).subscribe(hotels => {
      this.HotelService.updateHotelList(hotels);
    })
  }

  public selectLocations() {
    this.selectedLocations.push(this.currentlySelectedLocations);
  }
}

