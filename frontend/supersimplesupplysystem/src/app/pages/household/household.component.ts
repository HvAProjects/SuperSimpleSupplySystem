import { Component, OnInit } from '@angular/core';
import {Household} from './household';
import {HouseholdService} from '../../services/household.service';
import {finalize, tap} from 'rxjs/operators';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-household',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.css']
})
export class HouseholdComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'address', 'postalCode', 'country', 'action'];
  households: Household[];
  dataSource;

  constructor(private householdService: HouseholdService) {
  }

  ngOnInit(): void {
    this.getAllHouseholds();
  }


  public getAllHouseholds(){
    this.householdService.getAllHouseholds().pipe(
      tap( households => {
        this.households = households;
      }),
      finalize(() => {
        this.dataSource = new MatTableDataSource<Household>(this.households);
      })
    ).subscribe();
  }

  public removeHousehold(event){
    this.householdService.deleteHousehold(event.id);
  }

}
