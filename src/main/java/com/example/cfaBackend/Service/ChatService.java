package com.example.cfaBackend.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.cfaBackend.DTO.ChatMsgDTO;
import com.example.cfaBackend.Entity.ChatMessage;
import com.example.cfaBackend.Repository.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatRepository chatRepo;

  public void save(Long projectId, ChatMsgDTO dto) {
    ChatMessage msg = ChatMessage.builder()
        .projectId(projectId)
        .authorId(dto.getAuthorId())
        .authorName(dto.getAuthorName())
        .content(dto.getContent())
        .timestamp(dto.getTimestamp())
        .build();
    chatRepo.save(msg);
  }
}