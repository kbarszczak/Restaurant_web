import {Injectable} from '@angular/core';
import {environment} from "../environment";
import {ApiPaths} from "../environment";
import {HttpClient} from "@angular/common/http";
import {Dish} from "../model/dish";
import {Review} from "../model/review";

@Injectable({
    providedIn: 'root'
})
export class DishService {

    constructor(private http: HttpClient) {}

    loadDishes() {
        return this.http.get<Array<Dish>>(environment.baseUrl + ApiPaths.DishesAll)
    }

    loadDishById(id: string | null){
        return this.http.get<Dish>(environment.baseUrl + ApiPaths.Dish + "/" + id)
    }

    loadDishReviews(id: string | null){
        return this.http.get<Review[]>(environment.baseUrl + ApiPaths.Review + "/" + id + "/reviews")
    }

}
