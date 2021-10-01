import {Component, OnInit} from '@angular/core';
import {HouseholdService} from './services/household.service';
import {Household} from './pages/household/household';
import {tap} from 'rxjs/operators';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {ScannerDialogComponent} from './dialogs/scanner-dialog/scanner-dialog.component';
import {AddOrRemoveProductsDialogComponent} from './dialogs/add-or-remove-products-dialog/add-or-remove-products-dialog.component';
import {ScanAnotherProductPromptComponent} from './dialogs/scan-another-product-prompt/scan-another-product-prompt.component';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './app.component.scss']
})
export class AppComponent implements OnInit {
  private roles: string[];
  households: Household[];
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;
  collapsed: any;
  houseHoldLink = '/household/';
  householdSettingsLink = '/household-settings/';

  constructor(public auth: AuthService, private householdService: HouseholdService, private dialog: MatDialog,
              private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = function() {
      return false;
    };
  }

  ngOnInit(): void {
    this.getAllHouseholds();

    console.log(this.households);

  }

  getAllHouseholds(){
    this.householdService.getAllHouseholds().pipe(
      tap( households => {
        this.households = households;
      })
    ).subscribe();
  }

  openScanner(householdId: number): void {
    const dialogRef = this.dialog.open(ScannerDialogComponent, {
      // width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (typeof result === 'string') {
        this.openAddOrRemoveProductsDialog(result, householdId);
      }
    });
  }

  openAddOrRemoveProductsDialog(barcode: string, householdId: number): void {
    const dialogRef = this.dialog.open(AddOrRemoveProductsDialogComponent, {
      // width  : '380px',
      disableClose: false,
      data: {
        barcode,
        householdId
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.promptScanAnotherProduct(householdId);
      }
    });
  }

  promptScanAnotherProduct(householdId): void {
    const dialogRef = this.dialog.open(ScanAnotherProductPromptComponent, {
      // width  : '380px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.openScanner(householdId);
      }
    });
  }
}
