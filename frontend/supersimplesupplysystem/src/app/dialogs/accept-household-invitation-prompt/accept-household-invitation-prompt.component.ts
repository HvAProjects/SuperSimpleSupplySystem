import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Household} from '../../pages/household/household';
import {Notification} from '../../models/Notification';

@Component({
  selector: 'app-accept-household-invitation-prompt',
  templateUrl: './accept-household-invitation-prompt.component.html',
  styleUrls: ['./accept-household-invitation-prompt.component.css']
})
export class AcceptHouseholdInvitationPromptComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Notification, private dialogRef: MatDialogRef<AcceptHouseholdInvitationPromptComponent>) { }

  ngOnInit(): void {
  }

  closeDialog(value: boolean): void {
    this.dialogRef.close(value);
  }
}
