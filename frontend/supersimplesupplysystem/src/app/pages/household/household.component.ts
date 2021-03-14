import { Component, OnInit } from '@angular/core';
import {Household} from './household';
import {HouseholdService} from '../../services/household.service';
import {finalize, tap} from 'rxjs/operators';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import {AddHouseholdDialogComponent} from '../../components/add-household-dialog/add-household-dialog.component';

@Component({
  selector: 'app-household',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.css']
})
export class HouseholdComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'address', 'postalCode', 'country', 'action'];
  households: Household[];
  dataSource;

  constructor(private householdService: HouseholdService, private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getAllHouseholds();
  }


  getAllHouseholds(){
    this.householdService.getAllHouseholds().pipe(
      tap( households => {
        this.households = households;
      }),
      finalize(() => {
        this.dataSource = new MatTableDataSource<Household>(this.households);
      })
    ).subscribe();
  }

  removeHousehold(event){
    this.householdService.deleteHousehold(event.id).pipe(
      tap(()=> {
        console.log('Household ' + event.name + ' was deleted!')
      }),
      finalize(()=>{
        this.getAllHouseholds();
      })
    ).subscribe()

  }

  openDialog() {
    const dialogRef = this.dialog.open(AddHouseholdDialogComponent, {
      width  : '380px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllHouseholds();
    });
  }


}
