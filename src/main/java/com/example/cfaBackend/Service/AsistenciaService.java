package com.example.cfaBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cfaBackend.Entity.Asistencia;
import com.example.cfaBackend.Entity.Category;
import com.example.cfaBackend.Repository.AsistenciaRepository;
import com.example.cfaBackend.User.User;
import com.example.cfaBackend.User.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private UserRepository userRepository; 

    //Marcar asistencia solo 1 vez
    public Asistencia marcarAsistenciaPorMembresiaUnaVez(String codigoMembresia) throws Exception {
        // Buscar al usuario por el código de membresía
        User user = userRepository.findByCodigo(codigoMembresia);
        if (user == null) {
            throw new Exception("Número de membresía no válido");
        }

        // Verificar si ESTE usuario ya marcó asistencia hoy
        LocalDate hoy = LocalDate.now();
        Optional<Asistencia> asistenciaExistente = asistenciaRepository.findByUserAndDate(user, hoy);

        if (asistenciaExistente.isPresent()) {
            throw new Exception("Este usuario ya marcó asistencia hoy.");
        }

        // Si no ha marcado asistencia, registrar
        Asistencia nuevaAsistencia = new Asistencia();
        nuevaAsistencia.setUser(user);
        nuevaAsistencia.setDate(hoy);
        nuevaAsistencia.setTime(LocalTime.now());

        return asistenciaRepository.save(nuevaAsistencia);
    }
    
    

    public Asistencia saveAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }


    public Asistencia updateAporte(Long id, Double aporte) throws Exception {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new Exception("Asistencia no encontrada"));
        asistencia.setAporte(aporte);
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Asistencia getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    public void deleteAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }

    // Método para obtener las asistencias por usuario
    public List<Asistencia> getAsistenciasByUser(User user) {
        return asistenciaRepository.findByUser(user);
    }

    public List<Asistencia> obtenerAsistenciasPorUsuario(User user) {
        return asistenciaRepository.findByUser(user);
    }

    public List<Asistencia> obtenerAsistenciasPorCategoria(Category category) {
        return asistenciaRepository.findByUserCategories(category);
    }

    // Nuevo método para marcar asistencia por número de membresía
    public Asistencia marcarAsistenciaPorMembresia(String codigoMembresia) throws Exception {
        // Buscar al usuario por el código de membresía
        User user = userRepository.findByCodigo(codigoMembresia);
        if (user == null) {
            throw new Exception("Número de membresía no válido");
        }

        // Crear un registro de asistencia con fecha y hora actuales
        Asistencia asistencia = new Asistencia();
        asistencia.setUser(user);
        asistencia.setDate(LocalDate.now());
        asistencia.setTime(LocalTime.now());

        // Guardar el registro de asistencia
        return asistenciaRepository.save(asistencia);
    }


    
}
