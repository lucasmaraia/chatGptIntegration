package br.lucas.spring_boot_chat_gpt.client;

import br.lucas.spring_boot_chat_gpt.models.ChatRequest;
import br.lucas.spring_boot_chat_gpt.models.ChatResponse;
import br.lucas.spring_boot_chat_gpt.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenAiClient {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}")
    private String API_KEY;

    public String sendMessage(String prompt) {

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            ChatRequest chatRequest = new ChatRequest("gpt-3.5-turbo",
                    0.0, List.of(new Message(prompt, "user")));

            HttpEntity<ChatRequest> entity = new HttpEntity<>(chatRequest, headers);

            ChatResponse chatResponse = restTemplate.postForObject(API_URL, entity, ChatResponse.class);

            String message = chatResponse.getChoices().get(0).getMessage().getContent();

            if (message == null){
                return "No response from OpenAi Services";
            }

            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return "Internal Error found on call OpenAI Services";
        }

    }
}
