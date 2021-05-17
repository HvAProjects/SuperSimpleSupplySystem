import {ComponentFixture, TestBed} from '@angular/core/testing';

import {YourHouseholdsComponent} from './your-households.component';

describe('YourHouseholdsComponent', () => {
  let component: YourHouseholdsComponent;
  let fixture: ComponentFixture<YourHouseholdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [YourHouseholdsComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YourHouseholdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
