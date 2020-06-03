import { Component, OnInit, Input } from '@angular/core';
import { RatingComment } from '../rating-comment.model';

@Component({
  selector: 'app-rating-comment',
  templateUrl: './rating-comment.component.html',
  styleUrls: ['./rating-comment.component.css']
})
export class RatingCommentComponent implements OnInit {
  @Input() ratingComment: RatingComment;

  constructor() { }

  ngOnInit(): void {
  }

}
