import {ProductsLocation} from './ProductsLocation';

export class Product {
  id: number;
  barcode: string;
  name: string;
  quantity: string;
  amount: number;
  location: ProductsLocation;
  expirationDate: Date;
  addedDateTime: Date;
}
