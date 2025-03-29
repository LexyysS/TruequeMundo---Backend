package com.nataliapena.Grupo2.Dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.nataliapena.Grupo2.modelos.Imagen;
import com.nataliapena.Grupo2.modelos.Notificacion;
import com.nataliapena.Grupo2.modelos.Notificacion.TipoNotificacion;
import com.nataliapena.Grupo2.modelos.Trueque;

public class NotificacionDTO {
	
	 private Long id;
	    private Notificacion.TipoNotificacion tipo;
	    private Date fechaCreacion;
	    private TruequeDTO truequeDTO;
	    private boolean leida;

	   
	    public NotificacionDTO(Notificacion notificacion) {
	        this.id = notificacion.getId();
	        this.tipo = notificacion.getTipo();
	        this.fechaCreacion = notificacion.getFechaCreacion();
	        this.leida = notificacion.isLeida();
	        
	       
	        this.truequeDTO = new TruequeDTO(notificacion.getTrueque());
	    }

    
	public TipoNotificacion getTipo() {
		return tipo;
	}


	public void setTipo(TipoNotificacion tipo) {
		this.tipo = tipo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public TruequeDTO getTruequeDTO() {
		return truequeDTO;
	}


	public void setTruequeDTO(TruequeDTO truequeDTO) {
		this.truequeDTO = truequeDTO;
	}
    
	
    

}
