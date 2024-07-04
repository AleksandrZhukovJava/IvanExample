package com.example.demo.bot;

import com.example.demo.chatService.Chat;
import com.example.demo.chatService.HelloUser;
import com.example.demo.data_base_emtity.Date_entity;
import com.example.demo.upRepository.UpRepository;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Component
@Data
public class Bot extends TelegramLongPollingBot {

   private final UpRepository upRepository;

    private Set<Long> chatsId = new HashSet<>();

    public Bot(UpRepository upRepository) {
        super("7401077319:AAH5EWgeuYb_xmR2ROEWfFuiCubryTXFGXM");
        this.upRepository = upRepository;
    }
    @Override
    public void onUpdateReceived(Update update) {
        new HelloUser(this).goChat(update);
        new Chat(this).conversation(update);
        Date_entity dateEntity = Date_entity.builder()
                .message(update.getMessage().getText())
                .name_of_user(update.getMessage().getFrom().getUserName())
                .number_of_message(update.getMessage().getChatId())
                .build();
        upRepository.save(dateEntity);

    }

    @Override
    public String getBotUsername() {
        return "vavanka_ivan_bot";
    }
}
