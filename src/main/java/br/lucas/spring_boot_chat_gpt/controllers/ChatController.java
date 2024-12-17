package br.lucas.spring_boot_chat_gpt.controllers;

import br.lucas.spring_boot_chat_gpt.models.PromptRequest;
import br.lucas.spring_boot_chat_gpt.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<?> chat(@RequestBody PromptRequest promptRequest){
        return chatService.getMessage(promptRequest.prompt());
    }
}
