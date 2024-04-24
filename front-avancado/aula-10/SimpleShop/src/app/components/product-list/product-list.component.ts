import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Item } from '../shopping-cart/Item';
import { HeaderComponent } from "../header/header.component";
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-product-list',
    standalone: true,
    templateUrl: './product-list.component.html',
    styleUrl: './product-list.component.css',
    imports: [CommonModule, HeaderComponent, RouterModule]
})
export class ProductListComponent {

  ngOnInit(): void {
    console.log("PRODUCT LIST COMPONENT CALLED!");
}


  shoppingCartItens: Item[] = [

    {id: 1, name: 'Grand Theft Auto: San Andreas', price: 25.99, quantity: 1, imgURL: 'https://i.pinimg.com/736x/da/83/b5/da83b5be6c980836684ebfd0d935eabc.jpg'},
    {id: 2, name: 'God Of War II', price: 34.99, quantity: 1, imgURL: 'https://cdn2.spong.com/pack/g/o/godofwar2237772l/_-God-of-War-2-PS2-_.jpg'},
    {id: 3, name: 'Dragon Ball Z: Budokai Tenkaichi 3', price: 18.99, quantity: 1, imgURL: 'https://i.pinimg.com/736x/0e/57/68/0e57686878934966edba7499cd39ef3f.jpg'},
    {id: 4, name: 'Need for Speed: Most Wanted 2005', price: 16.99, quantity: 1, imgURL: 'https://i.pinimg.com/736x/9c/73/06/9c73062c409be31b1483086f76ba7152.jpg'},
    {id: 5, name: 'Bully', price: 19.99, quantity: 1, imgURL: 'https://i.pinimg.com/736x/4c/c4/7d/4cc47dd2ef2dc9e4759fea9a41cfbc2e.jpg'},
    {id: 6, name: 'Ben 10: Protector of Earth', price: 15.99, quantity: 1, imgURL: 'https://cdn.mobygames.com/covers/6505742-ben-10-protector-of-earth-playstation-2-front-cover.jpg'},
    {id: 7, name: 'Naruto: Ultimate Ninja 5', price: 17.99, quantity: 1, imgURL: 'https://cdn1.spong.com/pack/n/a/narutoship313441l/_-Naruto-Shippuden-Ultimate-Ninja-5-PS2-_.jpg'},
    {id: 8, name: 'Guitar Hero III: Legends of Rock', price: 13.99, quantity: 1, imgURL: 'https://cdromance.org/wp-content/uploads/2015/05/guitar-hero-iii-legends-of-rock-coverart.jpg'},
    {id: 9, name: 'God Hand', price: 9.99, quantity: 1, imgURL: 'https://upload.wikimedia.org/wikipedia/pt/a/a8/God_Hand_-_Arte_da_Capa_-_Europa.jpg'},
    {id: 10, name: 'PES 2014', price: 12.99, quantity: 1, imgURL: 'https://cdn0.spong.com/pack/p/e/pes2014404515l/_-PES-2014-PS2-_.jpg'}, 
    
  ];

  itensList = signal<Item[]>(this.shoppingCartItens);

}
