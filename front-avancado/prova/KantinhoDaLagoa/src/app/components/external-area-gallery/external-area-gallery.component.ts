import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Image } from '../../interfaces/image';

@Component({
  selector: 'app-external-area-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './external-area-gallery.component.html',
  styleUrl: './external-area-gallery.component.css'
})
export class ExternalAreaGalleryComponent {

  images: Image[] = [
    { src: 'assets/galleries/external-area-gallery/gate_d1.jpg' },
    { src: 'assets/galleries/external-area-gallery/pool_d1.jpg' },
    { src: 'assets/galleries/external-area-gallery/pool_d2.jpg' },
    { src: 'assets/galleries/external-area-gallery/pool_d3.jpg' },
    { src: 'assets/galleries/external-area-gallery/pool_n1.jpg' },
    { src: 'assets/galleries/external-area-gallery/pool_n2.jpg' },
    { src: 'assets/galleries/external-area-gallery/pool_n3.jpg' },
    { src: 'assets/galleries/external-area-gallery/garden_d1.jpg' },
    { src: 'assets/galleries/external-area-gallery/garden_d2.jpg' },
    { src: 'assets/galleries/external-area-gallery/garden_d3.jpg' },
    { src: 'assets/galleries/external-area-gallery/garden_n1.jpg' },
    { src: 'assets/galleries/external-area-gallery/garden_n3.jpg' },
    { src: 'assets/galleries/external-area-gallery/bar_d1.jpg' },
    { src: 'assets/galleries/external-area-gallery/bar_d2.jpg' },
    { src: 'assets/galleries/external-area-gallery/mini_house_d1.jpg' },
    { src: 'assets/galleries/external-area-gallery/mini_house_d2.jpg' }
    
  ];

}
