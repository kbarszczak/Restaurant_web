package app.dao;

import model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderDao extends MongoRepository<Order, String> {

    List<Order> findOrdersByAuthorId(String authorId);

}
