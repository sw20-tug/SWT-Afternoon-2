import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltersComponent } from './filters.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
import { MatButton } from '@angular/material/button';

describe('FiltersComponent', () => {
  let component: FiltersComponent;
  let fixture: ComponentFixture<FiltersComponent>;
  let de : DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltersComponent ],
      providers: [	
        HttpClient,	
        HttpHandler	
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('render all names and components', async(() => {
    const pricePerNightElement: HTMLElement = fixture.debugElement.query(By.css('#price_per_night')).nativeElement;
    expect(pricePerNightElement.innerHTML).toMatch('Price per night');
    const minpricePerNightElement: HTMLElement = fixture.debugElement.query(By.css('#min_price')).nativeElement;
    expect(minpricePerNightElement.innerHTML).toMatch('min');
    const maxpricePerNightElement: HTMLElement = fixture.debugElement.query(By.css('#max_price')).nativeElement;
    expect(maxpricePerNightElement.innerHTML).toMatch('max');
    const customerRatingElement: HTMLElement = fixture.debugElement.query(By.css('#customer_rating')).nativeElement;
    expect(customerRatingElement.innerHTML).toMatch('Customer rating');
    const mincustomerRatingElement: HTMLElement = fixture.debugElement.query(By.css('#min_rating')).nativeElement;
    expect(mincustomerRatingElement.innerHTML).toMatch('min');
    const maxcustomerRatingElement: HTMLElement = fixture.debugElement.query(By.css('#max_rating')).nativeElement;
    expect(maxcustomerRatingElement.innerHTML).toMatch('max');
    const starsElement: HTMLElement = fixture.debugElement.query(By.css('#stars')).nativeElement;
    expect(starsElement.innerHTML).toMatch('Stars');
    const activitiesElement: HTMLElement = fixture.debugElement.query(By.css('#activities')).nativeElement;
    expect(activitiesElement.innerHTML).toMatch('Activities');
    const locationsElement: HTMLElement = fixture.debugElement.query(By.css('#locations')).nativeElement;
    expect(locationsElement.innerHTML).toMatch('Locations');
    const otherFiltersElement: HTMLElement = fixture.debugElement.query(By.css('#other_filters')).nativeElement;
    expect(otherFiltersElement.innerHTML).toMatch('Other filters');
    const parkingElement: HTMLElement = fixture.debugElement.query(By.css('#parking')).nativeElement;
    expect(parkingElement.innerHTML).toMatch('Parking');
    const restaurantElement: HTMLElement = fixture.debugElement.query(By.css('#restaurant')).nativeElement;
    expect(restaurantElement.innerHTML).toMatch('Restaurant');
    const petsAllowedElement: HTMLElement = fixture.debugElement.query(By.css('#pets_allowed')).nativeElement;
    expect(petsAllowedElement.innerHTML).toMatch('Pets allowed');
    const nonSmokingRoomsElement: HTMLElement = fixture.debugElement.query(By.css('#non_smoking_rooms')).nativeElement;
    expect(nonSmokingRoomsElement.innerHTML).toMatch('Non-smoking rooms');
    const swimmingPoolElement: HTMLElement = fixture.debugElement.query(By.css('#swimming_pool')).nativeElement;
    expect(swimmingPoolElement.innerHTML).toMatch('Swimming pool');
    const beachfrontElement: HTMLElement = fixture.debugElement.query(By.css('#beachfront')).nativeElement;
    expect(beachfrontElement.innerHTML).toMatch('Beachfront');
    const airConditioningElement: HTMLElement = fixture.debugElement.query(By.css('#air_conditioning')).nativeElement;
    expect(airConditioningElement.innerHTML).toMatch('Air conditioning');
    const freeWifiElement: HTMLElement = fixture.debugElement.query(By.css('#free_wifi')).nativeElement;
    expect(freeWifiElement.innerHTML).toMatch('Free WiFi');
    const saunaElement: HTMLElement = fixture.debugElement.query(By.css('#sauna')).nativeElement;
    expect(saunaElement.innerHTML).toMatch('Sauna');
    const fitnessElement: HTMLElement = fixture.debugElement.query(By.css('#fitness')).nativeElement;
    expect(fitnessElement.innerHTML).toMatch('Fitness');
  }));

  it('click apply all filters', async(() => {
    const applyAllFiltersElement: HTMLElement = fixture.debugElement.query(By.css('#apply_all_filters')).nativeElement;
    expect(applyAllFiltersElement.innerHTML).toMatch('Apply all filters');
   /* component.
    component.applyAllFilters();*/
    
  }));
});
