import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DishesComponent } from './dishes/dishes.component';
import { HomeComponent } from './home/home.component';
import {AuthService} from "./services/auth.service";
import { BasketComponent } from './basket/basket.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {HttpClientModule} from "@angular/common/http";
import {DishService} from "./services/dish.service";
import {BasketService} from "./services/basket.service";

@NgModule({
  declarations: [
    AppComponent,
    DishesComponent,
    HomeComponent,
    BasketComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
      AuthService,
      DishService,
      BasketService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
