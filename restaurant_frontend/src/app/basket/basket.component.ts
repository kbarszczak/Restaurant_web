import {Component} from '@angular/core';
import {BasketService} from "../services/basket.service";

@Component({
    selector: 'app-basket',
    templateUrl: './basket.component.html',
    styleUrls: ['./basket.component.css']
})
export class BasketComponent {

    service: BasketService

    constructor(service: BasketService) {
        this.service = service;
        this.service.dishes = this.service.dishes.filter(p => p.selectedQuantity > 0)
    }

    decrease(event: any) {
        let id = event.target.parentElement.id
        for(let dish of this.service.dishes){
            if(dish.id === id){
                if(dish.selectedQuantity > 0) {
                    --dish.selectedQuantity
                }
            }
        }
    }

    increase(event: any) {
        let id = event.target.parentElement.id
        for(let dish of this.service.dishes){
            if(dish.id === id){
                if(dish.selectedQuantity < dish.maxQuantity-1) {
                    ++dish.selectedQuantity
                }
            }
        }
    }

    buy() {

    }
}
