import {Injectable} from '@angular/core';
import {Dish} from "../model/dish";

@Injectable({
    providedIn: 'root'
})
export class BasketService {

    dishes: Array<Dish>

    constructor() {
        this.dishes = []
    }

    add(dish: Dish){
        this.dishes.push(dish)
    }
}
