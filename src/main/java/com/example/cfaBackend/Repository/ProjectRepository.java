package com.example.cfaBackend.Repository;
// src/main/java/com/example/cfaBackend/repository/ProjectRepository.java

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfaBackend.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> { 

    
}
