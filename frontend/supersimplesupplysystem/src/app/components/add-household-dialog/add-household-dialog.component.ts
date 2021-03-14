import {Component, ElementRef, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {Household} from '../../pages/household/household';
import {HouseholdService} from '../../services/household.service';
import {Country} from '@angular-material-extensions/select-country';

@Component({
  selector: 'app-add-household-dialog',
  templateUrl: './add-household-dialog.component.html',
  styleUrls: ['./add-household-dialog.component.css'],
})
export class AddHouseholdDialogComponent implements OnInit {
  countryFormControl = new FormControl('', [
    Validators.required,
    Validators.minLength(1)
  ]);

  @ViewChild('addForm') public addForm: NgForm;
  household: Household = new Household();


  constructor(public dialogRef: MatDialogRef<AddHouseholdDialogComponent>, public householdService: HouseholdService) {
  }

  ngOnInit(): void {
  }

  addHousehold(){
    this.householdService.addHousehold(this.household).subscribe();
  }

  closeDialog() {
    this.dialogRef.close({event: 'close'});
  }

  onCountrySelected($event: Country) {
    this.household.country = $event.name;
  }




}
