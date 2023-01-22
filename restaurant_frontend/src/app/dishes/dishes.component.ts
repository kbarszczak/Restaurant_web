import {Component} from '@angular/core';
import {DishService} from "../services/dish.service";
import {Dish} from "../model/dish";
import {AuthService} from "../services/auth.service";

@Component({
    selector: 'app-dishes',
    templateUrl: './dishes.component.html',
    styleUrls: ['./dishes.component.css']
})
export class DishesComponent {

    service: DishService
    auth: AuthService
    dishes: Array<Dish>
    categories: Array<string>
    types: Array<string>
    onPageItems: number

    constructor(service: DishService, auth: AuthService) {
        this.service = service
        this.auth = auth
        this.dishes = []
        this.categories = []
        this.types = []
        this.onPageItems = 20

        this.service.loadDishes().subscribe(r => {
            for(let dish of r){
                dish.selectedQuantity = 0
                this.dishes.push(dish)
                this.categories.push(dish.category)
                this.types.push(dish.type)
            }
            this.categories = [... new Set(this.categories)]
            this.types = [... new Set(this.types)]
        })
    }

    decrease(event: any) {
        let dish = this.findDishById(event.target.parentElement.parentElement.id)
        if(dish.selectedQuantity > 0) dish.selectedQuantity--;
    }

    increase(event: any) {
        let dish = this.findDishById(event.target.parentElement.parentElement.id)
        if(dish.selectedQuantity < dish.maxQuantity) dish.selectedQuantity++;
    }

    private findDishById(id: string): Dish{
        return this.dishes.filter(p => p.id === id)[0]
    }
}
