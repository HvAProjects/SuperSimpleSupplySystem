import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {ProductsLocation} from '../../models/ProductsLocation';
import {LocationService} from '../../services/location.service';
import {MatDialog} from '@angular/material/dialog';
import {AddHouseholdDialogComponent} from '../../components/add-household-dialog/add-household-dialog.component';
import {AddLocationDialogComponent} from '../../dialogs/add-location-dialog/add-location-dialog.component';
import {MatTableDataSource} from '@angular/material/table';
import {Household} from '../household/household';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'numberOfProducts'];
  householdId;
  dataSource;

  constructor(
    private route: ActivatedRoute,
    private locationService: LocationService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.householdId = +params.id);
    this.getLocations();
  }

  getLocations(): void {
    this.locationService.getLocationsOfHousehold(this.householdId).subscribe((data: ProductsLocation[]) =>
      this.dataSource = new MatTableDataSource<ProductsLocation>(data));
  }

  removeLocation(location: ProductsLocation): void {

  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddLocationDialogComponent, {
      width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result instanceof ProductsLocation) {
        this.addLocation(result);
      }
    });
  }

  addLocation(location: ProductsLocation): void {
    this.locationService.addLocationToHousehold(this.householdId, location).subscribe(result => this.getLocations());
  }
}
