import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
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

  getProductsByHousehold(householdId : string): Observable<any>{
    return this.http.get<Product[]>(AppConstants.API_URL + `product/household/${householdId}`);
}

  addProductToLocation(locationId: number, product: Product): Observable<any>{
    return this.http.post(AppConstants.API_URL + `product/${locationId}`, product);
  }

  getProductsWithBarcode(barcode: string, householdId: number): Observable<any> {
    return this.http.get<Product[]>(AppConstants.API_URL + `product/${householdId}/${barcode}`);
  }

  deleteProducts(id: number, amount: number): Observable<any> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        amount
      },
    };
    return this.http.delete(AppConstants.API_URL + `product/${id}`, options);
  }
}
