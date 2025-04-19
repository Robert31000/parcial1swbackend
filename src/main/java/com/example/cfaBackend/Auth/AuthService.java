package com.example.cfaBackend.Auth;

import com.example.cfaBackend.Jwt.JwtService;
import com.example.cfaBackend.User.Role; // Importa el enum correcto
import com.example.cfaBackend.User.User; // Importa la clase correcta

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cfaBackend.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
       
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
           .token(token)
           .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .email(request.getEmail())
            .codigo(request.getCodigo())
            .nombre(request.getNombre()) // Nuevo campo
            .apellido(request.getApellido()) // Nuevo campo
            .role(Role.ADMIN) // Asegúrate de que el rol está bien configurado
            .build();
    
        userRepository.save(user);
    
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }
    
    
}
