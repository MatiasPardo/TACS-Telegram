package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "youName";
    }

    @Override
    public String getBotToken() {
        return "youToken";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        sendText(user.getId(), msg.getText());

        System.out.println(user.getFirstName() + " wrote " + msg.getText() );
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
