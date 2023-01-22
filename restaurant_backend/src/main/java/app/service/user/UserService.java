package app.service.user;

import model.Order;

public interface UserService {

    Boolean hasOrdered(String userEmail, String dishId);

    Void addOrder(String userEmail, String dishId, Integer quantity);

    Order[] getOrders(String userEmail);

}
