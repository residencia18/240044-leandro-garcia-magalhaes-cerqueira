import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ShoppingCartComponent } from "./components/shopping-cart/shopping-cart.component";
import { SharedModule } from "./shared/shared/shared.module";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, ProductListComponent, ShoppingCartComponent, SharedModule]
})
export class AppComponent {
  title = 'SimpleShop';
}
