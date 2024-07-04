package com.example.demo.service;

import com.example.demo.entity.AppMessage;
import com.example.demo.pojo.CommandsNames;
import com.example.demo.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HelloUser implements ChatCommand {
    private final MessagesRepository messagesRepository;

    public List<SendMessage> execute(Update update) {
        log.info("invoked 'hello' command from: [{}]", update.getMessage().getChatId());
        addNewUser(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Привет, " + update.getMessage().getFrom().getFirstName());

        return List.of(sendMessage);
    }

    @Override
    public String getCommandName() {
        return CommandsNames.HELLO.getName();
    }

    private void addNewUser(Update update) {
        messagesRepository.save(AppMessage.builder()
                .text(update.getMessage().getText())
                .username(update.getMessage().getFrom().getUserName())
                .chatId(update.getMessage().getChatId())
                .build());
    }
}
