package br.lucas.spring_boot_chat_gpt.models;

public class ResponseMessage {
    String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
