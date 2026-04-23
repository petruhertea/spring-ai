package com.petruth.myfirstspringai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class OpenAIChatController {
    private final ChatClient chatClient;

    public OpenAIChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat() {
        String response = chatClient.prompt()
                .user("Tell me an interesting fact about Java")
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("I'm visiting Graz soon, can you give me 10 places I must visit?")
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke() {
        return chatClient.prompt()
                .user("Tell me a joke about dogs")
                .call()
                .chatResponse();
    }
}
