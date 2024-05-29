import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeachGalleryComponent } from './beach-gallery.component';

describe('BeachGalleryComponent', () => {
  let component: BeachGalleryComponent;
  let fixture: ComponentFixture<BeachGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeachGalleryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BeachGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
