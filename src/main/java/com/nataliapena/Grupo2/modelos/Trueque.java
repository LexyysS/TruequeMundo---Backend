package com.nataliapena.Grupo2.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "trueques")
public class Trueque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_publicador_id")
    @NotNull(message = "usuario_publicador_id faltante")
    private Usuario usuarioPublicador;
    
    @ManyToOne
    @JoinColumn(name = "usuario_interesado_id")
    @NotNull(message = "usuario_interesado_id faltante")
    private Usuario usuarioInteresado;
    
    @ManyToOne
    @JoinColumn(name = "producto_publicador_id")
    @NotNull(message = "producto_publicador_id faltante")
    private Producto productoPublicador;
    
    @ManyToOne
    @JoinColumn(name = "producto_interesado_id")
    @NotNull(message = "producto_interesado_id faltante")
    private Producto productoInteresado;
    
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    @OneToMany(mappedBy = "trueque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacion> notificaciones = new ArrayList<>();

    
    

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuarioPublicador() {
		return usuarioPublicador;
	}

	public void setUsuarioPublicador(Usuario usuarioPublicador) {
		this.usuarioPublicador = usuarioPublicador;
	}

	public Usuario getUsuarioInteresado() {
		return usuarioInteresado;
	}

	public void setUsuarioInteresado(Usuario usuarioInteresado) {
		this.usuarioInteresado = usuarioInteresado;
	}

	public Producto getProductoPublicador() {
		return productoPublicador;
	}

	public void setProductoPublicador(Producto productoPublicador) {
		this.productoPublicador = productoPublicador;
	}

	public Producto getProductoInteresado() {
		return productoInteresado;
	}

	public void setProductoInteresado(Producto productoInteresado) {
		this.productoInteresado = productoInteresado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}