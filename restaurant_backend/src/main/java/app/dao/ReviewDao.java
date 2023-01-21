package app.dao;

import model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewDao extends MongoRepository<Review, String> {



}
