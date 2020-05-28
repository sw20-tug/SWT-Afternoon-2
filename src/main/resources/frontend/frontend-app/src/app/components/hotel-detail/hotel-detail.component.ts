import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClientService} from "../../service/http-client.service";
import { Hotel } from '../hotel-list/hotel.model';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { RatingComment } from './rating-comment.model';
import {HotelService} from "../../service/hotel.service";
import {Category} from "../category-list/category.model";
import {FiltersComponent} from "../filters/filters.component";
import {HomeComponent} from "../home/home.component";
import {ConfirmationDialogService} from "../header/login/confirmation-dialogue/confirmation-dialogue.service";


@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrls: ['./hotel-detail.component.css']
})
export class HotelDetailComponent implements OnInit {
  public hotel: Hotel;
  public commentForm: FormGroup;
  public images: any[] = [];
  public showDeleteConfirmationDialog: boolean;
  public showEditForm: boolean;
  public isAdminForDelete: boolean;


  constructor(private router: Router, private route: ActivatedRoute, private HttpClientService: HttpClientService, private hotelService: HotelService,
              private confirmationDialogService: ConfirmationDialogService) { }


  ngOnInit(): void {
    // TODO: LOAD IMAGES FOR HOTEL
    this.images = ["https://www.w3schools.com/w3css/img_lights.jpg", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"];
    this.route.params.subscribe(params => {
      this.HttpClientService.getHotelById(params.id).subscribe(hotel => {
        this.hotel = Hotel.MapHotel(hotel);
        this.hotel.facilitiesList = this.hotel.facilitiesList.filter(x => x.value);

        this.hotelService.translateAsObs.subscribe(trigger => {
          for(let i=0; i<this.hotel.facilitiesList.length; i++)
          {
            if(this.hotel.facilitiesList[i].name === 'Gym') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('FITNESS');
            }
           else if(this.hotel.facilitiesList[i].name === 'OpenBar') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('OPEN BAR');
            }
            else if(this.hotel.facilitiesList[i].name === 'Running') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('RUNNING');
            }
            else  if(this.hotel.facilitiesList[i].name === 'AirConditioning') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('AC');
            }
            else  if(this.hotel.facilitiesList[i].name === 'BeachFront') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('BEACH');
            }
            else  if(this.hotel.facilitiesList[i].name === 'FreeWiFi') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('WIFI');
            }
            else  if(this.hotel.facilitiesList[i].name === 'Pets') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('PETS');
            }
            else   if(this.hotel.facilitiesList[i].name === 'Restaurant') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('RESTAURANT');
            }
            else   if(this.hotel.facilitiesList[i].name === 'Smoking') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('SMOKE');
            }
            else if(this.hotel.facilitiesList[i].name === 'SwimmingPool') {
              this.hotel.facilitiesList[i].name = Hotel.translateService.instant('POOL');
            }
          }
        });
        // DELETE THIS AFTER DB IS FILLED WITH ACTUAL RATING+COMMENTS !
        this.HttpClientService.getCommentList(params.id).subscribe(comments => {
          var list_of_comments = new Array<RatingComment>();
          comments.forEach(comment => {
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


  showEdit()
  {
    this.showEditForm = true;
  }


  show()
  {
    this.showDeleteConfirmationDialog = true;
  }

  deleteHotel()
  {
    this.HttpClientService.deleteHotel(this.hotel.name).subscribe(response => {
      this.showDeleteConfirmationDialog = false;
      this.router.navigate(['']);
    });

  }

  hideEdit()
  {
    this.showEditForm = false;
  }

  hide()
  {
    this.showDeleteConfirmationDialog = false;
  }

  public insertNewComment()
  {
    this.route.params.subscribe(params => {

      this.HttpClientService.insertNewComment(this.commentForm.get('comment').value, this.commentForm.get('name').value,
        this.commentForm.get('rating').value, params.id).subscribe(response => {
      });

      this.HttpClientService.changeRating(this.commentForm.get('rating').value, params.id).subscribe(response => {
      });

    });

    this.confirmationDialogService.confirm("Comment confirmation", "You have sent feedback!")
      .then((confirmed) =>  !confirmed)
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));


  }
}
