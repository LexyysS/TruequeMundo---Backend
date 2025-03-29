package com.nataliapena.Grupo2.servicios;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.nataliapena.Grupo2.Dto.TruequeDTO;
import com.nataliapena.Grupo2.Dto.TruequeRequest;
import com.nataliapena.Grupo2.modelos.Estado;
import com.nataliapena.Grupo2.modelos.Notificacion;
import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.modelos.Trueque;
import com.nataliapena.Grupo2.repositorios.RepositorioEstado;
import com.nataliapena.Grupo2.repositorios.RepositorioNotificacion;
import com.nataliapena.Grupo2.repositorios.RepositorioProducto;
import com.nataliapena.Grupo2.repositorios.RepositorioTrueque;
import com.nataliapena.Grupo2.repositorios.RepositorioUsuario;

import jakarta.transaction.Transactional;

@Service
public class ServicioTrueque {

	@Autowired
	private final RepositorioTrueque repositorioTrueque;

	@Autowired
	private final RepositorioProducto repositorioProducto;

	@Autowired
	private final RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private final RepositorioEstado repositorioEstado;
	
	@Autowired
	private final RepositorioNotificacion repoNotificacion;
	@Autowired
	private final ServicioNotificacion servicioNotificacion;

	public ServicioTrueque(RepositorioTrueque repositorioTrueque, RepositorioProducto repositorioProducto,
			RepositorioUsuario repositorioUsuario , ServicioNotificacion servicioNotificacion, RepositorioEstado repositorioEstado, RepositorioNotificacion repoNotificacion) {
		this.repositorioTrueque = repositorioTrueque;
		this.repositorioProducto = repositorioProducto;
		this.repositorioUsuario = repositorioUsuario;
		this.servicioNotificacion = servicioNotificacion;
		this.repositorioEstado = repositorioEstado;
		this.repoNotificacion = repoNotificacion;
	}

	public List<TruequeDTO> misTrueques(Long usuarioId) {
		
		List<Trueque> trueques = repositorioTrueque.findByUsuarioInteresadoIdOrUsuarioPublicadorId(usuarioId, usuarioId);
		if(trueques != null) {
			return trueques.stream().map(TruequeDTO::new).collect(Collectors.toList());			
		} else {
			return null;
		}
	}
	
	public List<TruequeDTO> verTrueques(Long usuarioId) {
		
		List<Trueque> trueques = repositorioTrueque.findByUsuarioInteresadoIdOrUsuarioPublicadorIdAndEstadoId(usuarioId, usuarioId, 2L);
		if(trueques != null) {
			return trueques.stream().map(TruequeDTO::new).collect(Collectors.toList());			
		} else {
			return null;
		}
	}
	

	public Trueque obtenerTruequePorId(Long id) {
		return repositorioTrueque.findById(id).orElse(null);
	}

	public BindingResult validacionesTrueque(Trueque trueque) {
		BindingResult result = new BeanPropertyBindingResult(trueque, "trueque");

		
		if (trueque.getUsuarioInteresado() == null
				|| !repositorioUsuario.existsById(trueque.getUsuarioInteresado().getId())) {
			result.addError(new FieldError("trueque", "usuarioInteresado", "El usuario interesado no existe"));
		}

		
		if (trueque.getProductoInteresado() == null
				|| trueque.getProductoInteresado().getId() == null) {
			result.addError(new FieldError("trueque", "productoInteresado",
					"El producto ofrecido por el interesado es obligatorio"));
		} else {
			Producto productoOfrecidoPorInteresado = repositorioProducto
					.findById(trueque.getProductoInteresado().getId()).orElse(null);
			if (productoOfrecidoPorInteresado == null || !productoOfrecidoPorInteresado.getUsuario().getId()
					.equals(trueque.getUsuarioInteresado().getId())) {
				result.addError(new FieldError("trueque", "productoInteresado",
						"El producto ofrecido por el interesado no pertenece a él"));
			}
		}

		
		if (trueque.getUsuarioPublicador() == null
				|| !repositorioUsuario.existsById(trueque.getUsuarioPublicador().getId())) {
			result.addError(new FieldError("trueque", "usuarioPublicador", "El usuario publicador no existe"));
		}

		
		if (trueque.getProductoPublicador() == null || trueque.getProductoPublicador().getId() == null) {
			result.addError(new FieldError("trueque", "productoPublicado", "El producto publicado es obligatorio"));
		} else {
			Producto productoPublicado = repositorioProducto.findById(trueque.getProductoPublicador().getId())
					.orElse(null);
			if (productoPublicado == null
					|| !productoPublicado.getUsuario().getId().equals(trueque.getUsuarioPublicador().getId())) {
				result.addError(new FieldError("trueque", "productoPublicado",
						"El producto publicado no pertenece al usuario publicador"));
			}
		}

		return result;
	}
	
	public Trueque crearTrueque(TruequeRequest request) {
        Trueque trueque = new Trueque();
        
        trueque.setUsuarioPublicador(repositorioUsuario.findById(request.getUsuarioPublicadorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        
        trueque.setUsuarioInteresado(repositorioUsuario.findById(request.getUsuarioInteresadoId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        
        trueque.setProductoPublicador(repositorioProducto.findById(request.getProductoPublicadorId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        
        trueque.setProductoInteresado(repositorioProducto.findById(request.getProductoInteresadoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));

       
        Estado estado = repositorioEstado.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estado 'Esperando respuesta' no encontrado"));
            
        trueque.setEstado(estado);
        trueque.setFechaCreacion(new Date());

        Trueque savedTrueque = repositorioTrueque.save(trueque);
            
          
        servicioNotificacion.crearNotificacionSolicitud(savedTrueque);
            
        return savedTrueque;
    }

	@Transactional
	public void responderTrueque(Long truequeId, boolean aceptar) {
	    Trueque trueque = repositorioTrueque.findById(truequeId)
	        .orElseThrow(() -> new RuntimeException("Trueque no encontrado"));

	  
	    String nombreEstado = aceptar ? "Trueque aceptado" : "Trueque rechazado"; 
	    Estado estado = repositorioEstado.findByNombre(nombreEstado)
	        .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
	    trueque.setEstado(estado);
	    
	    Notificacion notificacionOriginal = repoNotificacion.findByTruequeAndTipo(
	        trueque, 
	        Notificacion.TipoNotificacion.SOLICITUD_TRUQUE
	    ).orElseThrow(() -> new RuntimeException("Notificación original no encontrada"));
	    
	    notificacionOriginal.setLeida(true);
	    notificacionOriginal.setTipo(aceptar ? 
	        Notificacion.TipoNotificacion.TRUEQUE_ACEPTADO : 
	        Notificacion.TipoNotificacion.TRUEQUE_RECHAZADO
	    );
	    repoNotificacion.save(notificacionOriginal);

	  
	    servicioNotificacion.crearNotificacionRespuesta(trueque, aceptar);
	}
	

	public Trueque agregarTrueque(Trueque trueque) {
		return this.repositorioTrueque.save(trueque);
	}

	public Trueque actualizarTrueque(Trueque trueque) {
		return this.repositorioTrueque.save(trueque);
	}

	public void eliminarTrueque(Long id) {
		this.repositorioTrueque.deleteById(id);
	}

}
