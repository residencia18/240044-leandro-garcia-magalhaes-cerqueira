  import { Injectable } from '@angular/core';
  import { BehaviorSubject } from 'rxjs';
  import { IPlan } from '../interfaces/plan';

  @Injectable({
    providedIn: 'root'
  })
  export class CartService {
    private cart: IPlan[] = [];
    private cartSubject = new BehaviorSubject<IPlan[]>([]);

    cart$ = this.cartSubject.asObservable();

    addToCart(plan: IPlan): void {
      this.cart.push(plan);
      this.cartSubject.next(this.cart);
    }

    getCart(): IPlan[] {
      return this.cart;
    }

    getTotal(): number {
      return this.cart.reduce((acc, plan) => acc + plan.price * plan.selectedDays, 0);
    }

    clearCart(): void {
      this.cart = [];
      this.cartSubject.next(this.cart);
    }
  }
