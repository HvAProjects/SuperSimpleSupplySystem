import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from './services/token-storage.service';
import {HouseholdService} from './services/household.service';
import {Household} from './pages/household/household';
import {finalize, tap} from 'rxjs/operators';
import {log} from 'util';
import {MatTableDataSource} from '@angular/material/table';
import {AddHouseholdDialogComponent} from './components/add-household-dialog/add-household-dialog.component';
import {HouseholdUsersDialogComponent} from './dialogs/household-users-dialog/household-users-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './app.component.scss']
})
export class AppComponent implements OnInit {
  private roles: string[];
  households: Household[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;
  collapsed: any;
  houseHoldLink = '/household';

  constructor(private tokenStorageService: TokenStorageService, private householdService: HouseholdService, private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.checkLoginStatus();
    this.getAllHouseholds();

    console.log(this.households)

  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.checkLoginStatus();
  }

  checkLoginStatus(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.displayName;
    }
  }

  getAllHouseholds(){
    this.householdService.getAllHouseholds().pipe(
      tap( households => {
        this.households = households;
      })
    ).subscribe();
  }


  leaveHousehold(event): void {
    this.householdService.leaveHousehold(event.id).pipe(
      tap(() => {
        console.log('Household ' + event.name + ' was deleted!');
      }),
      finalize(() =>{
        this.getAllHouseholds();
      })
    ).subscribe();
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


  openUserDialog(element): void {
    const dialogRef = this.dialog.open(HouseholdUsersDialogComponent, {
      width  : '380px',
      disableClose: false,
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {

    });
  }

}
