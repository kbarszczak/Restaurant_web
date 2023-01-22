import {Component} from '@angular/core';
import {DishService} from "../services/dish.service";
import {Dish} from "../model/dish";
import {AuthService} from "../services/auth.service";
import {BasketService} from "../services/basket.service";

@Component({
    selector: 'app-dishes',
    templateUrl: './dishes.component.html',
    styleUrls: ['./dishes.component.css']
})
export class DishesComponent {

    // todo: basket count
    // todo: click to item and redirect

    service: DishService
    auth: AuthService
    basket: BasketService
    dishes: Array<Dish>
    filteredDishes: Array<Dish>
    pageDishes: Array<Array<Dish>>
    categories: Array<string>
    currentCategories: Array<string>
    types: Array<string>
    currentTypes: Array<string>
    onPageItems: number
    currentPageIndex: number

    constructor(service: DishService, auth: AuthService, basket: BasketService) {
        this.service = service
        this.auth = auth
        this.basket = basket;
        this.dishes = []
        this.filteredDishes = []
        this.pageDishes = []
        this.currentCategories = []
        this.categories = []
        this.currentTypes = []
        this.types = []
        this.onPageItems = 20
        this.currentPageIndex = 0

        this.service.loadDishes().subscribe(r => {
            for(let dish of r){
                dish.selectedQuantity = 0
                this.dishes.push(dish)
                this.filteredDishes.push(dish)

                this.categories.push(dish.category)
                this.types.push(dish.type)
            }
            this.categories = [... new Set(this.categories)]
            this.types = [... new Set(this.types)]
            this.reloadPage()
        })
    }

    decrease(event: any) {
        let dish = this.findDishById(event.target.parentElement.parentElement.id)
        if(dish.selectedQuantity > 0) dish.selectedQuantity--;
        // todo: update basket
    }

    increase(event: any) {
        let dish = this.findDishById(event.target.parentElement.parentElement.id)
        if(dish.selectedQuantity < dish.maxQuantity) dish.selectedQuantity++;
        // todo: update basket
    }

    remove(event: any) {
        // todo: remove dish. Available only for admin
    }

    typeChanged(event: any) {
        if(event.target.checked){
            this.currentTypes.push(event.target.value)
        } else {
            const index = this.currentTypes.indexOf(event.target.value);
            if (index > -1) {
                this.currentTypes.splice(index, 1);
            }
        }
        this.applyFilters()
    }

    categoryChanged(event: any) {
        if(event.target.checked){
            this.currentCategories.push(event.target.value)
        } else {
            const index = this.currentCategories.indexOf(event.target.value);
            if (index > -1) {
                this.currentCategories.splice(index, 1);
            }
        }
        this.applyFilters()
    }

    pageItemsCountChanged(event: any) {
        if(event.target.value > 0){
            this.onPageItems = event.target.value
            this.currentPageIndex = 0
            this.reloadPage()
        }
    }

    nextPage() {
        if(this.currentPageIndex < this.pageDishes.length-1) ++this.currentPageIndex
    }

    previousPage() {
        if(this.currentPageIndex >= 1) --this.currentPageIndex
    }

    private findDishById(id: string): Dish{
        return this.dishes.filter(p => p.id === id)[0]
    }

    private reloadPage(){
        this.pageDishes = []
        for(let i=0; i<Math.ceil(this.filteredDishes.length / this.onPageItems); ++i){
            let pageDishes: Array<Dish> = []
            for(let index=i*this.onPageItems; index<(i+1)*this.onPageItems; ++index){
                if(index >= this.filteredDishes.length) break;
                pageDishes.push(this.filteredDishes[index])
            }
            this.pageDishes.push(pageDishes)
        }
    }

    private applyFilters(){
        if(this.currentCategories.length === 0 && this.currentTypes.length === 0){
            this.filteredDishes = this.dishes
        } else if(this.currentCategories.length === 0) {
            this.filteredDishes = this.dishes.filter(p => {
                return this.currentTypes.indexOf(p.type) >= 0
            })
        } else if(this.currentTypes.length === 0) {
            this.filteredDishes = this.dishes.filter(p => {
                return this.currentCategories.indexOf(p.category) >= 0
            })
        } else {
            this.filteredDishes = this.dishes.filter(p => {
                return this.currentTypes.indexOf(p.type) >= 0 && this.currentCategories.indexOf(p.category) >= 0
            })
        }
        this.reloadPage()
    }
}
