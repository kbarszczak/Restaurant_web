import {Injectable} from '@angular/core';
import {environment} from "../environment";
import {ApiPaths} from "../environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Dish} from "../model/dish";
import {Review} from "../model/review";
import {AuthService} from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class DishService {

    // todo: fix when token is not valid. User refresh token then

    constructor(private http: HttpClient, private auth: AuthService) {}

    loadDishes() {
        return this.http.get<Array<Dish>>(environment.baseUrl + ApiPaths.DishesAll)
    }

    loadDishById(id: string | null){
        return this.http.get<Dish>(environment.baseUrl + ApiPaths.Dish + "/" + id)
    }

    loadDishReviews(id: string | null){
        return this.http.get<Review[]>(environment.baseUrl + ApiPaths.Review + "/" + id + "/reviews")
    }

    canReview(id: string | null) {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.auth.token()}`
        })
        return this.http.get<boolean>(environment.baseUrl + ApiPaths.CanReview + "?dishId=" + id, {headers: headers})
    }

    addReview(id: string, text: string, mark: number){
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.auth.token()}`
        })
        return this.http.post<string>(environment.baseUrl + ApiPaths.Review + "/" + id + "/reviews?text="+text+"&rating="+mark, {headers: headers})
    }
}
