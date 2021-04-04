import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {ProductsLocation} from '../../models/ProductsLocation';
import {ActivatedRoute, Params} from '@angular/router';
import {LocationService} from '../../services/location.service';
import {MatDialog} from '@angular/material/dialog';
import {ProductService} from '../../services/product.service';
import {Product} from '../../models/Product';
import {AddLocationDialogComponent} from '../../dialogs/add-location-dialog/add-location-dialog.component';
import {AddProductDialogComponent} from '../../dialogs/add-product-dialog/add-product-dialog.component';
import {DeleteProductDialogComponent} from '../../dialogs/delete-product-dialog/delete-product-dialog.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  displayedColumns: string[] = ['barcode', 'name', 'quantity', 'amount', 'expirationDate', 'action'];
  locationId;
  dataSource: MatTableDataSource<Product>;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.locationId = +params.locationId);
    this.getProducts();
  }

  openDeleteProductDialog(product): void {
    const dialogRef = this.dialog.open(DeleteProductDialogComponent, {
      width  : '380px',
      disableClose: false,
      data: product
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== null) {
        console.log(product);
        this.productService.deleteProducts(product.id, result).subscribe(res => {
          if (res.success) {
            this.getProducts();
          }
        });
      }
    });
  }

  openAddProductDialog(): void {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== null) {
        this.productService.addProductToLocation(this.locationId, result).subscribe(res => {
          if (res.success) {
            this.getProducts();
          }
        });
      }
    });
  }

  private getProducts(): void {
    this.productService.getProducts(this.locationId).subscribe((data: Product[]) => {
      const dataSource = [];
      // Onderstaande code omdat de response niet automatisch naar een Product type vertaald wordt door javascript
      // Daardoor wordt de date als string geïnterpreteerd. Jeeej javascript
      // Typescript maakt het nog vager, omdat je er daardoor vanuit gaat dat het wel een Date type is
      for (const item of data) {
        const product = new Product();
        product.id = item.id;
        product.expirationDate = new Date(item.expirationDate);
        product.barcode = item.barcode;
        product.amount = item.amount;
        product.name = item.name;
        product.quantity = item.quantity;
        dataSource.push(product);
      }
      this.dataSource = new MatTableDataSource<Product>(dataSource);
    });
  }
}
