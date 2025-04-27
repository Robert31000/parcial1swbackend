package com.example.cfaBackend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "layout_patches")
public class ProjectLayoutPatch {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long projectId;

  @Column(nullable = false, columnDefinition = "jsonb")
  private String patchJson;   // ⬅️  guarda el JSON del patch

  private Long authorId;      // (opcional) id del usuario
  private Long timestamp;     // epoch millis
}