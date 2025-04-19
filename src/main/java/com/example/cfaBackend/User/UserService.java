package com.example.cfaBackend.User;
import java.util.List;


public interface UserService {

    public List<User> listaUser();

    public User buscaUserPorId(Integer idUser);

    public User guardarUser(User user);

    public void eliminarUser(User user);

    public Object findByUsername(String username);


    
    
}
