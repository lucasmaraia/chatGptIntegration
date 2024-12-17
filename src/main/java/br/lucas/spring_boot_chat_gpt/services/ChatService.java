package br.lucas.spring_boot_chat_gpt.services;

import br.lucas.spring_boot_chat_gpt.client.OpenAiClient;
import br.lucas.spring_boot_chat_gpt.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private OpenAiClient openAiClient;

    public ResponseEntity<?> getMessage(String prompt){

        ResponseMessage responseMessage = new ResponseMessage(openAiClient.sendMessage(prompt));

        if(responseMessage.getMessage().equals("No response from OpenAi Services")){
            return new ResponseEntity<>(responseMessage,HttpStatus.NO_CONTENT);
        }

        if(responseMessage.getMessage().equals("Internal Error found on call OpenAI Services")){
            return new ResponseEntity<>(responseMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseMessage,HttpStatus.OK);

    }

}
