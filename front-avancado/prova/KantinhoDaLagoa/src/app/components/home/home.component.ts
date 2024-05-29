import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { IPlan } from '../../interfaces/plan';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, CarouselModule, FormsModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {

  constructor(private cartService: CartService) { }

  plans: IPlan[] = [
    {
      type: "basic",
      imageUrl: 'https://via.placeholder.com/150',
      title: 'Plano básico',
      description:
        'Este é um plano básico, você vai ter acesso a todos os recursos básicos.',
      price: 150.0,
      selectedDays: 1,
    },
    {
      type: "intermediate",
      imageUrl: 'https://via.placeholder.com/150',
      title: 'Plano intermediário',
      description:
        'Este é um plano intermediário, você vai ter acesso a todos os recursos intermediários.',
      price: 250.0,
      selectedDays: 1,
    },
    {
      type: "advanced",
      imageUrl: 'https://via.placeholder.com/150',
      title: 'Plano avançado',
      description:
        'Este é um plano avançado, você vai ter acesso a todos os recursos avançados.',
      price: 350.0,
      selectedDays: 1,
    },
  ];

  addToCart(plan: any): void {
    this.cartService.addToCart(plan);
  }
}
