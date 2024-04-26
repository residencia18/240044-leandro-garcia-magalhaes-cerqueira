//Cart.service.ts

import { Injectable, computed, signal } from '@angular/core';
import { Item } from '../components/shopping-cart/Item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  
cartItems = signal<Item[]>([]);

subtotal = computed(() => this.cartItems().reduce((acc, item) =>
  acc + (item.quantity * item.price), 0));

addToCart(item: Item): void {
  this.cartItems.update(items => [...items, item]);
}

removeFromCart(cartItem: Item): void {
  this.cartItems.update(items => items.filter(item =>
    item.name !== cartItem.name));
}

updateInCart(cartItem: Item) {
  this.cartItems.update(items =>
    items.map(item => item.name === cartItem.name ? { ...item, quantity: cartItem.quantity } : item));
}
  
}
