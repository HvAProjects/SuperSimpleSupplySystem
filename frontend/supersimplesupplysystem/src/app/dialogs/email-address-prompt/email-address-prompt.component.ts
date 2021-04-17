import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-email-address-prompt',
  templateUrl: './email-address-prompt.component.html',
  styleUrls: ['./email-address-prompt.component.css']
})
export class EmailAddressPromptComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<EmailAddressPromptComponent>) { }

  email: string;

  ngOnInit(): void {
  }

  emailValid(): void {
    this.closeDialog(this.email);
  }

  closeDialog(value): void {
    this.dialogRef.close(value);
  }
}
