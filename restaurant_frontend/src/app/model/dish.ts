export class Dish{
    id: string;
    name: string;
    type: string;
    category: string;
    ingredients: Array<string>;
    maxQuantity: number;
    price: number;
    imageUrl: string;
    selectedQuantity: number;

    constructor(id: string, name: string, type: string, category: string, ingredients: Array<string>, maxQuantity: number, price: number, imageUrl: string) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.ingredients = ingredients;
        this.maxQuantity = maxQuantity;
        this.price = price;
        this.imageUrl = imageUrl;
        this.selectedQuantity = 0;
    }
}