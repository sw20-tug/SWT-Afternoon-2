import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelListItemComponent } from './hotel-list-item.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {TranslateModule} from "@ngx-translate/core";
import {RouterTestingModule} from "@angular/router/testing";
describe('HotelListItemComponent', () => {
  let component: HotelListItemComponent;
  let fixture: ComponentFixture<HotelListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule, HttpClientTestingModule, TranslateModule.forRoot()
      ],
      declarations: [ HotelListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterAll(() => TestBed.resetTestingModule());

});
