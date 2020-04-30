import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryListItemComponent } from './category-list-item.component';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('CategoryListItemComponent', () => {
  let component: CategoryListItemComponent;
  let fixture: ComponentFixture<CategoryListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryListItemComponent ],
      providers: [	
        HttpClient,	
        HttpHandler	
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
