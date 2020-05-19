import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpClientService} from "../../service/http-client.service";
import { Hotel } from '../hotel-list/hotel.model';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { RatingComment } from './rating-comment.model';
import {Category} from "../category-list/category.model";

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrls: ['./hotel-detail.component.css']
})
export class HotelDetailComponent implements OnInit {
  public hotel: Hotel;
  public commentForm: FormGroup;
  public images: any[] = [];

  constructor(private route: ActivatedRoute, private HttpClientService: HttpClientService) { }

  ngOnInit(): void {
    // TODO: LOAD IMAGES FOR HOTEL
    this.images = ["https://www.w3schools.com/w3css/img_lights.jpg", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"];
    this.route.params.subscribe(params => {
      this.HttpClientService.getHotelById(params.id).subscribe(hotel => {
        this.hotel = Hotel.MapHotel(hotel);
        this.hotel.facilitiesList = this.hotel.facilitiesList.filter(x => x.value);
        // DELETE THIS AFTER DB IS FILLED WITH ACTUAL RATING+COMMENTS !
        this.HttpClientService.getCommentList("10").subscribe(comments => {
          var list_of_comments = new Array<RatingComment>();
          comments.forEach(commment => {
            list_of_comments.push(new RatingComment("stefa", "kk", 1));
          });
          this.hotel.ratingComments = list_of_comments;
        });
      })
    });

    this.commentForm = new FormGroup({
      name: new FormControl('', [Validators.maxLength(60)]),
      rating: new FormControl('', [Validators.max(10), Validators.min(0), Validators.required]),
      comment: new FormControl('', [Validators.maxLength(200)])
    });
  }
}
