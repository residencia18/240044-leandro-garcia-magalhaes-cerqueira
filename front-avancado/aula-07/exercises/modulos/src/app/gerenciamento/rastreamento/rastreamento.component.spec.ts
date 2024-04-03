import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RastreamentoComponent } from './rastreamento.component';

describe('RastreamentoComponent', () => {
  let component: RastreamentoComponent;
  let fixture: ComponentFixture<RastreamentoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RastreamentoComponent]
    });
    fixture = TestBed.createComponent(RastreamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
