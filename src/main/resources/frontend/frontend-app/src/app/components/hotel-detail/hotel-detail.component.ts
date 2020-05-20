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
        this.HttpClientService.getCommentList(params.id).subscribe(comments => {
          var list_of_comments = new Array<RatingComment>();
          comments.forEach(comment => {
            console.log("ID HOTELA " + params.id);
            console.log("COMMMENTTTTTT" + comment.id);
            console.log("COMMMENTTTTTT" + comment.comm_text);
            list_of_comments.push(new RatingComment(comment.user_name, comment.comm_text, comment.rate));
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

  public insertNewComment()
  {
    this.route.params.subscribe(params => {
      console.log("NAMEEEEEEEEEEEEEE" + this.commentForm.get('name').value);
      console.log("NAMEEEEEEEEEEEEEE" + this.commentForm.get('comment').value);
      console.log("NAMEEEEEEEEEEEEEE" + this.commentForm.get('rating').value);
      console.log("NAMEEEEEEEEEEEEEE" + params.id);

    });
    this.HttpClientService.insertNewComment(this.commentForm.get('comment').value, this.commentForm.get('name').value,
      this.commentForm.get('rating').value, 805);
  }
}
