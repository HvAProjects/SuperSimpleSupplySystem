import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Household} from '../pages/household/household';
import {AppConstants} from '../common/app.constants';

@Injectable({
  providedIn: 'root'
})
export class HouseholdService {

  constructor(private http: HttpClient) { }

  public getAllHouseholds(): Observable<Household[]> {
    return this.http.get<Household[]>(AppConstants.API_URL + 'household/');
  }

  public deleteHousehold(id: number) {
    return this.http.delete<Household>(AppConstants.API_URL + `household/${id}`).subscribe(
      () => {
        console.log('Household: ' + id + ' has been deleted');
      },
      error => {
        console.log('Error deleting household ' + id + ' - Error: ' + error);
      }
    );
  }
}
