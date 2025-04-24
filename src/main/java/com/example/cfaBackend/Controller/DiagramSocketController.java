package com.example.cfaBackend.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.cfaBackend.DTO.EntityMessage;

@Controller
public class DiagramSocketController {

  @MessageMapping("/add-entity") // Cliente envía a /app/add-entity
  @SendTo("/topic/entities")     // Todos escuchan en /topic/entities
  public EntityMessage addEntity(EntityMessage message) {
    System.out.println("Entidad agregada por: " + message.getUsername());
    return message;
  }
}
