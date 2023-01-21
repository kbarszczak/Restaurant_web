package app.service.dish;

import app.dao.DishDao;
import lombok.RequiredArgsConstructor;
import model.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultDishService implements DishService{

    private final DishDao dishDao;

    @Override
    public Dish[] getDishes() {
        List<Dish> dishes = dishDao.findAll();
        Dish []result = new Dish[dishes.size()];
        int index = 0;
        for(Dish dish : dishes){
            result[index++] = dish;
        }
        return result;
    }

    @Override
    public Void addDish(Dish dish) {
        if(dish == null) throw new IllegalArgumentException("The body was not provided");
        if(dish.getId() != null) throw new IllegalArgumentException("The id value cannot be included. It is automatically generated");
        if(dish.getName() == null || dish.getName().isEmpty()) throw new IllegalArgumentException("The dish name is either null or an empty string");
        if(dish.getCategory() == null || dish.getCategory().isEmpty()) throw new IllegalArgumentException("The dish category is either null or an empty string");
        if(dish.getType() == null || dish.getType().isEmpty()) throw new IllegalArgumentException("The dish type is either null or an empty string");
        if(dish.getImageUrl() == null || dish.getImageUrl().isEmpty()) throw new IllegalArgumentException("The image url is either null or an empty string");
        if(dish.getPrice() == null || dish.getPrice() < 0) throw new IllegalArgumentException("The price cannot be negative");
        if(dish.getMaxQuantity() == null || dish.getMaxQuantity() <= 0) throw new IllegalArgumentException("The max quantity has to be positive integer");
        if(dish.getIngredients() == null) throw new IllegalArgumentException("The dish ingredients are not set");

        Optional<Dish> d = dishDao.findDishByNameEquals(dish.getName());
        if(d.isPresent()) throw new IllegalStateException("The dish with the name '" + dish.getName() + "' is already in the database");
        dishDao.insert(dish);
        return null;
    }

    @Override
    public Void editDish(Dish dish) {
        if(dish == null) throw new IllegalArgumentException("The body was not provided");
        if(dish.getId() == null || dish.getId().isEmpty()) throw new IllegalArgumentException("The dish id is either null or an empty string");

        Optional<Dish> d = dishDao.findDishByIdEquals(dish.getId());
        if(d.isEmpty()) throw new IllegalStateException("The dish with the id '" + dish.getId() + "' does not exist in the database");

        Dish entity = d.get();
        if(dish.getCategory() != null) entity.setCategory(dish.getCategory());
        if(dish.getName() != null) entity.setName(dish.getName());
        if(dish.getType() != null) entity.setType(dish.getType());
        if(dish.getImageUrl() != null) entity.setImageUrl(dish.getImageUrl());
        if(dish.getPrice() != null) entity.setPrice(dish.getPrice());
        if(dish.getMaxQuantity() != null) entity.setMaxQuantity(dish.getMaxQuantity());
        if(dish.getIngredients() != null) entity.setIngredients(dish.getIngredients());

        dishDao.save(entity);
        return null;
    }

}
