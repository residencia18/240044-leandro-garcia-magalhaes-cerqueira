import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableDatesManagerComponent } from './available-dates-manager.component';

describe('AvailableDatesManagerComponent', () => {
  let component: AvailableDatesManagerComponent;
  let fixture: ComponentFixture<AvailableDatesManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvailableDatesManagerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvailableDatesManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
