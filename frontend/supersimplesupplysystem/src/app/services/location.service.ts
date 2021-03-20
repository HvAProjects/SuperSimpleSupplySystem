import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Household} from '../pages/household/household';
import {AppConstants} from '../common/app.constants';
import {ProductsLocation} from '../models/ProductsLocation';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }

  getLocationsOfHousehold(householdId: number): Observable<ProductsLocation[]> {
    return this.http.get<ProductsLocation[]>(AppConstants.API_URL + `location/${householdId}`);
  }

  addLocationToHousehold(householdId: number, location: ProductsLocation): Observable<any> {
    return this.http.post<ProductsLocation>(AppConstants.API_URL + `location/${householdId}`, location);
  }
}
