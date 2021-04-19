import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../services/token-storage.service';
import {NotificationService} from '../../services/notification.service';

import {Notification} from '../../models/Notification';
import {NotificationState} from '../../models/NotificationState';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;
  collapsed: any;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.checkLoginStatus();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.checkLoginStatus();
  }

  checkLoginStatus(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.displayName;
    }
  }

}
