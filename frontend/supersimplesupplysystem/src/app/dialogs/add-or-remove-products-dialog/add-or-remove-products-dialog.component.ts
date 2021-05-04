import {Component, Inject, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Product} from '../../models/Product';
import {AddProductDialogComponent} from '../add-product-dialog/add-product-dialog.component';

@Component({
  selector: 'app-add-or-remove-products-dialog',
  templateUrl: './add-or-remove-products-dialog.component.html',
  styleUrls: ['./add-or-remove-products-dialog.component.css']
})
export class AddOrRemoveProductsDialogComponent implements OnInit {

  products: Product[];
  householdId: number;

  constructor(private productService: ProductService,
              private dialogRef: MatDialogRef<AddOrRemoveProductsDialogComponent>,
              private dialog: MatDialog,
              @Inject(MAT_DIALOG_DATA) public data: {
                barcode: string,
                householdId: number
              }) {
    this.productService.getProductsWithBarcode(data.barcode, data.householdId).subscribe(res => {
      this.products = res;
    });
    this.householdId = data.householdId;
  }

  ngOnInit(): void {
  }

  openAddProductDialog(): void {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width  : '380px',
      disableClose: false,
      data: {barcode : this.data.barcode, householdId: this.data.householdId}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== null) {
        console.error('TODO: product toevoegen');
        console.error('Gaat nog gewijzigd worden ivm nieuwe manier van producten listen');
        this.dialogRef.close(true);
      }
    });
  }

  prettyPrintDate(date: string): string {
    return new Date(date).toLocaleDateString();
  }

  removeProduct(product: Product): void {
    this.productService.deleteProducts(product.id, 1).subscribe();
    this.dialogRef.close(true);
  }
}
