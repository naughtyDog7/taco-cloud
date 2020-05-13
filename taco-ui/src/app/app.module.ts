import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {DesignComponent} from './design/design.component';
import {FormsModule} from '@angular/forms';
import {GroupBoxComponent} from './group-box/group-box.component';
import {BigButtonComponent} from './big-button/big-button.component';
import {RouterModule} from "@angular/router";
import {TacoHeaderComponent} from './taco-header/taco-header.component';
import {TacoFooterComponent} from './taco-footer/taco-footer.component';
import {HomeComponent} from './home/home.component';
import {CloudTitleComponent} from './cloud-title/cloud-title.component';
import {RecentsComponent} from './recents/recents.component';
import {NonWrapsPipe} from './recents/non-wraps.pipe';
import {WrapsPipe} from './recents/wraps.pipe';
import {LittleButtonComponent} from './little-button/little-button.component';
import {LocationsComponent} from './locations/locations.component';
import {SpecialsComponent} from './specials/specials.component';
import {ApiService} from "./api/api.service";
import {CartService} from "./cart/cart.service";
import {RecentService} from "./recents/recent.service";
import { CartComponent } from './cart/cart.component';
import { routes } from './app.routes';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    DesignComponent,
    GroupBoxComponent,
    BigButtonComponent,
    TacoHeaderComponent,
    TacoFooterComponent,
    HomeComponent,
    CloudTitleComponent,
    RecentsComponent,
    NonWrapsPipe,
    WrapsPipe,
    LittleButtonComponent,
    LocationsComponent,
    SpecialsComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [ApiService,
    CartService,
    RecentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
