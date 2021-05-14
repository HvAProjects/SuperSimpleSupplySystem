import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Product} from '../../models/Product';
import {Household} from '../../pages/household/household';
import {tap} from 'rxjs/operators';
import {HouseholdService} from '../../services/household.service';
import {ProductService} from '../../services/product.service';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-items-about-to-expire',
  templateUrl: './items-about-to-expire.component.html',
  styleUrls: ['./items-about-to-expire.component.css']
})
export class ItemsAboutToExpireComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['name', 'location', 'expirationDate'];
  products: Product[] = [];
  dataSource: MatTableDataSource<Product>;
  households: Household[];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private householdService: HouseholdService, private productService: ProductService) {
    this.getAllHouseholds();
  }


  ngOnInit(): void {
  }


  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getAllProducts())
      )
      .subscribe();
  }

  getAllHouseholds() {
    this.householdService.getAllHouseholds().pipe(
      tap(result => {
        console.log(result);
        this.households = result;
        this.getAllProducts();
      })
    ).subscribe();
  }

  getAllProducts() {
    this.productService.getProductsFromAllHouseholds(this.households).subscribe((data: Product[]) => {
      const dataSource = [];
      for (const item of data) {
        const product = new Product();
        product.id = item.id;
        product.expirationDate = new Date(item.expirationDate);
        product.barcode = item.barcode;
        product.amount = item.amount;
        product.name = item.name;
        product.quantity = item.quantity;
        product.location = item.location;
        dataSource.push(product);
        this.products.push(product);
      }
      this.dataSource = new MatTableDataSource<Product>(dataSource);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }


}
