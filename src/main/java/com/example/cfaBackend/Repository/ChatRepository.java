package com.example.cfaBackend.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfaBackend.Entity.ChatMessage;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
  List<ChatMessage> findByProjectId(Long projectId);
}