package app.service.user;

import app.dao.DishDao;
import app.dao.OrderDao;
import app.dao.UserDao;
import lombok.RequiredArgsConstructor;
import model.Dish;
import model.Order;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final DishDao dishDao;
    private final UserDao userDao;
    private final OrderDao orderDao;

    @Override
    public boolean hasOrdered(String userEmail, String dishId) {
        Optional<User> author = userDao.findByEmailEquals(userEmail);
        if (author.isEmpty())
            throw new IllegalArgumentException("The user with email '" + userEmail + "' does not exist");

        Optional<Dish> dish = dishDao.findDishByIdEquals(dishId);
        if (dish.isEmpty()) throw new IllegalArgumentException("The dish with id '" + dishId + "' does not exist");

        List<Order> orders = orderDao.findOrdersByAuthorId(author.get().getId());
        return orders.stream().anyMatch(p -> {
            for (Dish d : p.getDishes()) {
                if (d.getId().equals(dishId)) return true;
            }
            return false;
        });

    }
}
