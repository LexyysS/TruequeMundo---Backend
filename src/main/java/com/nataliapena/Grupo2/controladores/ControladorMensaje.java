package com.nataliapena.Grupo2.controladores;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nataliapena.Grupo2.modelos.Mensaje;
import com.nataliapena.Grupo2.servicios.ServicioMensaje;

@RestController
@RequestMapping("/api/chat")
public class ControladorMensaje {
	
	 private final SimpMessagingTemplate simpMessagingTemplate;
	 
	 @Autowired
	 private ServicioMensaje servMensaje;
	 
	 public ControladorMensaje(SimpMessagingTemplate simpMessagingTemplate, 
             ServicioMensaje servicioMensaje) {
		 this.simpMessagingTemplate = simpMessagingTemplate;
		 this.servMensaje = servicioMensaje;
	 }

	 @PostMapping("/enviar")
	 public Mensaje enviarMensaje(@RequestBody Mensaje mensaje) {
	     return servMensaje.guardarMensaje(mensaje);
	 }

	 @GetMapping("/mensajes/{emisor}/{receptor}")
	 public List<Mensaje> obtenerMensajes(@PathVariable String emisor, @PathVariable String receptor) {
	     return servMensaje.obtenerMensajes(emisor, receptor);
	 }
	 
	 @MessageMapping("/enviarMensaje")
	 public void enviarMensajeWebSocket(@Payload Mensaje mensaje) {
	        // Ordenar IDs para crear un canal único bidireccional
	    List<String> ids = Arrays.asList(mensaje.getEmisor(), mensaje.getReceptor());
	    Collections.sort(ids);  // Ordena los IDs alfabéticamente/numericamente
	        
	    String chatId = "chat_" + String.join("_", ids);
	        
	        // Guardar en base de datos
	    Mensaje guardado = servMensaje.guardarMensaje(mensaje);
	        
	        // Enviar a todos suscritos al canal específico
	    simpMessagingTemplate.convertAndSend("/topic/" + chatId, guardado);
	  }

}
