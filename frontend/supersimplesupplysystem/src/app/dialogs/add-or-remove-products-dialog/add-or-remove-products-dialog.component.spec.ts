import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrRemoveProductsDialogComponent } from './add-or-remove-products-dialog.component';

describe('AddOrRemoveProductsDialogComponent', () => {
  let component: AddOrRemoveProductsDialogComponent;
  let fixture: ComponentFixture<AddOrRemoveProductsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrRemoveProductsDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOrRemoveProductsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
