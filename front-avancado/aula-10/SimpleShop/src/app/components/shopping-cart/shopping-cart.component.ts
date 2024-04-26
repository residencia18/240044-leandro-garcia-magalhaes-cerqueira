//Shopping-cart.component.ts

import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared/shared.module';
import { RouterModule } from '@angular/router';
import { CartService } from '../../shared/cart.service';
import { FormsModule } from '@angular/forms';
import { Item } from './Item';
import { FooterComponent } from "../footer/footer.component";


@Component({
    selector: 'app-shopping-cart',
    standalone: true,
    templateUrl: './shopping-cart.component.html',
    styleUrl: './shopping-cart.component.css',
    imports: [RouterModule, CommonModule, SharedModule, FormsModule, FooterComponent]
})
export class ShoppingCartComponent {

    // qtyEff = effect(() => console.log("Latest quantity: ", this.quantity()));

    // teste = effect(() => this.subtotal = computed(() => this.cartItems().reduce((acc, item) =>
    //     acc + ( item.price * (item.quantity * this.quantity())),0)));

    cartService = inject(CartService);

    // quantity = signal(1);

    qtyAvaible = signal([1,2,3,4,5]);

    // onQuantitySelect(qty: number){
    //     this.quantity.set(qty);
    // }

    updateQuantity(item: Item, newQuantity: number) {
        this.cartService.updateInCart({ ...item, quantity: newQuantity });
      }
      
    

    cartItems = this.cartService.cartItems;


    deleteItem(item : any): void {
        this.cartService.removeFromCart(item); 
    }


    subtotal = this.cartService.subtotal;
      

    
}
