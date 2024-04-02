import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarPesosComponent } from './listar-pesos.component';

describe('ListarPesosComponent', () => {
  let component: ListarPesosComponent;
  let fixture: ComponentFixture<ListarPesosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarPesosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListarPesosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
