package ruslan.simakov.aigeneratedchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ruslan.simakov.aigeneratedchat.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
