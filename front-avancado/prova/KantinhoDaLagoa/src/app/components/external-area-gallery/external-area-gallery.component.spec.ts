import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalAreaGalleryComponent } from './external-area-gallery.component';

describe('ExternalAreaGalleryComponent', () => {
  let component: ExternalAreaGalleryComponent;
  let fixture: ComponentFixture<ExternalAreaGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExternalAreaGalleryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ExternalAreaGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
