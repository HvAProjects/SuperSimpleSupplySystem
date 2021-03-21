import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {Household} from '../../pages/household/household';
import {ProductsLocation} from '../../models/ProductsLocation';
import {LocationService} from '../../services/location.service';

@Component({
  selector: 'app-add-location-dialog',
  templateUrl: './add-location-dialog.component.html',
  styleUrls: ['./add-location-dialog.component.css']
})
export class AddLocationDialogComponent implements OnInit {

  location: ProductsLocation = new ProductsLocation();

  constructor(
    private dialogRef: MatDialogRef<AddLocationDialogComponent>) { }

  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close({event: 'close'});
  }

  addLocation(): void {
    this.dialogRef.close(this.location);
  }
}
