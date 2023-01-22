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
    id: string
    reviews: Array<Review>
    canReview: boolean
    text: string
    error: string

    constructor(route: ActivatedRoute, service: DishService) {
        this.route = route
        this.service = service
        this.dish = null
        this.reviews = []
        this.canReview = false
        this.text = ""
        this.error = ""
        let buf = this.route.snapshot.paramMap.get("id")
        this.id =  buf !== null ? buf : ""

        this.service.loadDishById(this.id).subscribe(p => {
            this.dish = p
        })

        this.service.loadDishReviews(this.id).subscribe(p => {
            this.reviews = p
        })

        this.service.canReview(this.id).subscribe(p => {
            this.canReview = p
        })
    }

    addReview() {
        this.error = ""
        this.service.addReview(this.id, this.text, 5).subscribe(p => {
            console.log(p)
        })
    }

    updateReview(event: any) {
        this.text = event.target.value
    }
}
