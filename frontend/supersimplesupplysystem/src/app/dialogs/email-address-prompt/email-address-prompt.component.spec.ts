import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailAddressPromptComponent } from './email-address-prompt.component';

describe('EmailAddressPromptComponent', () => {
  let component: EmailAddressPromptComponent;
  let fixture: ComponentFixture<EmailAddressPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmailAddressPromptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailAddressPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
