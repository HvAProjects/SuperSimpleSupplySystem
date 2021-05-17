import {ComponentFixture, TestBed} from '@angular/core/testing';

import {YourLocationsComponent} from './your-locations.component';

describe('YourLocationsComponent', () => {
  let component: YourLocationsComponent;
  let fixture: ComponentFixture<YourLocationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [YourLocationsComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YourLocationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
