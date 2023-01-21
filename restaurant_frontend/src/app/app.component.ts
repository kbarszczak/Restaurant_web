import { Component } from '@angular/core';
import {AuthService} from "./auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  auth: AuthService
  title = "Restaurant"

  constructor(auth: AuthService) {
    // todo: load proper window
    this.auth = auth
  }


}
