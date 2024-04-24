import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ProductListComponent } from '../product-list/product-list.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, ProductListComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(private router: Router) {}

  goToCart() {
    this.router.navigate(['cart']);
  }


}
