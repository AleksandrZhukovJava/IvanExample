package com.example.demo.entity;

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
@Table(name = "messages")
public class AppMessage {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "message_number")
    private Long messageNumber;
}
