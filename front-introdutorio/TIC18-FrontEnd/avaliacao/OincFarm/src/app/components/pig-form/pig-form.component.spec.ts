import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PigFormComponent } from './pig-form.component';

describe('PigFormComponent', () => {
  let component: PigFormComponent;
  let fixture: ComponentFixture<PigFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PigFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PigFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
