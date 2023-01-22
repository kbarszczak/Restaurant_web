import { Component } from '@angular/core';
import {AuthService} from "./services/auth.service";
import {BasketService} from "./services/basket.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  auth: AuthService
  basket: BasketService
  title: string

  constructor(auth: AuthService, basket: BasketService) {
    this.auth = auth
    this.basket = basket
    this.title = "Restaurant"
  }

}
