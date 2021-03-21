import { Component, OnInit } from '@angular/core';
import {Product} from '../../models/Product';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
  styleUrls: ['./add-product-dialog.component.css']
})
export class AddProductDialogComponent implements OnInit {

  product: Product;

  constructor(
    private dialogRef: MatDialogRef<AddProductDialogComponent>) { }

  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close({event: 'close'});
  }

  addLocation(): void {
    this.dialogRef.close(this.product);
  }
}
