import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Hotel} from "../components/hotel-list/hotel.model";
import {Category} from "../components/category-list/category.model";
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
    this.httpOptions.headers.append('Content-Type: application/json', '*');
    this.usersUrl = 'http://localhost:8080';
    this.retryCount = 1;
  }

  public findAll(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.usersUrl + '/hotels');
  }

  /* TODO
  public save(hotel: Hotel) {
    return this.http.post<Hotel>(this.usersUrl, Hotel);
  } */

  public getHotelWithinPriceRange(price: number): Observable<Hotel[]>
  {

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

  public getHotelWithActivities(activities: string[]): Observable<any> {
    return this.http.get<Hotel[]>(this.usersUrl + '/activities=' + activities, this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
  }

  public sortByCriteria(category_id: number): Observable<any> {
    console.log("Htp options are, ", new HttpHeaders());
    return this.http.get<Hotel[]>(this.usersUrl + '/criteria?category_id=' + category_id, this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
    // return this.http.get("/api/countries", {params: criteria.toString()})

  }

  // public getFilteredHotels(filters: any[]): Observable<Hotel[]>
  // {
  //   return this.http.get<Hotel[]>(this.usersUrl+ '/hotels?filtered' + filters);
  //
  // }

  public errorHandler(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }

}
