import { Component, OnInit } from '@angular/core';
import {CartService} from "../cart/cart.service";

@Component({
  selector: 'app-taco-header',
  templateUrl: './taco-header.component.html',
  styleUrls: ['./taco-header.component.css']
})
export class TacoHeaderComponent implements OnInit {

  cart: CartService

  constructor(cart: CartService) {
    this.cart = cart;
  }

  ngOnInit(): void {
  }

}
