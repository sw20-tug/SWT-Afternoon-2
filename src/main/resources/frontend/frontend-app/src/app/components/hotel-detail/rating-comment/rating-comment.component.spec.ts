import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingCommentComponent } from './rating-comment.component';

describe('RatingCommentComponent', () => {
  let component: RatingCommentComponent;
  let fixture: ComponentFixture<RatingCommentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
    expect(component).toBeTruthy();
  });
});
