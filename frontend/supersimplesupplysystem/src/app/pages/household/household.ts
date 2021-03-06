import {User} from '../profile/user';

export class Household {
  id: number;
  name: string;
  address: string;
  postalCode: string;
  city: string;
  country: string;
  users: User[] = [];
}
