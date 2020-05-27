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
                        stars: number, city: string, currentlySelectedActivities: string[], otherFilters: (boolean)[],
                        imageURL: string) {
    return this.http.post(this.serverUrl + '/addNewHotels?name=' + name + '&description=' + description +
      '&category=' + category +
      '&price=' + price + '&rating=' + rating + '&stars=' + stars + '&city=' + city +
      '&currentlySelectedActivities=' + currentlySelectedActivities + '&otherFilters=' + otherFilters
     + '&imageURL=' + imageURL, this.httpOptions, {responseType: 'text'}).pipe(
        catchError(this.errorHandler)
    );
  }

  public insertNewComment(comm_text: string, user_name: string, rate: number, hotel_id: number) {

    return this.http.post(this.serverUrl + '/addNewComment?comm_text=' + comm_text + '&user_name=' + user_name +
      '&rate=' + rate +
      '&hotel_id=' + hotel_id, this.httpOptions, {responseType: 'text'}).pipe(
      catchError(this.errorHandler)
    );


  }

  public deleteHotel(hotel_name: string)
  {
    console.log("delete hotel with name: " + hotel_name)
    return this.http.post(this.serverUrl + '/deleteHotel?hotel_name=' + hotel_name, this.httpOptions, {responseType: 'text'}).pipe(
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

  public getHotelById(id: String): Observable<any> {
    return this.http.get<any>(this.serverUrl + '/hotelDetail?id=' + id).pipe(
      retry(this.retryCount),
      catchError(this.errorHandler)
    );
  }

  public getCommentList(id: String): Observable<any> {
    return this.http.get<any[]>(this.serverUrl + '/commentHotel?id=' + id).pipe(
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

  public changeRating (new_rate: number, id: number) {
  console.log("ID ID ID ID ID" + id);
  return this.http.post(this.serverUrl + '/changeRating?rate=' + new_rate + '&id=' + id, this.httpOptions, {responseType: 'text'}).pipe(
        catchError(this.errorHandler)
      );
  }

}
