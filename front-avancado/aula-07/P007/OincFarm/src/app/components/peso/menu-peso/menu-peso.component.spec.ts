import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuPesoComponent } from './menu-peso.component';

describe('MenuPesoComponent', () => {
  let component: MenuPesoComponent;
  let fixture: ComponentFixture<MenuPesoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuPesoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuPesoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
