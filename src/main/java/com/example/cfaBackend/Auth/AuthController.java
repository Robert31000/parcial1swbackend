package com.example.cfaBackend.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


import com.example.cfaBackend.Excepcion.RecursoNoEncontradoExcepcion;
import com.example.cfaBackend.User.User;
import com.example.cfaBackend.User.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userIService;
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/users")
    public List<User> obtenerUser() {
        List<User> users = userIService.listaUser();
        users.forEach(user -> logger.info(user.toString()));
        return users;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> obtenerUserPorId(@PathVariable Integer id) {
        User user = userIService.buscaUserPorId(id);
        if (user == null) throw new RecursoNoEncontradoExcepcion("no se encontro el id" + id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> actualizarUser(@PathVariable Integer id, @RequestBody User userRecibido) {
        User user = userIService.buscaUserPorId(id);
        if (user == null) throw new RecursoNoEncontradoExcepcion("El id no recibido, no existe" + id);
        user.setUsername(userRecibido.getUsername());
        user.setEmail(userRecibido.getEmail());
        userIService.guardarUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUser(@PathVariable Integer id) {
        User user = userIService.buscaUserPorId(id);
        if (user == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe" + id);
        userIService.eliminarUser(user);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


    
}
