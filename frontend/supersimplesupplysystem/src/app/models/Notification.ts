import {User} from '../pages/profile/user';
import {NotificationState} from './NotificationState';
import {NotificationType} from './NotificationType';

export class Notification {
  id: number;
  sender: User;
  date: Date;
  state: string;
  notificationType: string;

  constructor(obj) {
    obj && Object.assign(this, obj);
  }
}
