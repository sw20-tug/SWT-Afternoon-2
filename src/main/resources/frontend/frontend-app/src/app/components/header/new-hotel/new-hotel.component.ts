import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UploadService} from "../../../service/upload.service";
import {catchError, map} from "rxjs/operators";
import {HttpErrorResponse, HttpEventType} from "@angular/common/http";
import {of} from "rxjs";
import {HttpClientService} from "../../../service/http-client.service";
import {OtherFilters} from "../../filters/other-filter.model";
import {FiltersModel} from "../../filters/filters.model";
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {Hotel} from "../../hotel-list/hotel.model";
import {TranslateService} from "@ngx-translate/core";
import {HotelService} from "../../../service/hotel.service";

export interface OwnerForCreation {
  name: string;
  dateOfBirth: Date;
  address: string;
}

@Component({
  selector: 'app-new-hotel',
  templateUrl: './new-hotel.component.html',
  styleUrls: ['./new-hotel.component.css']
})
export class NewHotelComponent implements OnInit {

  public hotelForm: FormGroup;
  public starsFilter: number = null;
  @ViewChild("fileUpload", {static: false}) fileUpload: ElementRef;files  = [];
  public uploadButtonClicked: boolean;
  private imageURL: string;
  public otherFilters: OtherFilters;
  private _dropdownSettings: IDropdownSettings;
  private dropdownSettingsCategory: IDropdownSettings;
  public activities: any;
  public categories: any;



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



  constructor(private readonly router: Router, private readonly uploadService: UploadService,
              private readonly httpService: HttpClientService, private translateService: TranslateService,
              private HotelService: HotelService) {
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
    this.HotelService.translateAsObs.subscribe(trigger => {
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

  ngOnInit() {
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

  uploadFile(file) {
    const formData = new FormData();
    formData.append('file', file.data);
    file.inProgress = true;
    this.uploadService.upload(formData).pipe(
      map(event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            file.progress = Math.round(event.loaded * 100 / event.total);
            break;
          case HttpEventType.Response:
            return event;
        }
      }),
      catchError((error: HttpErrorResponse) => {
        file.inProgress = false;
        return of(`${file.data.name} upload failed.`);
      })).subscribe((event: any) => {
      if (typeof (event) === 'object') {
        this.uploadButtonClicked = true;
        this.imageURL = event.body.link;
      }
    });
  }

  onClick() {
    const fileUpload = this.fileUpload.nativeElement;fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++)
      {
        const file = fileUpload.files[index];
        this.files.push({ data: file, inProgress: false, progress: 0});
      }
      this.uploadFiles();
    };
    fileUpload.click();
  }

  onItemSelect(item: any) {
    console.log('test')
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log('test2');
    console.log(items);

  }

  public onCancel = () => {
    this.router.navigateByUrl('');
  };

  private uploadFiles() {
    this.fileUpload.nativeElement.value = '';
    this.files.forEach(file => {
      this.uploadFile(file);
    });
  }

  public insertNewHotel() {

    let allFiltersIntoList = [this.otherFilters.parkingFilter === undefined ? false : true, this.otherFilters.restaurantFilter  === undefined ? false : true,
      this.otherFilters.petsAllowedFilter  === undefined ? false : true, this.otherFilters.nonsmokingRoomsFilter  === undefined ? false : true,
      this.otherFilters.swimmingPoolFilter  === undefined ? false : true, this.otherFilters.beachfrontFilter  === undefined ? false : true,
      this.otherFilters.airConditioningFilter  === undefined ? false : true, this.otherFilters.freeWifiFilter  === undefined ? false : true,
      this.otherFilters.saunaFilter  === undefined ? false : true, this.otherFilters.fitnessFilter  === undefined ? false : true];

    this.httpService.insertNewHotel(this.hotelForm.get('name').value, this.hotelForm.get('descr').value,
      this.currentlySelectedCategories, this.hotelForm.get('price').value, this.hotelForm.get('rating').value,
      this.hotelForm.get('stars').value, this.hotelForm.get('city').value,  this.currentlySelectedActivities, allFiltersIntoList, this.imageURL).subscribe(response => {
 
    });
  }
}
