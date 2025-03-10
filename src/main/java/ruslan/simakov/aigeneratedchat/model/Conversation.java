package ruslan.simakov.aigeneratedchat.model;

import java.util.List;

public record Conversation(
        String id,
        String userId,
        List<ChatMessage> messages) {
}
