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

    console.log('other filters: ')
    console.log(currentlySelectedLocations)
    return this.http.get<Category[]>(this.usersUrl+ '/apply?minPrice=' + this.checkIfUndefined(minPrice) + '&maxPrice=' + this.checkIfUndefined(maxPrice)
      + '&minRating=' + this.checkIfUndefined(minRating)+ '&maxRating=' + this.checkIfUndefined(maxRating) + '&starsFilter=' + starsFilter +
      '&currentlySelectedActivities=' + this.checkIfUndefined(currentlySelectedActivities)
      + '&currentlySelectedLocations=' + this.checkIfUndefined(currentlySelectedLocations) + '&otherFilters=' + otherFilters);
  }

  public getHotelWithActivities(activities: string[]): Observable<any> {
    return this.http.get<Hotel[]>(this.usersUrl + '/activities=' + activities, this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
  }

  public sortByCriteria(category_id: number, criteria_id: number): Observable<any> {
    console.log("Htp options are, ", new HttpHeaders());
    return this.http.get<Hotel[]>(this.usersUrl + '/criteria?category_id=' + category_id + '&criteria_id=' + criteria_id, this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );

  }

  public errorHandler(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }

  //helper function
  public checkIfUndefined (value)
  {
    if(value === undefined)
      return 0;
    else
      return value;
  }

  public checkIfEmptyList(list_to_send)
  {
    if(list_to_send === undefined)
    {
      return '';
    }
    else
    {
      return list_to_send;
    }
  }

}
