import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Household} from "../pages/household/household";
import {AppConstants} from "../common/app.constants";
import {Observable} from "rxjs";
import {Product} from '../models/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public getProductByEAN(eanCode: string): Observable<any>{
    return this.http.get<any>(AppConstants.API_URL + `product/productType/${eanCode}`);
  }

  getProducts(locationId: number): Observable<Product[]> {
    return this.http.get<Product[]>(AppConstants.API_URL + `product/${locationId}`);
  }
}
