import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../common/app.constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signin', {
      email: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signup', {
      displayName: user.displayName,
      email: user.email,
      password: user.password,
      matchingPassword: user.matchingPassword,
      socialProvider: 'LOCAL'
    }, httpOptions);
  }

  forgotPassword(emailAddress): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'forgot-password', {
      email: emailAddress
    }, httpOptions);
  }

  // From a reset password url (with password reset token)
  changePasswordWithToken(oldPassword, newPassword, confirmNewPassword, token): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'change-password-with-token', {
      newPassword,
      token
    }, httpOptions);
  }

  activateAccount(token): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'activate-account', {
      token
    }, httpOptions);
  }
}
