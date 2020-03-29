import {Component, Input} from '@angular/core';
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {HttpClientService} from "../../service/http-client.service";
import {HotelListComponent} from "../hotel-list/hotel-list.component";
import {HotelService} from "../../service/hotel.service";
import {Hotel} from "../hotel-list/hotel.model";

@Component({
  selector: 'buttons-checkbox',
  templateUrl: './button-checkbox.component.html',
  styleUrls: ['./button-checkbox.component.css']
})
export class NgbdButtonsCheckbox {
  private selectedItems: Map<string, Array<any>>;
  private _dropdownSettings: IDropdownSettings;
  public activities: any;
  public currentlySelected: any;
  public selectedActivities: any[];

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
    this.HttpClientService.getHotelWithActivities(this.selectedActivities).subscribe(hotels => {
      this.HotelService.updateHotelList(hotels);
    })
  }

  constructor(private HttpClientService: HttpClientService, private HotelService: HotelService) {

    this._dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 14,
      allowSearchFilter: true
    };
    this.activities = ["Gym", "Running", "Open bar"];
    this.selectedActivities = [];

  }

  public selectActivities() {
    this.selectedActivities.push(this.currentlySelected);
  }

  public sortHotelsByActivity() {
    this.HttpClientService.getHotelWithActivities(this.selectedActivities).subscribe(hotels => {
      this.HotelService.updateHotelList(hotels);
    })
  }
}

