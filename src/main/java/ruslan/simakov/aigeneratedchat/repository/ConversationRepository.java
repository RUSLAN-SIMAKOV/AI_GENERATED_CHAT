package ruslan.simakov.aigeneratedchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ruslan.simakov.aigeneratedchat.model.Conversation;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
