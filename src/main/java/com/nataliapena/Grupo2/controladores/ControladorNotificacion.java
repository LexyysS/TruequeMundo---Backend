package com.nataliapena.Grupo2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nataliapena.Grupo2.Dto.NotificacionDTO;
import com.nataliapena.Grupo2.servicios.ServicioNotificacion;
import com.nataliapena.Grupo2.servicios.ServicioTrueque;

@RestController
@RequestMapping("/api/notificaciones")
public class ControladorNotificacion {
	
	@Autowired
	ServicioNotificacion servicioNotificacion;
	
	@Autowired
    private ServicioTrueque servicioTrueque;

	  @GetMapping("/usuario/{usuarioId}")
	    public List<NotificacionDTO> obtenerNotificacionesPendientes(@PathVariable Long usuarioId) {
	        return servicioNotificacion.obtenerNotificacionesPendientes(usuarioId);
	    }
	  @PostMapping("/{notificacionId}/marcar-leida")
	  public ResponseEntity<?> marcarComoLeida(@PathVariable Long notificacionId) {
	      servicioNotificacion.marcarComoLeida(notificacionId);
	      return ResponseEntity.ok().build();
	  }
    
    

}
