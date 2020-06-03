import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {TranslateModule} from "@ngx-translate/core";
import { RatingCommentComponent } from './rating-comment.component';

describe('RatingCommentComponent', () => {
  let component: RatingCommentComponent;
  let fixture: ComponentFixture<RatingCommentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ RatingCommentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
  const spy = spyOnProperty(component, 'ratingComment').and.returnValue(
   {name: 'test', comment: 'comment', rating: 5});

  });
});
