package com.example.cfaBackend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;     // ← si usas Spring Security
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    // 💾 almacenamiento en memoria; cámbialo luego por BD o servicio
    private final Map<String, Map<String, String>> roomStore = new ConcurrentHashMap<>();

    /* ---------- GET /rooms  (ya lo tenés) ---------- */

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createRoom(
            @RequestBody Map<String, String> body,
            Authentication auth                    // username sale del JWT
    ) {
        // 1. validar el nombre
        String roomName = body.get("room_name");
        if (roomName == null || roomName.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "room_name requerido"));
        }

        // 2. generamos un código (6 caracteres alfanuméricos)
        String roomCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        // 3. simulamos guardar la sala
        roomStore.put(roomCode, Map.of(
                "room_name", roomName,
                "room_code", roomCode,
                "admin", auth != null ? auth.getName() : "anonymous"
        ));

        // 4. respondemos
        return ResponseEntity.ok(Map.of(
                "room_name", roomName,
                "room_code", roomCode
        ));
    }

      /* ---------- GET  /rooms  ---------- */
      @GetMapping
      public ResponseEntity<Map<String, Object>> getUserRooms(Authentication auth) {
  
          // aquí deberías filtrar por usuario (auth.getName());  ahora devolvemos todos
          List<Map<String, String>> adminRooms        = new ArrayList<>();
          List<Map<String, String>> collaboratorRooms = new ArrayList<>();
  
          roomStore.forEach((code, room) -> adminRooms.add(room)); // TODO filtrar
  
          Map<String, Object> resp = new HashMap<>();
          resp.put("adminRooms",        adminRooms);
          resp.put("collaboratorRooms", collaboratorRooms);
  
          return ResponseEntity.ok(resp);
      }
  
      @PostMapping("/join")
      public ResponseEntity<?> joinRoom(@RequestBody Map<String,String> body) {
          String code = body.get("roomCode");
          Map<String,String> room = roomStore.get(code);
          if (room == null) {
            return ResponseEntity.status(404).body(Map.of("error","Sala no encontrada"));
          }
          return ResponseEntity.ok(room);
      }
      
}
