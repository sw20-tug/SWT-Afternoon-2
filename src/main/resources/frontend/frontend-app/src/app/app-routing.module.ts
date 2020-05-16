import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "./components/home/home.component";
import { NewHotelComponent } from "./components/header/new-hotel/new-hotel.component";
import { HotelDetailComponent } from "./components/hotel-detail/hotel-detail.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'new-hotel', component: NewHotelComponent },
  { path: 'hotel-detail/:id', component: HotelDetailComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
