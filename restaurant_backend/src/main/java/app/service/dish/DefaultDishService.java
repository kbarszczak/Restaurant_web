package app.service.dish;

import app.dao.DishDao;
import app.dao.OrderDao;
import app.dao.ReviewDao;
import app.dao.UserDao;
import lombok.RequiredArgsConstructor;
import model.Dish;
import model.Order;
import model.Review;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultDishService implements DishService {

    private final DishDao dishDao;
    private final UserDao userDao;
    private final ReviewDao reviewDao;
    private final OrderDao orderDao;

    @Override
    public Dish getDish(String id) {
        Optional<Dish> dish = dishDao.findDishByIdEquals(id);
        if (dish.isEmpty()) throw new IllegalArgumentException("The dish with id '" + id + "' was not found");
        return dish.get();
    }

    @Override
    public Dish[] getDishes() {
        List<Dish> dishes = dishDao.findAll();
        Dish[] result = new Dish[dishes.size()];
        int index = 0;
        for (Dish dish : dishes) {
            result[index++] = dish;
        }
        return result;
    }

    @Override
    public Void addDish(Dish dish) {
        if (dish == null) throw new IllegalArgumentException("The body was not provided");
        if (dish.getId() != null)
            throw new IllegalArgumentException("The id value cannot be included. It is automatically generated");
        if (dish.getName() == null || dish.getName().isEmpty())
            throw new IllegalArgumentException("The dish name is either null or an empty string");
        if (dish.getCategory() == null || dish.getCategory().isEmpty())
            throw new IllegalArgumentException("The dish category is either null or an empty string");
        if (dish.getType() == null || dish.getType().isEmpty())
            throw new IllegalArgumentException("The dish type is either null or an empty string");
        if (dish.getImageUrl() == null || dish.getImageUrl().isEmpty())
            throw new IllegalArgumentException("The image url is either null or an empty string");
        if (dish.getPrice() == null || dish.getPrice() < 0)
            throw new IllegalArgumentException("The price cannot be negative");
        if (dish.getMaxQuantity() == null || dish.getMaxQuantity() <= 0)
            throw new IllegalArgumentException("The max quantity has to be positive integer");
        if (dish.getIngredients() == null) throw new IllegalArgumentException("The dish ingredients are not set");

        Optional<Dish> d = dishDao.findDishByNameEquals(dish.getName());
        if (d.isPresent())
            throw new IllegalStateException("The dish with the name '" + dish.getName() + "' is already in the database");
        dishDao.insert(dish);
        return null;
    }

    @Override
    public Void editDish(Dish dish) {
        if (dish == null) throw new IllegalArgumentException("The body was not provided");
        if (dish.getId() == null || dish.getId().isEmpty())
            throw new IllegalArgumentException("The dish id is either null or an empty string");

        Optional<Dish> d = dishDao.findDishByIdEquals(dish.getId());
        if (d.isEmpty())
            throw new IllegalStateException("The dish with the id '" + dish.getId() + "' does not exist in the database");

        Dish entity = d.get();
        if (dish.getCategory() != null) entity.setCategory(dish.getCategory());
        if (dish.getName() != null) entity.setName(dish.getName());
        if (dish.getType() != null) entity.setType(dish.getType());
        if (dish.getImageUrl() != null) entity.setImageUrl(dish.getImageUrl());
        if (dish.getPrice() != null) entity.setPrice(dish.getPrice());
        if (dish.getMaxQuantity() != null) entity.setMaxQuantity(dish.getMaxQuantity());
        if (dish.getIngredients() != null) entity.setIngredients(dish.getIngredients());

        dishDao.save(entity);
        return null;
    }

    @Override
    public Review[] getDishReviews(String dishId) {
        List<Review> reviews = reviewDao.getReviewsByDishId(dishId);
        Review[] result = new Review[reviews.size()];
        int index = 0;
        for (Review review : reviews) {
            result[index++] = review;
        }
        return result;
    }

    @Override
    public Void addDishReview(String authorEmail, String dishId, String text, Integer rating) {
        User author = getUserByEmail(authorEmail);
        Dish dish = getDishById(dishId);

        List<Order> orders = orderDao.findOrdersByAuthorId(author.getId());
        if (orders.stream().noneMatch(p -> p.getDish().getId().equals(dishId))) throw new IllegalArgumentException("The user with id '" + author.getId() + "' never ordered dish with id '" + dishId + "'");

        if (text.length() < 50 || text.length() > 500)
            throw new IllegalArgumentException("The review text length must be in the range from 50 to 500 characters");
        if (rating < 0 || rating > 5)
            throw new IllegalArgumentException("The review rate must be in the range from 0 to 5");

        reviewDao.insert(new Review(
                author,
                dish,
                rating,
                text
        ));
        return null;
    }

    private User getUserByEmail(String email){
        Optional<User> user = userDao.findByEmailEquals(email);
        if (user.isEmpty())
            throw new IllegalArgumentException("The user with email '" + email + "' does not exist");
        return user.get();
    }

    private Dish getDishById(String id){
        Optional<Dish> dish = dishDao.findDishByIdEquals(id);
        if (dish.isEmpty()) throw new IllegalArgumentException("The dish with id '" + id + "' does not exist");
        return dish.get();
    }

}
