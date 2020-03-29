import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Hotel} from "../components/hotel-list/hotel.model";
import {catchError, retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  private usersUrl: string;
  private retryCount: number;
  private httpOptions: any;
  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders()
    };
    this.httpOptions.headers.append('Access-Control-Allow-Headers', '*');
    this.httpOptions.headers.append('Access-Control-Allow-Methods', '*');
    this.httpOptions.headers.append('Access-Control-Allow-Origin', '*');
    this.usersUrl = 'http://localhost:8080/';
    this.retryCount = 1;
  }
  public findAll(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.usersUrl+ '/hotels');
  }
  /* TODO
  public save(hotel: Hotel) {
    return this.http.post<Hotel>(this.usersUrl, Hotel);
  } */

  public getHotelWithinPriceRange(price: number): Observable<Hotel[]>
  {
    return this.http.get<Hotel[]>(this.usersUrl+ '/hotel?price=' + price);

  }

  public getHotelWithActivities(activities: string[]): Observable<any>
  {
    return this.http.get<Hotel[]>(this.usersUrl + '/activities=' + activities, this.httpOptions) .pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
  }

  public errorHandler(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }



}
