package com.example.cfaBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cfaBackend.Entity.Asistencia;
import com.example.cfaBackend.Entity.Category;
import com.example.cfaBackend.User.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByUser(User user);
    @Query("SELECT a FROM Asistencia a WHERE :category MEMBER OF a.user.categories")
    List<Asistencia> findByUserCategories(@Param("category") Category category);

    Optional<Asistencia> findByUserAndDate(User user, LocalDate date);



    }
