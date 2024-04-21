import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarSuinosComponent } from './listar-suinos.component';

describe('ListarSuinosComponent', () => {
  let component: ListarSuinosComponent;
  let fixture: ComponentFixture<ListarSuinosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarSuinosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListarSuinosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
