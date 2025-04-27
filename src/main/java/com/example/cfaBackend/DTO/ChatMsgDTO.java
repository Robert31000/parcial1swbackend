package com.example.cfaBackend.DTO;

import lombok.Data;

@Data
public class ChatMsgDTO {
  private String authorName;
  private Long   authorId;
  private String content;
  private Long   timestamp;
}