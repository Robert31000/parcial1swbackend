package com.example.cfaBackend.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cfaBackend.Entity.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String username;

    private String codigo;
    private String nombre;
    private String apellido;

    String password;
    String email;
   
    @Enumerated(EnumType.STRING)
    Role role;

   // Relación muchos a muchos con categorías
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
       name = "user_categories",
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "category_id")
   )
   private Set<Category> categories = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
