import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHouseholdDialogComponent } from './add-household-dialog.component';

describe('AddHouseholdDialogComponent', () => {
  let component: AddHouseholdDialogComponent;
  let fixture: ComponentFixture<AddHouseholdDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddHouseholdDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddHouseholdDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
