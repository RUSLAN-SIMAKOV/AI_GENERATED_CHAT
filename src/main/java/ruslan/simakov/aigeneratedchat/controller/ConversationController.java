package ruslan.simakov.aigeneratedchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ruslan.simakov.aigeneratedchat.model.ChatMessage;
import ruslan.simakov.aigeneratedchat.model.Conversation;
import ruslan.simakov.aigeneratedchat.model.ConversationRequest;
import ruslan.simakov.aigeneratedchat.service.ConversationService;

@RequiredArgsConstructor
@RestController
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping("/conversation")
    public Conversation createConversation(@RequestBody ConversationRequest request) {
        return conversationService.createConversation(request);
    }

    @PutMapping("/conversation/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId,
                                                 @RequestBody ChatMessage message) {
        return conversationService.addMessageToConversation(conversationId, message);
    }

    @GetMapping("/conversation/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationService.getConversation(conversationId);
    }
}
