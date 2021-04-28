import {Component, Inject, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Product} from '../../models/Product';

@Component({
  selector: 'app-add-or-remove-products-dialog',
  templateUrl: './add-or-remove-products-dialog.component.html',
  styleUrls: ['./add-or-remove-products-dialog.component.css']
})
export class AddOrRemoveProductsDialogComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService,
              private dialogRef: MatDialogRef<AddOrRemoveProductsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {
                barcode: string,
                householdId: number
              }) {
    this.productService.getProductsWithBarcode(data.barcode, data.householdId).subscribe(res => {
      console.log(res);
      this.products = res;
    });
  }

  ngOnInit(): void {
  }

}
