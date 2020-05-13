import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {CartService} from '../cart/cart.service';
import {ApiService} from "../api/api.service";
import {map} from "rxjs/operators";
import {User} from "../model/user";
import {Ingredient} from "../model/ingredient";
import {Taco} from "../model/taco";

@Component({
  selector: 'app-design',
  templateUrl: './design.component.html',
  styleUrls: ['./design.component.css']
})

@Injectable()
export class DesignComponent implements OnInit{

  model = new Taco();
  allIngredients: Ingredient[];
  wraps: Ingredient[];
  proteins: Ingredient[];
  veggies: Ingredient[];
  cheeses: Ingredient[];
  sauces: Ingredient[];

  constructor(private apiService: ApiService, private httpClient: HttpClient, private router: Router, private cart: CartService) { }

  ngOnInit(): void {
    this.apiService.get('/ingredients').subscribe((data:any) => {
      this.allIngredients = <Ingredient[]>(data._embedded.ingredients);
      this.wraps = this.allIngredients.filter(i => i.type === 'WRAP');
      this.proteins = this.allIngredients.filter(i => i.type === 'PROTEIN');
      this.veggies = this.allIngredients.filter(i => i.type === 'VEGGIES');
      this.cheeses = this.allIngredients.filter(i => i.type === 'CHEESE');
      this.sauces = this.allIngredients.filter(i => i.type === 'SAUCE');
    })
  }

  updateIngredients(ingredient, event) {
    if (event.target.checked) {
      this.model.ingredients.push(ingredient as Ingredient);
    } else {
      this.model.ingredients.splice(this.model.ingredients.findIndex(i => i === ingredient), 1);
    }
  }

  onSubmit() {
    console.log('current taco ' + JSON.stringify(this.model));
    this.apiService.post<Taco>(
      '/tacos',
      this.model, {
        headers: new HttpHeaders().set('Content-type', 'application/json').set('Access-Control-Allow-Origin', '*'),
      }).subscribe(taco => this.cart.addToCart(taco));

    this.router.navigate(['/cart']);
  }
}

