package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandsNames {
    HELLO("Hello"),
    CHAT("Chat");
    private final String name;
}
