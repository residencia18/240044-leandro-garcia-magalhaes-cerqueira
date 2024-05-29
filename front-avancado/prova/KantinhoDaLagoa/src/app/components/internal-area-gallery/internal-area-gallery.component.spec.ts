import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalAreaGalleryComponent } from './internal-area-gallery.component';

describe('InternalAreaGalleryComponent', () => {
  let component: InternalAreaGalleryComponent;
  let fixture: ComponentFixture<InternalAreaGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InternalAreaGalleryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InternalAreaGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
