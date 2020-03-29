import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { HotelListComponent } from './components/hotel-list/hotel-list.component';
import { HotelListItemComponent } from './components/hotel-list/hotel-list-item/hotel-list-item.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientService} from "./service/http-client.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryListItemComponent } from './components/category-list/category-list-item/category-list-item.component';
import { CategoryCardComponent } from './components/category-card/category-card.component';
import { NgbdButtonsCheckbox } from './components/button-checkbox/button-checkbox.component';
import { SearchboxComponent } from './components/searchbox/searchbox.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NgxBootstrapSliderModule } from 'ngx-bootstrap-slider';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    HotelListComponent,
    HotelListItemComponent,
    CategoryListComponent,
    CategoryListItemComponent,
    CategoryCardComponent,
    NgbdButtonsCheckbox,
    SearchboxComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    NgxBootstrapSliderModule,
    NgMultiSelectDropDownModule.forRoot()
  ],
  exports: [
    HeaderComponent
  ],
  providers: [HttpClientService, HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
