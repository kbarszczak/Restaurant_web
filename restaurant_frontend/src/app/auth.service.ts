import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwt: string;
  refreshJwt: string;
  roles: Array<string>

  constructor() {
    this.jwt = ""
    this.refreshJwt = ""
    this.roles = []
  }

  login(email: string, password:string){
    // todo: login
  }

  logout(){
    this.jwt = ""
    this.refreshJwt = ""
    this.roles = []
  }

  register(name: string, surname: string, email: string, password:string){
    // todo: register
  }

  hasRole(role: string){
    return role.indexOf(role) >= 0
  }

  isAdmin(){
    return this.hasRole("ROLE_ADMIN")
  }

  isUser(){
    return this.hasRole("ROLE_USER")
  }

  isManager(){
    return this.hasRole("ROLE_MANAGER")
  }

  isLoggedIn(){
    return this.jwt !== ""
  }

}
