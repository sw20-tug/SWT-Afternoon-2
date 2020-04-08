import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Hotel} from "../components/hotel-list/hotel.model";
import {Category} from "../components/category-list/category.model";

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
    this.usersUrl = 'http://localhost:8080';
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
    console.log("test2")
    return this.http.get<Hotel[]>(this.usersUrl+ '/hotel?price=' + price);
  }

  public getFilteredHotels(minPrice: number, maxPrice: number,
                           minRating: number, maxRating: number,
                           starsFilter: number, currentlySelectedActivities: string[],
                           currentlySelectedLocations: string[], otherFilters: boolean[]): Observable<Category[]>
  {

    return this.http.get<Category[]>(this.usersUrl+ '/apply?minPrice=' + minPrice + '&maxPrice=' + maxPrice + '&minRating='
      + minRating+ '&maxRating=' + maxRating + '&starsFilter=' + starsFilter + '&currentlySelectedActivities=' + currentlySelectedActivities
      + '&currentlySelectedLocations=' + currentlySelectedLocations + '&otherFilters=' + otherFilters);

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
