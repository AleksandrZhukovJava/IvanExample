package com.example.demo.service;

import com.example.demo.pojo.CommandsNames;
import com.example.demo.repository.MessagesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class Chat implements ChatCommand {
    private MessagesRepository messagesRepository;

    public List<SendMessage> execute(Update update) {
        log.info("invoked 'chat' command from: [{}]", update.getMessage().getChatId());
        return messagesRepository.findAllDifferentChatIds().stream()
                .filter(a -> !a.equals(update.getMessage().getChatId()))
                .map(a -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(a);
                    sendMessage.setText(update.getMessage().getText());
                    return sendMessage;
                }).toList();
    }

    public String getCommandName() {
        return CommandsNames.CHAT.getName();
    }
}
