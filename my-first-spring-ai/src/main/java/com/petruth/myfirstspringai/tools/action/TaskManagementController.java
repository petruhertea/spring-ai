package com.petruth.myfirstspringai.tools.action;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManagementController {

    private final ChatClient chatClient;
    private final TaskManagementTools taskManagementTools;

    public TaskManagementController(ChatClient chatClient, TaskManagementTools taskManagementTools) {
        this.chatClient = chatClient;
        this.taskManagementTools = taskManagementTools;
    }

    @GetMapping("/tasks")
    public ResponseEntity<String> createTask(@RequestParam String message) {
        String task = chatClient.prompt()
                .tools(taskManagementTools)
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok().body(task);
    }
}
