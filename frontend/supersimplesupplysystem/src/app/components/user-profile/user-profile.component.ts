import { Component } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'user-profile',
  template: `
    <span class="ss-welcome" *ngIf="auth.user$ | async as user">
        Welcome {{user.name}} ! |
    </span>`
})
export class UserProfileComponent {
  constructor(public auth: AuthService) {}
}