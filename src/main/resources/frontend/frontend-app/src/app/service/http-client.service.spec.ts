import { async, TestBed } from '@angular/core/testing';

import { HttpClientService } from './http-client.service';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('HttpClientService', () => {
  let service: HttpClientService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [	
        HttpClient,	
        HttpHandler	
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

  it('check if undefined', async(() => {
   var u = service.checkIfUndefined(undefined);
    expect(u).toEqual(0);

    var v = service.checkIfUndefined("value");
    expect(v).toEqual("value");

  }));
});
