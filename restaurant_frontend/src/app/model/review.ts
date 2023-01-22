import {User} from "./user";
import {Dish} from "./dish";

export class Review{

    id: string
    author: User
    dish: Dish
    mark: number
    description: string

    constructor(id: string, author: User, dish: Dish, mark: number, description: string) {
        this.id = id;
        this.author = author;
        this.dish = dish;
        this.mark = mark;
        this.description = description;
    }
}