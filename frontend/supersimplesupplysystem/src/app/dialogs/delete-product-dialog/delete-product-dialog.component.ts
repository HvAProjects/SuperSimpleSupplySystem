import {AfterViewInit, Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Product} from '../../models/Product';

@Component({
  selector: 'app-delete-product-dialog',
  templateUrl: './delete-product-dialog.component.html',
  styleUrls: ['./delete-product-dialog.component.css']
})
export class DeleteProductDialogComponent implements OnInit, AfterViewInit {

  public amount = 0;
  product: Product;

  constructor(private dialogRef: MatDialogRef<DeleteProductDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Product) {
    this.product = data;
  }

  ngAfterViewInit(): void {
    this.dialogRef.beforeClosed().subscribe(() => {
      this.dialogRef.close(null);
    });
  }

  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close(null);
  }

  deleteProducts(): void {
    this.dialogRef.close(this.amount);
  }
}
