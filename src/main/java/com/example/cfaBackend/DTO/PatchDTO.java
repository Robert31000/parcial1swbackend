package com.example.cfaBackend.DTO;

import lombok.Data;

@Data
public class PatchDTO {
  private String op;       // "add" | "update" | "delete"
  private String nodeId;   // id del objeto (Rect, Text …)
  private Object attrs;    // mapa con cambios {x:10,y:20,…}
}
