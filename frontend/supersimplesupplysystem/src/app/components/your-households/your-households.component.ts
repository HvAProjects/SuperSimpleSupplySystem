import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Household} from '../../pages/household/household';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {HouseholdService} from '../../services/household.service';
import {finalize, tap} from 'rxjs/operators';
import {AddHouseholdDialogComponent} from '../add-household-dialog/add-household-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-your-households',
  templateUrl: './your-households.component.html',
  styleUrls: ['./your-households.component.css']
})
export class YourHouseholdsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['id', 'name', 'address', 'leave'];
  dataSource: MatTableDataSource<Household>;
  households: Household[];

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
        this.dataSource = new MatTableDataSource<Household>(this.households);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      })
    ).subscribe();
  }

  addNewHousehold() {
    const dialogRef = this.dialog.open(AddHouseholdDialogComponent, {
      width: '380px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllHouseholds();
      window.location.reload();
    });
  }

  leaveHousehold(event): void {
    this.householdService.leaveHousehold(event.id).pipe(
      tap(() => {
        console.log('Household ' + event.name + ' was deleted!');
      }),
      finalize(() => {
        this.getAllHouseholds();
        window.location.reload();
      })
    ).subscribe();
  }

}
