package com.example.cfaBackend.Service;
// src/main/java/com/example/cfaBackend/service/ProjectService.java

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.cfaBackend.Entity.Project;
import com.example.cfaBackend.Repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> listarTodos() {
        return projectRepository.findAll();
    }

    public Project crearProyecto(Project project) {
        // Aquí podrías validar nombre único, etc.
        return projectRepository.save(project);
    }

    public Project obtenerPorId(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }
}
