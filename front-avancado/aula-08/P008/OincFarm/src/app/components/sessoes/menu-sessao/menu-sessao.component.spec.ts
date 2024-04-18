import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSessaoComponent } from './menu-sessao.component';

describe('MenuSessaoComponent', () => {
  let component: MenuSessaoComponent;
  let fixture: ComponentFixture<MenuSessaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuSessaoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuSessaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
