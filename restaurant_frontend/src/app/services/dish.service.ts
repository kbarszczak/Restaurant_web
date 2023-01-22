import {Injectable} from '@angular/core';
import {environment} from "../environment";
import {ApiPaths} from "../environment";
import {HttpClient} from "@angular/common/http";
import {Dish} from "../model/dish";

@Injectable({
    providedIn: 'root'
})
export class DishService {

    constructor(private http: HttpClient) {}

    loadDishes() {
        return this.http.get<Array<Dish>>(environment.baseUrl + ApiPaths.DishesAll)
    }

}
