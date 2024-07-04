package com.example.demo.repository;

import com.example.demo.entity.AppMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessagesRepository extends JpaRepository<AppMessage, Long> {
    @Query(value = "select distinct chat_id from messages", nativeQuery = true)
    List<Long> findAllDifferentChatIds();

    @Query(value = "select exists (select 1 from messages where chat_id = :chatId)", nativeQuery = true)
    boolean existByChatId(long chatId);
}
