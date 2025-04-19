package com.example.cfaBackend.Entity;

import com.example.cfaBackend.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "asistencia")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  

public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;  // Fecha de asistencia
    private LocalTime time;  // Hora de asistencia
    private Double aporte;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
