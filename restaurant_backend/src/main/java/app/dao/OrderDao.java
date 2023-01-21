package app.dao;

import model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDao extends MongoRepository<Order, String> {



}
