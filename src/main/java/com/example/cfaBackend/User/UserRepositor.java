package com.example.cfaBackend.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositor extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
