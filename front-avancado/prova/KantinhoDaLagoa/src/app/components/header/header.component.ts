import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IPlan } from '../../interfaces/plan';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  cart: IPlan[] = [];
  isCartOpen: boolean = false;

  constructor(private cartService: CartService) {
    this.cartService.cart$.subscribe(cart => this.cart = cart);
  }

  toggleCart(): void {
    if (this.cart.length == 0) {
      return;
    } else {
      this.isCartOpen = !this.isCartOpen;
    }
  }

  clearCart(): void {
    this.cartService.clearCart();
    this.isCartOpen = false;
  }

  getTotal(): number {
    return this.cartService.getTotal();
  }
}
