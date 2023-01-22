import {Injectable} from '@angular/core';
import {Dish} from "../model/dish";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ApiPaths, environment} from "../environment";
import {AuthService} from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class BasketService {

    dishes: Array<Dish>

    constructor(private http: HttpClient, private auth: AuthService) {
        this.dishes = []
    }

    add(dish: Dish){
        this.dishes.push(dish)
    }

    getCount(): number {
        let result = 0
        for(let dish of this.dishes){
            result += dish.selectedQuantity
        }
        return result
    }

    getValue(): number{
        let result = 0
        for(let dish of this.dishes){
            result += dish.price * dish.selectedQuantity
        }
        return Math.round(result*100)/100.0
    }

    isHuge() {
        return this.getCount() >= 10
    }

    buy(dishId: string, quantity: number){
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.auth.token()}`
        })
        return this.http.post<string>(environment.baseUrl+ApiPaths.AddOrder+"?dishId="+dishId+"&quantity="+quantity, {headers: headers})
    }

}
