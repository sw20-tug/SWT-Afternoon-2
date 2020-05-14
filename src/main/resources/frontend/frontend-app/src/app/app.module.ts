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
import { SearchboxComponent } from './components/searchbox/searchbox.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NgxBootstrapSliderModule } from 'ngx-bootstrap-slider';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import { FiltersComponent } from './components/filters/filters.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule, MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import { NgSelectModule } from '@ng-select/ng-select';
import {MatChipsModule} from '@angular/material/chips';
import { SortByPipe } from './sort-by-pipe.pipe';
import { NewHotelComponent } from './components/header/new-hotel/new-hotel.component';
import { ReactiveFormsModule } from '@angular/forms';
import {UploadService} from "./service/upload.service";
import {MatProgressBarModule} from "@angular/material/progress-bar";

import { LoginComponent } from './components/header/login/login.component';
import { ConfirmationDialogComponent } from './components/header/login/confirmation-dialogue/confirmation-dialogue.component';
import { ConfirmationDialogService } from './components/header/login/confirmation-dialogue/confirmation-dialogue.service';

import { HotelDetailComponent } from './components/hotel-detail/hotel-detail.component';
import {MatSliderModule} from '@angular/material/slider';
import { RatingCommentComponent } from './components/hotel-detail/rating-comment/rating-comment.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    HotelListComponent,
    HotelListItemComponent,
    CategoryListComponent,
    CategoryListItemComponent,
    SearchboxComponent,
    FiltersComponent,
    SortByPipe,
    NewHotelComponent,
    LoginComponent,
    ConfirmationDialogComponent
    HotelDetailComponent,
    RatingCommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    NgxBootstrapSliderModule,
    NgMultiSelectDropDownModule.forRoot(),
    MatCardModule,

    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatCheckboxModule,
    MatRadioModule,
    MatButtonModule,
    MatSelectModule,
    MatListModule,
    NgSelectModule,
    MatChipsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatProgressBarModule,
    NgbModule
  ],
  exports: [
    HeaderComponent
  ],
  providers: [HttpClient, HttpClientService, SortByPipe, UploadService, ConfirmationDialogService],
  bootstrap: [AppComponent]
})
export class AppModule { }
