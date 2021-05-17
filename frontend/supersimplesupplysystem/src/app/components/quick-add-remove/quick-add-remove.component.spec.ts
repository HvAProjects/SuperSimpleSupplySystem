import {ComponentFixture, TestBed} from '@angular/core/testing';

import {QuickAddRemoveComponent} from './quick-add-remove.component';

describe('QuickAddRemoveComponent', () => {
  let component: QuickAddRemoveComponent;
  let fixture: ComponentFixture<QuickAddRemoveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuickAddRemoveComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuickAddRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
