package app.service.user;

import app.dao.DishDao;
import app.dao.OrderDao;
import app.dao.UserDao;
import lombok.RequiredArgsConstructor;
import model.Dish;
import model.Order;
import model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final DishDao dishDao;
    private final UserDao userDao;
    private final OrderDao orderDao;

    @Override
    public Boolean hasOrdered(String userEmail, String dishId) {
        User user = getUserByEmail(userEmail);
        List<Order> orders = orderDao.findOrdersByAuthorId(user.getId());
        return orders.stream().anyMatch(p -> p.getDish().getId().equals(dishId));
    }

    @Override
    public Void addOrder(String userEmail, String dishId, Integer quantity) {
        User user = getUserByEmail(userEmail);
        Dish dish = getDishById(dishId);
        orderDao.insert(new Order(
                user,
                dish,
                quantity,
                LocalDate.now()
        ));
        return null;
    }

    @Override
    public Order[] getOrders(String userEmail) {
        User user = getUserByEmail(userEmail);
        List<Order> orders = orderDao.findOrdersByAuthorId(user.getId());
        Order []result = new Order[orders.size()];
        int index = 0;
        for(Order order : orders){
            result[index++] = order;
        }
        return result;
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
