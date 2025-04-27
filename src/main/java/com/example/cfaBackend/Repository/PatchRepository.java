package com.example.cfaBackend.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfaBackend.Entity.ProjectLayoutPatch;

public interface PatchRepository extends JpaRepository<ProjectLayoutPatch, Long> {
  List<ProjectLayoutPatch> findByProjectId(Long projectId);
}
