package com.example.demo.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface ChatCommand {
    List<SendMessage> execute(Update update);
    String getCommandName();
}
