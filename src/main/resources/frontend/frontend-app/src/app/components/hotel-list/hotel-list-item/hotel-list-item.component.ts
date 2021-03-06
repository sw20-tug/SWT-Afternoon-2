import { Component, OnInit, Input } from '@angular/core';
import { Hotel } from '../hotel.model';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotel-list-item',
  templateUrl: './hotel-list-item.component.html',
  styleUrls: ['./hotel-list-item.component.css'],
  providers: [NgbRatingConfig]
})
export class HotelListItemComponent implements OnInit {
  @Input() hotel: Hotel;
  @Input() index: number;
  @Input() isAdmin: boolean;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  openHotelDetailPage(id: number){
    this.router.navigate(['/hotel-detail', id]);
  }
}
