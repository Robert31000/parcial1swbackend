package com.example.cfaBackend.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSockets implements WebSocketMessageBrokerConfigurer {
  
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic"); // destino al que los clientes se suscriben
    config.setApplicationDestinationPrefixes("/app"); // destino para enviar mensajes desde el cliente
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // punto de conexión
  }
}
