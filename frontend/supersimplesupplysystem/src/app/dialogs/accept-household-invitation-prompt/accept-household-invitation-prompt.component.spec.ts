import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptHouseholdInvitationPromptComponent } from './accept-household-invitation-prompt.component';

describe('AcceptHouseholdInvitationPromptComponent', () => {
  let component: AcceptHouseholdInvitationPromptComponent;
  let fixture: ComponentFixture<AcceptHouseholdInvitationPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptHouseholdInvitationPromptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptHouseholdInvitationPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
