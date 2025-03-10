package ruslan.simakov.aigeneratedchat.model;

import java.time.LocalDateTime;

public record ChatMessage(
        String messageText,
        String userId,
        LocalDateTime timestamp) {
}
