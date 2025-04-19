package com.example.cfaBackend.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas las rutas
            .allowedOrigins("*")   // Permite todas las solicitudes desde cualquier origen
            .allowedMethods("POST","GET","PUT","DELETE","PATCH") // Métodos permitidos
            .allowedHeaders("*")   // Permite todos los headers
            .exposedHeaders("Authorization") // Expones el header Authorization
            .maxAge(3600);  // Cacheo de las políticas CORS por una hora
    }
}
