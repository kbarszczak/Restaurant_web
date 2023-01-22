package app.dao;

import model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewDao extends MongoRepository<Review, String> {

    List<Review> getReviewsByDishId(String dishId);

}
