package com.petruth.myfirstspringai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {
    private final ChatClient chatClient;

    public AcmeBankController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public ResponseEntity<java.lang.String> chat(@RequestParam String message) {
        var systemInstructions = """
                You are a customer service assistant for Acme Bank.
                You can ONLY discuss:
                - Account balances and transactions
                - Branch locations and hours
                - General Banking Services
                
                If asked about anything else, respond: "I can only help with banking-related questions."
                """;


        String response = chatClient.prompt()
                .user(message)
                .system(systemInstructions)
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }
}
