package app.service.dish;

import model.Dish;
import model.Review;

public interface DishService {

    Dish getDish(String id);
    Dish[] getDishes();
    Void addDish(Dish dish);
    Void editDish(Dish dish);
    Review[] getDishReviews(String dishId);
    Void addDishReview(String authorEmail, String dishId, String text, Integer rating);
    Void removeDish(String id);
}
