import { Injectable } from '@angular/core';
import {CartItem} from './cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  item$: CartItem[] = [];

  constructor() {this.item$ = []}

  addToCart(taco: any) {
    this.item$.push(new CartItem(taco));
  }

  getItemsInCart() {
    return this.item$;
  }

  getCartTotal() {
    let total = 0;
    this.item$.forEach(i => total += i.lineTotal);
    return total;
  }

  emptyCart() {
    this.item$ = [];
  }
}
