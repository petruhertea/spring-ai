package com.petruth.myfirstspringai.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlanController {
    private final ChatClient chatClient;

    public VacationPlanController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/vacation/unstructured")
    public ResponseEntity<String> unstructured() {
        String response = chatClient.prompt()
                .user("I want to plan a trip to Hawaii. Give me a list of things to do.")
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/vacation/structured")
    public ResponseEntity<Itinerary> structured() {
        Itinerary response = chatClient.prompt()
                .user("I want to plan a trip to Hawaii. Give me a list of things to do.")
                .call()
                .entity(Itinerary.class);

        return ResponseEntity.ok().body(response);
    }
}
