package com.example.cfaBackend.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIService implements UserService {

    @Autowired
    private UserRepositor userRepositor;

    @Override
    public List<User> listaUser() {
        return userRepositor.findAll();
    }

    @Override
    public User buscaUserPorId(Integer idUser) {
        return userRepositor.findById(idUser).orElse(null);
    }

    @Override
    public User guardarUser(User user) {
        return userRepositor.save(user);
    }

    @Override
    public void eliminarUser(User user) {
        userRepositor.delete(user);
    }

    // Método para encontrar un usuario por su nombre de usuario
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepositor.findByUsername(username);
    }
}
