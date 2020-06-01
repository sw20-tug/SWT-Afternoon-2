import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewHotelComponent } from './new-hotel.component';
import { RouterTestingModule } from '@angular/router/testing';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {TranslateModule} from "@ngx-translate/core";

describe('NewHotelComponent', () => {
  let component: NewHotelComponent;
  let fixture: ComponentFixture<NewHotelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, TranslateModule.forRoot()],

      declarations: [ NewHotelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it(`form should be invalid`, async(() => {
    component.hotelForm.controls['stars'].setValue('test_stars');
    component.hotelForm.controls['price'].setValue('test_price');
    component.hotelForm.controls['rating'].setValue('test_rating');
    expect(component.hotelForm.valid).toBeFalsy();
  }));

  it(`form should be valid`, async(() => {
    component.hotelForm.controls['stars'].setValue(7);
    component.hotelForm.controls['price'].setValue(98);
    component.hotelForm.controls['rating'].setValue(8.5);
    expect(component.hotelForm.valid).toBeTruthy();
  }));
});
