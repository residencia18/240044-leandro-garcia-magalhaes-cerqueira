import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaSessoesComponent } from './lista-sessoes.component';

describe('ListaSessoesComponent', () => {
  let component: ListaSessoesComponent;
  let fixture: ComponentFixture<ListaSessoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaSessoesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListaSessoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
