import { Component } from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  auth: AuthService
  title: string

  constructor(auth: AuthService) {
    this.auth = auth
    this.title = "Restaurant"
  }

}
