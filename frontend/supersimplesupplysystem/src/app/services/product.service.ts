import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Household} from "../pages/household/household";
import {AppConstants} from "../common/app.constants";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public getProductByEAN(eanCode: string): Observable<any>{
    return this.http.get<any>(AppConstants.API_URL + `product/${eanCode}`);
  }


}
