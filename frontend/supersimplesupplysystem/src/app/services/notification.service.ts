import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductsLocation} from '../models/ProductsLocation';
import {AppConstants} from '../common/app.constants';
import {Notification} from '../models/Notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }

  getNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(AppConstants.API_URL + `notifications/householdInvitations`);
  }

  inviteUserToHousehold(emailAddress: string, householdId: number): Observable<any> {
    return this.http.post(AppConstants.API_URL + `notifications/inviteToHousehold`, {emailAddress, householdId});
  }

  setNotificationsSeen(): Observable<Notification[]> {
    return this.http.post<Notification[]>(AppConstants.API_URL + `notifications/setNotificationsSeen`, {});
  }

  acceptOrDeclineHouseholdInvitation(notificationId: number, accept: any): Observable<Notification[]> {
    return this.http.post<Notification[]>(AppConstants.API_URL + `notifications/acceptOrDeclineHouseholdInvitation`, {
      notificationId,
      accept
    });
  }
}
