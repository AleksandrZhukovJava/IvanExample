package com.example.demo.chatService;

import com.example.demo.bot.Bot;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@AllArgsConstructor
public class HelloUser {
    private Bot bot;

    public void goChat(Update update) {
        Long chatId = update.getMessage().getChatId();
        if (!bot.getChatsId().contains(chatId)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Привет, " + update.getMessage().getFrom().getFirstName());
            bot.getChatsId().add(chatId);
            toExecute(sendMessage);
        }
    }

    public void toExecute(SendMessage sendMessage) {
        try {
            bot.execute(sendMessage);
            log.info("hello user is ok");
        } catch (TelegramApiException e) {
            log.error("error hello user");
        }
    }
}
