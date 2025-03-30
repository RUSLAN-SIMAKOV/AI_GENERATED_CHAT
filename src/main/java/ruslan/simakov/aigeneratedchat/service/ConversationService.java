package ruslan.simakov.aigeneratedchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import ruslan.simakov.aigeneratedchat.model.ChatMessage;
import ruslan.simakov.aigeneratedchat.model.Conversation;
import ruslan.simakov.aigeneratedchat.model.ConversationRequest;
import ruslan.simakov.aigeneratedchat.repository.ConversationRepository;
import ruslan.simakov.aigeneratedchat.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ConversationService {

    private final OpenAiChatModel openAiChatModel;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public Conversation generateUserResponse(Conversation conversation) {
        SystemMessage systemMessage = new SystemMessage("Pretend to be smart guy");
        List<Message> conversationMessages = conversation.messages().stream()
                .map(message -> {
                    if (message.userId().equals("admin")) {
                        return new UserMessage(message.messageText());
                    }
                    return (Message) new AssistantMessage(message.messageText());
                }).toList();

        List<Message> messages = new ArrayList<>();
        messages.add(systemMessage);
        messages.addAll(conversationMessages);

        Prompt prompt = new Prompt(messages);
        ChatResponse chatResponse = openAiChatModel.call(prompt);
        System.out.println(chatResponse.getResult().getOutput().getText());
        return conversation;
    }

    public Conversation createConversation(@RequestBody ConversationRequest request) {
        userRepository.findById(request.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.userId(),
                new ArrayList<>());

        return conversationRepository.save(conversation);
    }

    public Conversation addMessageToConversation(String conversationId, ChatMessage message) {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.findById(message.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ChatMessage newMessage = new ChatMessage(message.messageText(), message.userId(), LocalDateTime.now());
        conversation.messages().add(newMessage);
        conversation = generateUserResponse(conversation);
        return conversationRepository.save(conversation);
    }

    public Conversation getConversation(String conversationId) {
        return conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
