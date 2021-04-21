import {User} from '../pages/profile/user';
import {NotificationState} from './NotificationState';
import {NotificationType} from './NotificationType';
import {Product} from './Product';

export class Notification {
  id: number;
  sender: User;
  date: Date;
  state: string;
  notificationType: string;
  product: Product;

  constructor(obj) {
    obj && Object.assign(this, obj);
  }
}
