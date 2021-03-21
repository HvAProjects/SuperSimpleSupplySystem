import { Component, OnInit } from '@angular/core';
import {Product} from '../../models/Product';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ProductsLocation} from '../../models/ProductsLocation';
import {ScannerDialogComponent} from '../scanner-dialog/scanner-dialog.component';

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
  styleUrls: ['./add-product-dialog.component.css']
})
export class AddProductDialogComponent implements OnInit {

  product = new Product();

  constructor(
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<AddProductDialogComponent>) { }

  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close({event: 'close'});
  }

  addProduct(): void {
    this.dialogRef.close(this.product);
  }

  scanProduct(): void {
    const dialogRef = this.dialog.open(ScannerDialogComponent, {
      // width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result instanceof Product) {
        // this.addLocation(result);
      }
    });
  }
}
