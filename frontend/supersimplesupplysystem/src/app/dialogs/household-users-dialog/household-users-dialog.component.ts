import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {HouseholdService} from '../../services/household.service';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import {Product} from '../../models/Product';
import {User} from '../../pages/profile/user';
import {Household} from '../../pages/household/household';
import {MatSelectionList} from '@angular/material/list';
import {TokenStorageService} from '../../services/token-storage.service';
import {faPlus} from '@fortawesome/free-solid-svg-icons/faPlus';
import {faMinus} from '@fortawesome/free-solid-svg-icons';
import {ScannerDialogComponent} from '../scanner-dialog/scanner-dialog.component';
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from '@angular/material/snack-bar';
import {NotificationService} from '../../services/notification.service';
import {EmailAddressPromptComponent} from '../email-address-prompt/email-address-prompt.component';

@Component({
  selector: 'app-household-users-dialog',
  templateUrl: './household-users-dialog.component.html',
  styleUrls: ['./household-users-dialog.component.css']
})
export class HouseholdUsersDialogComponent implements OnInit {

  constructor(private householdService: HouseholdService,
              private tokenStorageService: TokenStorageService,
              private snackBar: MatSnackBar,
              private notificationService: NotificationService,
              private dialog: MatDialog,
              @Inject(MAT_DIALOG_DATA) public data: Household) { }

  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  faPlus = faPlus;
  faMinus = faMinus;

  users;
  selectedUser = null;

  userSelected = false;

  @ViewChild('userList', { static: true }) userList: MatSelectionList;

  ngOnInit(): void {
    this.getUsers();
    this.userList.selectionChange.subscribe(res => {
      this.selectedUser = this.userList.selectedOptions.selected[0]?.value;
      this.userSelected = true;
    });
  }

  getUsers(): void {
    this.householdService.getUsersOfHousehold(this.data.id).subscribe(res => {
      this.users = res;
    });
  }

  isSelectionValid(): boolean {
    return this.userSelected && this.selectedUser?.id !== parseInt(this.tokenStorageService.getUser().id, 0);
  }

  openAddUserDialog(): void {
    const dialogRef = this.dialog.open(EmailAddressPromptComponent, {
      // width  : '380px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (typeof result === 'string') {
        this.notificationService.inviteUserToHousehold(result, this.data.id).subscribe(
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

  private openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
}
