package com.petruth.myfirstspringai.tools.weather;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final ChatClient chatClient;
    private final WeatherTools weatherTools;

    public WeatherController(ChatClient.Builder builder, WeatherTools weatherTools) {
        this.chatClient = builder.build();
        this.weatherTools = weatherTools;
    }

    @GetMapping("/weather/alerts")
    public ResponseEntity<String> getAlerts(@RequestParam String message) {
        String weatherAlert = chatClient.prompt()
                .tools(weatherTools)
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok().body(weatherAlert);
    }
}
