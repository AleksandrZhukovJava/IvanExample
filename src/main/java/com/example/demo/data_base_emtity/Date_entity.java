package com.example.demo.data_base_emtity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bot_trying2")
public class Date_entity {

    @Column(name = "message_from_user")
    private String message;

    @Column(name = "chat_id")
    @Id
    private Long chatId;

    @Column(name = "name_of_user")
    private String name_of_user;

    @Column(name = "number_of_message")
    private Long number_of_message;
}
