import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Product} from '../../models/Product';
import {MatTableDataSource} from '@angular/material/table';
import {Household} from '../../pages/household/household';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {HouseholdService} from '../../services/household.service';
import {ProductService} from '../../services/product.service';
import {finalize, tap} from 'rxjs/operators';

@Component({
  selector: 'app-quick-add-remove',
  templateUrl: './quick-add-remove.component.html',
  styleUrls: ['./quick-add-remove.component.css']
})
export class QuickAddRemoveComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['name', 'location', 'amount', 'add', 'remove'];
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
        product.addedDateTime = new Date(item.addedDateTime);
        dataSource.push(product);
        this.products.push(product);
      }
      this.dataSource = new MatTableDataSource<Product>(dataSource);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  quickAdd(event: Product) {
    event.amount = 1;
    this.productService.addProductToLocation(event.location.id, event).pipe(
      finalize(() => {
        this.getAllProducts();
      })
    ).subscribe();
  }

  quickRemove(event: Product) {
    this.productService.deleteProducts(event.id, 1).pipe(
      finalize(() => {
        this.getAllProducts();
      })
    ).subscribe();
  }

}
