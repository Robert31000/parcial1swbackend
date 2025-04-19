package com.example.cfaBackend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.example.cfaBackend.Entity.Asistencia;
import com.example.cfaBackend.Entity.Category;
import com.example.cfaBackend.Service.AsistenciaService;
import com.example.cfaBackend.Service.CategoryService;
import com.example.cfaBackend.User.User;
import com.example.cfaBackend.User.UserIService;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private UserIService userService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/marcar-por-membresia")
    public ResponseEntity<?> marcarAsistenciaPorMembresiaUnaVez(@RequestBody Map<String, String> payload) {
        String codigo = payload.get("codigoMembresia");
        try {
            asistenciaService.marcarAsistenciaPorMembresiaUnaVez(codigo);
            return ResponseEntity.ok(" Asistencia marcada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/marcar-automatico")
    public ResponseEntity<String> marcarAsistenciaAutomatica() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Asistencia asistencia = new Asistencia();
        asistencia.setDate(LocalDate.now()); // Fecha actual
        asistencia.setTime(LocalTime.now()); // Hora actual
        asistencia.setUser(user);

        asistenciaService.saveAsistencia(asistencia);

        return ResponseEntity.ok("¡Asistencia marcada automáticamente al iniciar sesión!");
    }

   

    @GetMapping("/mis-asistencias")
    public ResponseEntity<List<Asistencia>> obtenerMisAsistencias() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorUsuario(user);

        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Asistencia>> obtenerTodasLasAsistencias() {
        List<Asistencia> asistencias = asistenciaService.getAllAsistencias();
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/por-categoria/{categoryId}")
    public ResponseEntity<List<Asistencia>> obtenerAsistenciasPorCategoria(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorCategoria(category);
        return ResponseEntity.ok(asistencias);
    }


    @PatchMapping("/{id}/editar-aporte")
    public ResponseEntity<?> editarAporte(@PathVariable Long id, @RequestBody Map<String, Double> payload) {
        try {
            Double aporte = payload.get("aporte");
            Asistencia asistencia = asistenciaService.updateAporte(id, aporte);
            return ResponseEntity.ok(asistencia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    
}
