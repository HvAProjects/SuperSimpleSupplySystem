import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {Notification} from '../../models/Notification';
import {NotificationType} from '../../models/NotificationType';
import {NotificationState} from '../../models/NotificationState';
import {NotificationService} from '../../services/notification.service';
import {EmailAddressPromptComponent} from '../../dialogs/email-address-prompt/email-address-prompt.component';
import {MatDialog} from '@angular/material/dialog';
import {AcceptHouseholdInvitationPromptComponent} from '../../dialogs/accept-household-invitation-prompt/accept-household-invitation-prompt.component';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  constructor(private notificationService: NotificationService,
              private dialog: MatDialog) { }

  @Input('notification') notification: Notification;

  // @Output('click') click = new EventEmitter();

  getNotificationType(): string {
    return NotificationType.getStringValue(this.notification.notificationType);
  }

  getActionText(): string {
    return NotificationState.getStringValue(this.notification.state);
  }

  ngOnInit(): void {
  }

  onClick(): void {
    // this.click.emit();
  }

  getStyle(): string {
    return this.notification.state === NotificationState.getName(NotificationState.unseen) ?
      'background-color: rgba(0, 0, 0, 0.1)' : 'background-color: transparent';
  }

  canInteract(): boolean {
    return this.notification.state === NotificationState.getName(NotificationState.unseen) ||
      this.notification.state === NotificationState.getName(NotificationState.seen);
  }

  nameof<T>(key: keyof T, instance?: T): keyof T {
    return key;
  }
}
