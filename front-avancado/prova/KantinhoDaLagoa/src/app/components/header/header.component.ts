import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { IPlan } from '../../interfaces/plan';
import { CartService } from '../../services/cart.service';
import { inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../security/authentication/authentication.component';

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

  constructor(private cartService: CartService, private router : Router) {
    this.cartService.cart$.subscribe(cart => this.cart = cart);
  }

  authService = inject(AuthService)

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


  ngOnInit(): void {
     this.authService.user$.subscribe(user => {
      if (user){
        this.authService.currentUserSig.set({
          email: user.email!,
          username: user.displayName!,
        });
      } else {
        this.authService.currentUserSig.set(null);
      }
      console.log(this.authService.currentUserSig())
     }) 
  }


  goToHome() {
    this.router.navigate(['/home']);
  }
    title = 'OincFarm';

  logout(): void {
      this.authService.logout();
      this.router.navigate(['/authentication']);
  }

}
