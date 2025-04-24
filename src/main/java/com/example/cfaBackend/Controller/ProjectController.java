package com.example.cfaBackend.Controller;
// src/main/java/com/example/cfaBackend/controller/ProjectController.java


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cfaBackend.Entity.Project;
import com.example.cfaBackend.Service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173") 
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> listar() {
        return ResponseEntity.ok(projectService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Project> crear(@RequestBody Project project) {
        Project nuevo = projectService.crearProyecto(project);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.obtenerPorId(id));
    }
}
