package com.petruth.myfirstspringai.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelsController {
    private final ChatClient chatClient;

    public ModelsController(@Qualifier("ragChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rag/models")
    public ResponseEntity<Models> faq(@RequestParam(value = "message", defaultValue = "Give me a list of all the models only from OpenAI along with their context window.") String message) {
        Models models = chatClient.prompt()
                .user(message)
                .call()
                .entity(Models.class);

        return ResponseEntity.ok().body(models);
    }
}
