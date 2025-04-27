package com.example.cfaBackend.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.cfaBackend.DTO.PatchDTO;
import com.example.cfaBackend.Entity.ProjectLayoutPatch;
import com.example.cfaBackend.Repository.PatchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class CanvasService {
  private final PatchRepository patchRepo;
  private final ObjectMapper mapper;

  public void applyPatch(Long projectId, PatchDTO dto) {
    // 1) Persistimos el patch como JSON
    ProjectLayoutPatch patch = ProjectLayoutPatch.builder()
        .projectId(projectId)
        .patchJson(asJson(dto))
        .timestamp(System.currentTimeMillis())
        .build();
    patchRepo.save(patch);
    // 2) (Opcional) aquí podrías aplicar la mutación a un "snapshot" del layout.
  }

  private String asJson(Object o) {
    try { return mapper.writeValueAsString(o); }
    catch (Exception e) { throw new RuntimeException(e); }
  }
}