package app.service.dish;

import model.Dish;

public interface DishService {

    Dish[] getDishes();
    Void addDish(Dish dish);
    Void editDish(Dish dish);
}
