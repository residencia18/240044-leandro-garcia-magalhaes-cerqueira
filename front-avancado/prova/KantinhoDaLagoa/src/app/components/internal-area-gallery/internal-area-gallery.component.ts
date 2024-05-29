import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Image } from '../../interfaces/image';

@Component({
  selector: 'app-internal-area-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './internal-area-gallery.component.html',
  styleUrl: './internal-area-gallery.component.css'
})
export class InternalAreaGalleryComponent {

  images: Image[] = [
    { src: 'assets/galleries/internal-area-gallery/living_room_d1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/living_room_d2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/kitchen_d1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/kitchen_d2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_2_d1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_2_d2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bathroom_1_d1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bathroom_1_d2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_d1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_d2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_d3.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_d4.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_n1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_n2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_n3.jpg' },
    { src: 'assets/galleries/internal-area-gallery/bedroom_n4.jpg' },
    { src: 'assets/galleries/internal-area-gallery/balcony_d1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/balcony_d2.jpg' },
    { src: 'assets/galleries/internal-area-gallery/balcony_n1.jpg' },
    { src: 'assets/galleries/internal-area-gallery/balcony_n2.jpg' },

  ];

}
