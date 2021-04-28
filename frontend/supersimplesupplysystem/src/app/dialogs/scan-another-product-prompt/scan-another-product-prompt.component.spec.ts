import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScanAnotherProductPromptComponent } from './scan-another-product-prompt.component';

describe('ScanAnotherProductPromptComponent', () => {
  let component: ScanAnotherProductPromptComponent;
  let fixture: ComponentFixture<ScanAnotherProductPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScanAnotherProductPromptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScanAnotherProductPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
