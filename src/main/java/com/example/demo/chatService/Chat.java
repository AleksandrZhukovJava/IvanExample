package com.example.demo.chatService;

import com.example.demo.bot.Bot;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@AllArgsConstructor
public class Chat {
    private Bot bot;

    public void conversation(Update update) {
        bot.getChatsId().stream()
                .filter(a -> !a.equals(update.getMessage().getChatId()))
                .forEach(a -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(a);
                    sendMessage.setText(update.getMessage().getText());
                    toExecute(sendMessage);
                });
    }

    public void toExecute(SendMessage sendMessage) {
        try {
            bot.execute(sendMessage);
            log.info("chat is ok");
        } catch (TelegramApiException e) {
            log.error("error is in chat");        }
    }
}
