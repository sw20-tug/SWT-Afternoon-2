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

  private serverUrl: string;
  private retryCount: number;
  private httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders()
    };
    this.httpOptions.headers.append('Content-Type: application/json', '*');
    this.serverUrl = 'http://localhost:8080';
    this.retryCount = 1;
  }

  public findAll(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.serverUrl + '/hotels');
  }

  /* TODO
  public save(hotel: Hotel) {
    return this.http.post<Hotel>(this.usersUrl, Hotel);
  } */

  public getHotelWithinPriceRange(price: number): Observable<Hotel[]>
  {
    return this.http.get<Hotel[]>(this.serverUrl+ '/hotel?price=' + price);
  }

  // public insertNewHotel(name: string, description: string, category: string, activities: string, stars: number, price: number,
  //                       city: string, rating: number, otherFilters: number, imageURL: string) {
  //   return this.http.post<any>(this.serverUrl + 'addNewHotel/' + name + description + category + activities + stars
  //     + price + city + rating + otherFilters + imageURL,this.httpOptions)
  //     .pipe(
  //       retry(this.retryCount)
  //     )
  // }

  //TODO parameters: name, description, price, rating, stars, city, activity, otherFilters, image
  public insertNewHotel(name: string, description: string, category: string, price: number, rating: number,
                        stars: number, city: string, activities: string, otherFilters: number, imageURL: string) {


    console.log("hotel name: " + name)
    console.log("hotel desc: " + description)
    console.log("hotel category: " + category)
    console.log("hotel price: " + price)
    console.log("hotel rating: " + rating)
    console.log("hotel stars: " + stars)
    console.log("hotel city: " + city)
    console.log("hotel activities: " + activities)
    console.log("hotel of: " + otherFilters)
    console.log("hotel image: " + imageURL)
    return this.http.post<any[]>(this.serverUrl + '/addNewHotel?name=' + name + '&description=' + description +
      '&category=' + category +
      '&price=' + price + '&rating=' + rating + '&stars=' + stars + '&city=' + city +
      '&activities=' + activities + '&otherFilters=' + otherFilters + '&imageURL=' + imageURL, this.httpOptions).pipe(
        catchError(this.errorHandler)
    );
  }

  public getFilteredHotels(minPrice: number, maxPrice: number,
                           minRating: number, maxRating: number,
                           starsFilter: number, currentlySelectedActivities: string[],
                           currentlySelectedLocations: string[], otherFilters: boolean[]): Observable<Category[]>
  {

    console.log('other filters: ')
    console.log(currentlySelectedLocations)
    return this.http.get<Category[]>(this.serverUrl+ '/apply?minPrice=' + this.checkIfUndefined(minPrice) + '&maxPrice=' + this.checkIfUndefined(maxPrice)
      + '&minRating=' + this.checkIfUndefined(minRating)+ '&maxRating=' + this.checkIfUndefined(maxRating) + '&starsFilter=' + starsFilter +
      '&currentlySelectedActivities=' + this.checkIfUndefined(currentlySelectedActivities)
      + '&currentlySelectedLocations=' + this.checkIfUndefined(currentlySelectedLocations) + '&otherFilters=' + otherFilters);
  }

  public getHotelWithActivities(activities: string[]): Observable<any> {
    return this.http.get<Hotel[]>(this.serverUrl + '/activities=' + activities, this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
  }

  public sortByCriteria(category_id: number, criteria_id: number): Observable<any> {
    console.log("Htp options are, ", new HttpHeaders());
    return this.http.get<Hotel[]>(this.serverUrl + '/criteria?category_id=' + category_id + '&criteria_id=' + criteria_id, this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );

  }

  public getCategories(): Observable<any> {
    return this.http.get<any[]>(this.serverUrl + '/getCategories', this.httpOptions).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
  }

  public getHotelById(hotelId: number): Observable<any> {
    return this.http.get<any>(this.serverUrl + '/hotel-detail/' + hotelId, this.httpOptions).pipe(
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

}
