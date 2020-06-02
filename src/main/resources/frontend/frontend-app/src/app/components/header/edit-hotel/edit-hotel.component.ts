import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClientService} from "../../../service/http-client.service";
import {OtherFilters} from "../../filters/other-filter.model";
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {Hotel} from "../../hotel-list/hotel.model";
import {TranslateService} from "@ngx-translate/core";
import {HotelService} from "../../../service/hotel.service";
import {HotelDetailComponent} from "../../hotel-detail/hotel-detail.component";


@Component({
  selector: 'app-edit-hotel',
  templateUrl: './edit-hotel.component.html',
  styleUrls: ['./edit-hotel.component.css']
})
export class EditHotelComponent implements OnInit {

  public hotelForm: FormGroup;
  public uploadButtonClicked: boolean;
  private imageURL: string;
  public otherFilters: OtherFilters;
  private _dropdownSettings: IDropdownSettings;
  private dropdownSettingsCategory: IDropdownSettings;
  public activities: any;
  public categories: any;
  public hotel_id: number;
  public hotel_: Hotel;
  public hotelDetail: HotelDetailComponent;
  public userInput: boolean;


  public get dropdownSettings() {
    return this._dropdownSettings;
  }

  public get get_dropdownSettings() {
    return this.dropdownSettingsCategory;
  }
  public selectActivities() {
    this.selectedActivities.push(this.currentlySelectedActivities);
  }

  public selectCategories() {
    this.selectedCategories.push(this.currentlySelectedCategories);
  }

  public currentlySelectedActivities: any;
  public currentlySelectedCategories: any;

  public selectedCategories: any[] = [];
  public selectedActivities: any[] = [];



  constructor(private hotelService: HotelService, private HttpClientService: HttpClientService, private route: ActivatedRoute, private readonly router: Router,
              private readonly httpService: HttpClientService, private translateService: TranslateService) {


    this.hotel_id = this.router.getCurrentNavigation().extras.state.hotel_id;
    this.HttpClientService.getHotelById(this.hotel_id.toString()).subscribe(hotel => {
      this.hotel_ = hotel;

    });


    this.otherFilters = new OtherFilters();
    this._dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'name',
      selectAllText: this.translateService.instant('UNSELECT'),
      unSelectAllText: this.translateService.instant('UNSELECT'),
      itemsShowLimit: 14,
      allowSearchFilter: true
    };
    this.hotelService.translateAsObs.subscribe(trigger => {
      this._dropdownSettings = {
        singleSelection: false,
        idField: 'item_id',
        textField: 'name',
        selectAllText: this.translateService.instant('SELECT'),
        unSelectAllText: this.translateService.instant( 'UNSELECT'),
        itemsShowLimit: 14,
        allowSearchFilter: true
      };
    });

    this.dropdownSettingsCategory = {
      singleSelection: true,
      idField: 'item_id',
      textField: 'item_text',
      enableCheckAll: false,
      itemsShowLimit: 1,
      allowSearchFilter: false
    };
    this.activities = [Hotel.translateService.instant('FITNESS'), Hotel.translateService.instant('RUNNING'), Hotel.translateService.instant('OPEN BAR')];
    this.categories = ["Romantic", "Adventure", "Holiday", "Wellness", "Family", "Camping"];
  }

  ngOnInit() : void{

    this.hotelForm = new FormGroup({
      name: new FormControl('', [Validators.maxLength(60)]),
      descr: new FormControl('', [Validators.maxLength(100)]),
      category: new FormControl('', [ Validators.maxLength(20)]),
      activities: new FormControl('', [Validators.maxLength(100)]),
      stars: new FormControl('', [Validators.maxLength(10)]),
      price: new FormControl('', [Validators.maxLength(10)]),
      rating: new FormControl('', [Validators.maxLength(10)]),
      city: new FormControl('', [Validators.maxLength(50)]),
      activity: new FormControl('', [Validators.maxLength(50)]),
      otherFilters: new FormControl('', [Validators.maxLength(50)])
    });

  }


  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);

  }

  public onCancel = () => {
    console.log(this.hotel_.name)
    this.router.navigateByUrl('');
  };

  public checkUserInput ()
  {
    if(this.hotelForm.get('name').value == '' || this.hotelForm.get('descr').value == ''
      || this.hotelForm.get('price').value == null || this.hotelForm.get('rating').value == null
      || this.hotelForm.get('stars').value == null || this.hotelForm.get('city').value == ''
      || this.selectedCategories == null || this.selectedActivities == null
      || this.currentlySelectedCategories == null || this.currentlySelectedActivities == null)
      return false;
    else
      return true;
  }


  public editHotel() {

    let allFiltersIntoList = [this.otherFilters.parkingFilter === undefined ? false : true, this.otherFilters.restaurantFilter  === undefined ? false : true,
      this.otherFilters.petsAllowedFilter  === undefined ? false : true, this.otherFilters.nonsmokingRoomsFilter  === undefined ? false : true,
      this.otherFilters.swimmingPoolFilter  === undefined ? false : true, this.otherFilters.beachfrontFilter  === undefined ? false : true,
      this.otherFilters.airConditioningFilter  === undefined ? false : true, this.otherFilters.freeWifiFilter  === undefined ? false : true,
      this.otherFilters.saunaFilter  === undefined ? false : true, this.otherFilters.fitnessFilter  === undefined ? false : true];

    this.httpService.editHotel(this.hotelForm.get('name').value, this.hotelForm.get('descr').value,
      this.currentlySelectedCategories, this.hotelForm.get('price').value, this.hotelForm.get('rating').value,
      this.hotelForm.get('stars').value, this.hotelForm.get('city').value,  this.currentlySelectedActivities, allFiltersIntoList, this.hotel_.id).subscribe(response => {
          this.router.navigateByUrl('');
    });
  }
}
