package com.nataliapena.Grupo2.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nataliapena.Grupo2.Dto.NotificacionDTO;
import com.nataliapena.Grupo2.modelos.Notificacion;
import com.nataliapena.Grupo2.modelos.Notificacion.TipoNotificacion;
import com.nataliapena.Grupo2.modelos.Trueque;
import com.nataliapena.Grupo2.repositorios.RepositorioNotificacion;
import com.nataliapena.Grupo2.repositorios.RepositorioTrueque;

import jakarta.transaction.Transactional;

@Service
public class ServicioNotificacion {
	
	@Autowired
	RepositorioNotificacion repoNotificacion;
	
	@Autowired
	RepositorioTrueque repoTrueque;

  
	 public void crearNotificacionSolicitud(Trueque trueque) {
	        Notificacion notificacion = new Notificacion();
	        notificacion.setUsuario(trueque.getUsuarioPublicador());
	        notificacion.setTrueque(trueque);
	        notificacion.setTipo(Notificacion.TipoNotificacion.SOLICITUD_TRUQUE);
	        repoNotificacion.save(notificacion);
	    }
	    
	    public void crearNotificacionRespuesta(Trueque trueque, boolean aceptada) {
	        Notificacion notificacion = new Notificacion();
	        notificacion.setUsuario(trueque.getUsuarioInteresado());
	        notificacion.setTrueque(trueque);
	        notificacion.setTipo(aceptada ? Notificacion.TipoNotificacion.TRUEQUE_ACEPTADO : Notificacion.TipoNotificacion.TRUEQUE_RECHAZADO);
	        repoNotificacion.save(notificacion);
	    }

	    public List<NotificacionDTO> obtenerNotificacionesPendientes(Long usuarioId) {
	        return repoNotificacion.findByUsuarioIdAndLeidaFalse(usuarioId).stream()
	            .map(notificacion -> new NotificacionDTO(notificacion))
	            .collect(Collectors.toList());
	    }
	    
	    @Transactional
	    public void marcarComoLeida(Long notificacionId) {
	        Notificacion notificacion = repoNotificacion.findById(notificacionId)
	            .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
	        notificacion.setLeida(true);
	        repoNotificacion.save(notificacion);
	    }
}
