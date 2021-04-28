import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Notification} from '../../models/Notification';

@Component({
  selector: 'app-scan-another-product-prompt',
  templateUrl: './scan-another-product-prompt.component.html',
  styleUrls: ['./scan-another-product-prompt.component.css']
})
export class ScanAnotherProductPromptComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ScanAnotherProductPromptComponent>) { }

  ngOnInit(): void {
  }

  closeDialog(value: boolean): void {
    this.dialogRef.close(value);
  }

}
