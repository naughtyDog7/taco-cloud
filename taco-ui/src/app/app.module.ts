import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RecentTacosComponent } from './recent-tacos/recent-tacos.component';
import { DesignComponent } from './design/design.component';
import {FormsModule} from '@angular/forms';
import { GroupBoxComponent } from './group-box/group-box.component';
import { BigButtonComponent } from './big-button/big-button.component';

@NgModule({
  declarations: [
    AppComponent,
    RecentTacosComponent,
    DesignComponent,
    GroupBoxComponent,
    BigButtonComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
