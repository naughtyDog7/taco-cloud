import {Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {RecentsComponent} from "./recents/recents.component";
import {SpecialsComponent} from "./specials/specials.component";
import {LocationsComponent} from "./locations/locations.component";
import {DesignComponent} from "./design/design.component";
import {CartItem} from "./cart/cart-item";

export const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'recents',
    component: RecentsComponent
  },
  {
    path: 'specials',
    component: SpecialsComponent
  },
  {
    path: 'locations',
    component: LocationsComponent
  },
  {
    path: 'design',
    component: DesignComponent
  },
  {
    path: 'cart',
    component: CartItem
  },
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  }
]

