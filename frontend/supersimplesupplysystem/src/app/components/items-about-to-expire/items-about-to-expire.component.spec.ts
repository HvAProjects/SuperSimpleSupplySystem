import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ItemsAboutToExpireComponent} from './items-about-to-expire.component';

describe('ItemsAboutToExpireComponent', () => {
  let component: ItemsAboutToExpireComponent;
  let fixture: ComponentFixture<ItemsAboutToExpireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ItemsAboutToExpireComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemsAboutToExpireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
