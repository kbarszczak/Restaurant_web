import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Dish} from "../model/dish";
import {DishService} from "../services/dish.service";
import {Review} from "../model/review";

@Component({
    selector: 'app-dish-details',
    templateUrl: './dish-details.component.html',
    styleUrls: ['./dish-details.component.css']
})
export class DishDetailsComponent {

    private route: ActivatedRoute
    service: DishService
    dish: Dish | null
    reviews: Array<Review>

    constructor(route: ActivatedRoute, service: DishService) {
        this.route = route
        this.service = service
        this.dish = null
        this.reviews = []

        let id = this.route.snapshot.paramMap.get("id")
        this.service.loadDishById(id).subscribe(p => {
            this.dish = p
        })

        this.service.loadDishReviews(id).subscribe(p => {
            this.reviews = p
            console.log("Reviews '"+this.reviews+"'")
        })
    }

}
