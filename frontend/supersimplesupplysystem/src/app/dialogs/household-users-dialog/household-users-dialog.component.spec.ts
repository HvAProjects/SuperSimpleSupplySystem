import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseholdUsersDialogComponent } from './household-users-dialog.component';

describe('HouseholdUsersDialogComponent', () => {
  let component: HouseholdUsersDialogComponent;
  let fixture: ComponentFixture<HouseholdUsersDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HouseholdUsersDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseholdUsersDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
