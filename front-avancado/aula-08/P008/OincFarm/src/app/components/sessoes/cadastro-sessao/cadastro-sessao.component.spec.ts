import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroSessaoComponent } from './cadastro-sessao.component';

describe('CadastroSessaoComponent', () => {
  let component: CadastroSessaoComponent;
  let fixture: ComponentFixture<CadastroSessaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroSessaoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CadastroSessaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
