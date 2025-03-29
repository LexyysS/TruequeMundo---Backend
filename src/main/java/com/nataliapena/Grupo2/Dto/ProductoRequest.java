package com.nataliapena.Grupo2.Dto;

import java.util.List;

public class ProductoRequest {
	private String titulo;
    private String descripcion;
    private Long valor_referencia;
    private String estado;
    private Long usuarioId;
    private List<Long> categorias;
    private List<String> imagenes;
    
    public ProductoRequest() {
    	
    }
    
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getValor_referencia() {
		return valor_referencia;
	}

	public void setValor_referencia(Long valor_referencia) {
		this.valor_referencia = valor_referencia;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public List<Long> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Long> categorias) {
		this.categorias = categorias;
	}

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}
	
	
	
	 	
	 

}
