package com.petruth.myfirstspringai.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {
    private final ChatClient chatClient;
    private final DateTimeTools dateTimeTools;
    public DateTimeController(ChatClient chatClient, DateTimeTools dateTimeTools) {
        this.chatClient = chatClient;
        this.dateTimeTools = dateTimeTools;
    }

    @GetMapping("/tools")
    public ResponseEntity<String> tools() {
        String content = chatClient.prompt("What is tomorrow's date?")
                .tools(dateTimeTools)
                .call()
                .content();

        return ResponseEntity.ok().body(content);
    }
}
