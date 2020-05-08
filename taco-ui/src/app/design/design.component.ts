import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {CartService} from '../cart/cart.service';

@Component({
  selector: 'app-design',
  templateUrl: './design.component.html',
  styleUrls: ['./design.component.css']
})

@Injectable()
export class DesignComponent implements OnInit{

  model = {
    name: '',
    ingredients: []
  };

  allIngredients: any;
  wraps = [];
  proteins = [];
  veggies = [];
  cheeses = [];
  sauces = [];

  constructor(private httpClient: HttpClient, private router: Router, private cart: CartService) { }

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8081/ingredients')
      .subscribe(data =>
        this.allIngredients = data);
    this.wraps = this.allIngredients.filter(i => i.type === 'WRAP');
    this.proteins = this.allIngredients.filter(i => i.type === 'PROTEIN');
    this.veggies = this.allIngredients.filter(i => i.type === 'VEGGIES');
    this.cheeses = this.allIngredients.filter(i => i.type === 'CHEESE');
    this.sauces = this.allIngredients.filter(i => i.type === 'SAUCE');
  }

  updateIngredients(ingredient, event) {
    if (event.target.checked) {
      this.model.ingredients.push(ingredient);
    } else {
      this.model.ingredients.splice(this.model.ingredients.findIndex(i => i === ingredient), 1);
    }
  }

  onSubmit() {
    this.httpClient.post(
      'http://localhost:8081/design',
      this.model, {
        headers: new HttpHeaders().set('Content-type', 'application-json'),
      }).subscribe(taco => this.cart.addToCart(taco));

    this.router.navigate(['/cart']);
  }

}
