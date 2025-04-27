package com.example.cfaBackend.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.cfaBackend.DTO.ChatMsgDTO;
import com.example.cfaBackend.DTO.PatchDTO;
import com.example.cfaBackend.Service.CanvasService;
import com.example.cfaBackend.Service.ChatService;

@Controller
@RequiredArgsConstructor
public class RealtimeController {

  private final CanvasService canvasService;
  private final ChatService chatService;
  private final SimpMessagingTemplate simp;

  /* ---------- Lienzo (patches) ---------- */
  @MessageMapping("/project/{id}/update")
  public void updateCanvas(@DestinationVariable Long id,
                           @Payload PatchDTO patch) {
    canvasService.applyPatch(id, patch);                       // persiste
    simp.convertAndSend("/topic/project/" + id, patch);        // broadcast
  }

  /* --------------- Chat ----------------- */
  @MessageMapping("/chat/{id}")
  public void chat(@DestinationVariable Long id,
                   @Payload ChatMsgDTO msg) {
    chatService.save(id, msg);
    simp.convertAndSend("/topic/chat/" + id, msg);             // broadcast
  }
}