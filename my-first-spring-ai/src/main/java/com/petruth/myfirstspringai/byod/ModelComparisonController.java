package com.petruth.myfirstspringai.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparisonController {
    private final ChatClient chatClient;

    public ModelComparisonController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/models")
    public ResponseEntity<String> models() {
        String response = chatClient.prompt()
                .user("Can you give me an up to date list of popular large language models and their current context windows?")
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/models/stuff-the-prompt")
    public ResponseEntity<String> modelsStuffThePrompt() {
        var system = """
                If you're asked about up to date language models and there context window here is some information to help you with your response:
                
                GPT-5: 400k
                GPT-4o: 128k
                Claude Sonnet 4.5: 200k
                Gemini 2.5 Pro: 1M
                Grok 4: 256k
                Llama 3.3 70B: 128k
                Mistral Large 2: 128k
                Qwen2.5-Max: 128k
                DeepSeek V3.1: 128k
                Command A: 256k
                """;

        String response = chatClient.prompt()
                .user("Can you give me an up to date list of popular large language models and their current context windows?")
                .system(system)
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }
}
