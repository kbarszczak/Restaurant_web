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
}
