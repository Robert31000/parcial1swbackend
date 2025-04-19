package com.example.cfaBackend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.cfaBackend.User.User; // Importar la clase User

@Data
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonProperty("nombre") 
    public String name;

    @JsonProperty("email")
    public String email;

    @JsonProperty("mensaje") 
    @Column(length = 500) 
    public String message;

  
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)  
    private User user;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
