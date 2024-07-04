package com.example.demo.bot;

import com.example.demo.pojo.CommandsNames;
import com.example.demo.repository.MessagesRepository;
import com.example.demo.service.ChatCommand;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final Map<String, ChatCommand> CHAT_COMMANDS;
    private final MessagesRepository messagesRepository;

    public Bot(MessagesRepository messagesRepository,
               List<ChatCommand> commands
    ) {
        super("7492936114:AAF2lx4XpOB0wNh_afT9AcGr4tUftL5Jrm4");
        this.CHAT_COMMANDS = setUpCommands(commands);
        this.messagesRepository = messagesRepository;

    }

    @Override
    public void onUpdateReceived(Update update) {
        Long currentChatId = update.getMessage().getChatId();
        log.info("message from: [{}] was arrived with text: [{}]", currentChatId, update.getMessage().getText());
        ChatCommand chatCommand;
        if (messagesRepository.existByChatId(currentChatId)) {
            chatCommand = CHAT_COMMANDS.get(CommandsNames.CHAT.getName());
        } else {
            chatCommand = CHAT_COMMANDS.get(CommandsNames.HELLO.getName());
        }
        executeAll(chatCommand.execute(update));
    }

    @SneakyThrows
    private void executeAll(List<SendMessage> sendMessages) {
        for (SendMessage message : sendMessages) {
            execute(message);
        }
    }

    @Override
    public String getBotUsername() {
        return "test_onesuparmy_bot";
    }

    private Map<String, ChatCommand> setUpCommands(List<ChatCommand> commands) {
        return commands.stream().collect(Collectors.toMap(ChatCommand::getCommandName, Function.identity()));
    }
}
