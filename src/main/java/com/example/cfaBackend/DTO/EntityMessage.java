package com.example.cfaBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityMessage {
    private String roomCode;
    private String username;
    private String entityName;
  
  }