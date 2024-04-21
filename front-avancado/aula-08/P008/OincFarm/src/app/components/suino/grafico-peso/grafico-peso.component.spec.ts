import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraficoPesoComponent } from './grafico-peso.component';

describe('GraficoPesoComponent', () => {
  let component: GraficoPesoComponent;
  let fixture: ComponentFixture<GraficoPesoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraficoPesoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GraficoPesoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
