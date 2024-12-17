package br.lucas.spring_boot_chat_gpt.models;

import java.util.List;

public class ChatResponse {

    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }
}
