package app.dao;

import model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DishDao extends MongoRepository<Dish, String> {

    Optional<Dish> findDishByNameEquals(String name);
    Optional<Dish> findDishByIdEquals(String name);
}
