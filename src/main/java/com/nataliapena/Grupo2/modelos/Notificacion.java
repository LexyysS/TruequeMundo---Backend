package com.nataliapena.Grupo2.modelos;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "notificaciones")
public class Notificacion {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @ManyToOne
	 @JoinColumn(name = "usuario_id", nullable = false)
	 private Usuario usuario; 

	 @ManyToOne(fetch = FetchType.EAGER) // ¡Im
	 @JoinColumn(name = "trueque_id", nullable = false)
	 private Trueque trueque; 
	 
	 private boolean leida = false;
	    
	 @Enumerated(EnumType.STRING)
	 private TipoNotificacion tipo;

	 @Temporal(TemporalType.TIMESTAMP)
	 private Date fechaCreacion;

	 public enum TipoNotificacion {
	     SOLICITUD_TRUQUE,
	     TRUEQUE_ACEPTADO,
	     TRUEQUE_RECHAZADO
	 }

	 @PrePersist
	 protected void onCreate() {
	     this.fechaCreacion = new Date();
	 }
	 
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Trueque getTrueque() {
		return trueque;
	}

	public void setTrueque(Trueque trueque) {
		this.trueque = trueque;
	}



	public boolean isLeida() {
		return leida;
	}



	public void setLeida(boolean leida) {
		this.leida = leida;
	}



	public TipoNotificacion getTipo() {
		return tipo;
	}



	public void setTipo(TipoNotificacion tipo) {
		this.tipo = tipo;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	 
	 

}
