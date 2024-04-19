import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricoAnimalComponent } from './historico-animal.component';

describe('HistoricoAnimalComponent', () => {
  let component: HistoricoAnimalComponent;
  let fixture: ComponentFixture<HistoricoAnimalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistoricoAnimalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HistoricoAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
