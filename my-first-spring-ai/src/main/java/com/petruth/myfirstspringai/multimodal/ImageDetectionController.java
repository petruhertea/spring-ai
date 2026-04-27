package com.petruth.myfirstspringai.multimodal;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetectionController {
    private final ChatClient chatClient;

    @Value("classpath:/static/images/eu.png")
    Resource sampleImage;
    public ImageDetectionController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/image-to-text")
    public ResponseEntity<String> imageToText() {
        String response = chatClient.prompt()
                .user(u -> {
                    u.text("Can you please describe what you see in the following image?");
                    u.media(MimeTypeUtils.IMAGE_JPEG, sampleImage);
                })
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }
}
