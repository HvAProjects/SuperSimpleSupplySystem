import {Component, OnInit, ViewChild} from '@angular/core';
import {finalize, tap} from 'rxjs/operators';
import {AddHouseholdDialogComponent} from '../../components/add-household-dialog/add-household-dialog.component';
import {HouseholdUsersDialogComponent} from '../../dialogs/household-users-dialog/household-users-dialog.component';
import {HouseholdService} from '../../services/household.service';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Params} from '@angular/router';
import {Household} from '../household/household';
import {Country} from '@angular-material-extensions/select-country';
import {FormControl, NgForm, Validators} from '@angular/forms';
import {EmailAddressPromptComponent} from '../../dialogs/email-address-prompt/email-address-prompt.component';
import {NotificationService} from '../../services/notification.service';
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from '@angular/material/snack-bar';
import {TokenStorageService} from '../../services/token-storage.service';

@Component({
  selector: 'app-household-settings',
  templateUrl: './household-settings.component.html',
  styleUrls: ['./household-settings.component.css']
})
export class HouseholdSettingsComponent implements OnInit {
  householdId;
  household: Household;
  disabledFields: boolean = true;
  users;
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  selectedUser = null;
  userSelected = false;

  countryFormControl = new FormControl('', [
    Validators.required,
    Validators.minLength(1)
  ]);

  @ViewChild('addForm') public addForm: NgForm;

  constructor(private householdService: HouseholdService, private dialog: MatDialog, private route: ActivatedRoute,
              private notificationService: NotificationService, private snackBar: MatSnackBar,
              private tokenStorageService: TokenStorageService) {
    this.route.params.subscribe((params: Params) => this.householdId = +params.householdId);
    this.getHousehold(this.householdId);

    console.log(this.household);
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.householdService.getUsersOfHousehold(this.householdId).subscribe(res => {
      this.users = res;
    });
  }

  leaveHousehold(event): void {
    this.householdService.leaveHousehold(event.id).pipe(
      tap(() => {
        console.log('Household ' + event.name + ' was deleted!');
      }),
      finalize(() => {
        // this.getAllHouseholds();
      })
    ).subscribe();
  }

  openDialog() {
    const dialogRef = this.dialog.open(AddHouseholdDialogComponent, {
      width: '380px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.getAllHouseholds();
    });
  }


  openUserDialog(element): void {
    const dialogRef = this.dialog.open(HouseholdUsersDialogComponent, {
      width: '380px',
      disableClose: false,
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {

    });
  }

  onCountrySelected($event: Country) {
    console.log($event.name);
    this.household.country = $event.name;
  }

  getHousehold(id: number) {
    this.householdService.getHousehold(id).pipe(
      tap((result) => {
        this.household = result;
        console.log(result);
      })
    ).subscribe();
  }

  saveEditedHousehold() {
    this.householdService.editHousehold(this.householdId, this.household).subscribe(() => {
      window.location.reload();
    });
  }

  openAddUserDialog(): void {
    const dialogRef = this.dialog.open(EmailAddressPromptComponent, {
      // width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (typeof result === 'string') {
        this.notificationService.inviteUserToHousehold(result, this.householdId).subscribe(
          (res) => {
            this.openSnackBar(res.message, '');
          },
          (error => {
            this.openSnackBar('Something went horribly wrong!', '');
          })
        );
      }
    });
  }

  isSelectionValid(): boolean {
    return this.userSelected && this.selectedUser?.id !== parseInt(this.tokenStorageService.getUser().id, 0);
  }

  private openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

}
