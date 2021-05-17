import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Household} from '../../pages/household/household';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {HouseholdService} from '../../services/household.service';
import {MatDialog} from '@angular/material/dialog';
import {tap} from 'rxjs/operators';
import {ProductsLocation} from '../../models/ProductsLocation';

@Component({
  selector: 'app-your-locations',
  templateUrl: './your-locations.component.html',
  styleUrls: ['./your-locations.component.css']
})
export class YourLocationsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['id', 'name', 'household', 'remove'];
  dataSource: MatTableDataSource<ProductsLocation>;
  households: Household[];
  locations: Location[];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private householdService: HouseholdService, private dialog: MatDialog) {
    this.getAllHouseholds();
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getAllHouseholds())
      )
      .subscribe();
  }

  getAllHouseholds() {
    this.householdService.getAllHouseholds().pipe(
      tap(result => {
        console.log(result);
        this.households = result;
      })
    ).subscribe();
  }

  // this.dataSource = new MatTableDataSource<ProductsLocation>(this.households);
  // this.dataSource.paginator = this.paginator;
  // this.dataSource.sort = this.sort;


}
