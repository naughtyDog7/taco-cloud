import {Component, OnInit} from '@angular/core';
import {CartService} from "./cart.service";
import {ApiService} from "../api/api.service";
import {HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  model = {
    name: '',
    street: '',
    city: '',
    phoneNum: '',
    creditCardNum: '',
    tacos: []
  }

  constructor(private cart: CartService, private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  get cartItems() {
    return this.cart.getItemsInCart();
  }

  get cartTotal() {
    return this.cart.getCartTotal();
  }

  onSubmit() {
    this.cart.getItemsInCart().forEach(cartItem =>
      this.model.tacos.push(cartItem));

    this.apiService.post('/orders', this.model, {
      headers: new HttpHeaders().set('Content-type', 'application/json')
        .set('Accept', 'application/json')})
      .subscribe(i => this.cart.emptyCart());
  }

}
