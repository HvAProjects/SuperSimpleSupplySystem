import {AfterViewInit, Component, Inject, OnInit} from '@angular/core';
import {Product} from '../../models/Product';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ProductsLocation} from '../../models/ProductsLocation';
import {ScannerDialogComponent} from '../scanner-dialog/scanner-dialog.component';
import {ProductService} from '../../services/product.service';
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from '@angular/material/snack-bar';
import Quagga from '@ericblade/quagga2';
import {LocationService} from '../../services/location.service';
import {tap} from 'rxjs/operators';

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
  styleUrls: ['./add-product-dialog.component.css']
})
export class AddProductDialogComponent implements OnInit, AfterViewInit {

  product = new Product();
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  householdId: number;
  locationsHousehold: ProductsLocation[];
  selectedLocation: ProductsLocation;

  constructor(
    private productService: ProductService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<AddProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {barcode: string, householdId: number}, private locationService: LocationService) {
    if (data !== null && typeof data.barcode !== 'undefined') {
      this.getProductByEAN(data.barcode);
    }
    this.householdId = this.data.householdId;
    this.retrieveLocationsFromHousehold()
  }

  ngOnInit(): void {
    this.product = new Product();
  }

  ngAfterViewInit(): void {
    this.dialogRef.beforeClosed().subscribe(() => {
      this.dialogRef.close(null);
    });
  }

  closeDialog(): void {
    this.product = null;
    this.dialogRef.close(null);
  }

  addProduct(): void {
    this.dialogRef.close({product: this.product, locationId: this.selectedLocation.id});
  }

  scanProduct(): void {
    const dialogRef = this.dialog.open(ScannerDialogComponent, {
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (typeof result === 'string') {
        this.getProductByEAN(result);
      }
    });
  }

  getProductByEAN(barcode: string): void {
    this.productService.getProductByEAN(barcode).subscribe(
      (res: Product) => {
        if (res !== null) {
          this.product = res;
        } else {
          this.openSnackBar('Product not found!', '');
        }
      },
      (error => {
        this.openSnackBar(error, '');
      })
    );
  }

  public openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  public retrieveLocationsFromHousehold(){
    this.locationService.getLocationsOfHousehold(this.householdId).pipe(
      tap(locations => {
        this.locationsHousehold = locations;
      })
    ).subscribe();

  }
}
