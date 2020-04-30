import { async, TestBed } from '@angular/core/testing';

import { HttpClientService } from './http-client.service';
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';

describe('HttpClientService', () => {
  let service: HttpClientService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [	
        HttpClient, HttpHandler
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  afterAll(() => TestBed.resetTestingModule());

});
