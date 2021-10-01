import { Component, OnInit } from '@angular/core';
import {NotificationType} from '../../models/NotificationType';
import {AcceptHouseholdInvitationPromptComponent} from '../../dialogs/accept-household-invitation-prompt/accept-household-invitation-prompt.component';
import {NotificationState} from '../../models/NotificationState';
import {Notification} from '../../models/Notification';
import {faBell} from '@fortawesome/free-solid-svg-icons';
import {NotificationService} from '../../services/notification.service';
import {MatDialog} from '@angular/material/dialog';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.css']
})
export class NotificationListComponent implements OnInit {

  faBell = faBell;
  notifications: Array<Notification> = [];

  constructor(public auth: AuthService,
              private notificationService: NotificationService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getNotifications();
    setInterval(() => this.getNotifications(), 5000);
  }

  onClick(notification: Notification): void {
    if (this.canInteract(notification)) {
      switch (notification.notificationType) {
        case NotificationType.getName(NotificationType.householdInvitation):
          this.onClickHouseholdInvitation(notification);
          break;
      }
    }
  }

  onClickHouseholdInvitation(notification: Notification): void {
    const dialogRef = this.dialog.open(AcceptHouseholdInvitationPromptComponent, {
      // width  : '380px',
      disableClose: true,
      data: notification
    });

    dialogRef.afterClosed().subscribe(result => {
      this.notificationService.acceptOrDeclineHouseholdInvitation(notification.id, result).subscribe(res => {
        this.updateNotifications(res);
      });
    });
  }

  canInteract(notification: Notification): boolean {
    return notification.state === NotificationState.getName(NotificationState.unseen) ||
      notification.state === NotificationState.getName(NotificationState.seen);
  }

  countUnseenNotifications(): number {
    let count = 0;
    for (const notification of this.notifications) {
      if (notification.state === NotificationState.getName(NotificationState.unseen)) {
        count++;
      }
    }
    return count;
  }

  getNotifications(): void {
    this.notificationService.getNotifications().subscribe((res: Array<Notification>) => {
      this.updateNotifications(res);
    });
  }

  setNotificationsSeen(): void {
    this.notificationService.setNotificationsSeen().subscribe((res: Array<Notification>) => {
      this.updateNotifications(res);
    });
  }

  updateNotifications(notifications): void {
    this.notifications = [];
    for (const notification of notifications) {
      this.notifications.push(new Notification(notification));
    }
  }
}
