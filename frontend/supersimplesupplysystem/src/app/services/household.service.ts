import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Household} from '../pages/household/household';
import {AppConstants} from '../common/app.constants';
import {User} from '../pages/profile/user';

@Injectable({
  providedIn: 'root'
})
export class HouseholdService {

  constructor(private http: HttpClient) { }

  public getAllHouseholds(): Observable<Household[]> {
    return this.http.get<Household[]>(AppConstants.API_URL + 'household/');
  }

  public leaveHousehold(id: number) {
    return this.http.post<Household>(AppConstants.API_URL + `household/leaveHousehold/${id}`, {});
  }

  public addHousehold(household: Household){
    return this.http.post<Household>(AppConstants.API_URL  + `household/`, household );
  }


  getUsersOfHousehold(id: number): Observable<User[]> {
    return this.http.get<User[]>(AppConstants.API_URL + `household/householdUsers/${id}`);
  }
}
