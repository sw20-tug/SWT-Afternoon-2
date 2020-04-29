import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchboxComponent } from './searchbox.component';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('SearchboxComponent', () => {
  let component: SearchboxComponent;
  let fixture: ComponentFixture<SearchboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchboxComponent ],
      providers: [
        HttpClient,
        HttpHandler
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

 it('should emit searchBoxEvent in search', () => {
     component.searchText = 'test';
     const spyEmit = spyOn(component.searchBoxEvent, 'emit');
     component.search();
     expect(spyEmit).toHaveBeenCalled();
   });


});
