import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormControl, NgForm, Validators} from '@angular/forms';
import {Household} from '../../pages/household/household';
import {HouseholdService} from '../../services/household.service';

@Component({
  selector: 'app-add-household-dialog',
  templateUrl: './add-household-dialog.component.html',
  styleUrls: ['./add-household-dialog.component.css'],
})
export class AddHouseholdDialogComponent implements OnInit {
  textValidator = new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]);

  @ViewChild('addForm') public addForm: NgForm;

  household: Household = new Household();


  textControl = new FormControl('', [
    Validators.required,
    Validators.minLength(4)
  ]);

  constructor(public dialogRef: MatDialogRef<AddHouseholdDialogComponent>, public householdService: HouseholdService) { }

  ngOnInit(): void {
  }

  addHousehold(){
    this.householdService.addHousehold(this.household).subscribe();
  }

  closeDialog() {
    this.dialogRef.close({event: 'close'});
  }



}
