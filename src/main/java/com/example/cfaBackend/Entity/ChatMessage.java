package com.example.cfaBackend.Entity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "chat_messages")
public class ChatMessage {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long projectId;
  private Long authorId;
  private String authorName;

  @Column(nullable = false, length = 1000)
  private String content;

  private Long timestamp;
}