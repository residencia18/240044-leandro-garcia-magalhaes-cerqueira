import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableDatesComponent } from './available-dates.component';

describe('AvailableDatesComponent', () => {
  let component: AvailableDatesComponent;
  let fixture: ComponentFixture<AvailableDatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvailableDatesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvailableDatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
