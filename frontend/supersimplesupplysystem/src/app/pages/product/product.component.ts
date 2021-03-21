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

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'quantity', 'action'];
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

  }

  openAddProductDialog(): void {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result instanceof Product) {
        // this.addLocation(result);
      }
    });
  }

  private getProducts(): void {
    this.productService.getProducts(this.locationId).subscribe((data: Product[]) =>
      this.dataSource = new MatTableDataSource<Product>(data));
  }
}
