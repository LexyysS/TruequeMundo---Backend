package com.nataliapena.Grupo2.Dto;


import java.util.stream.Collectors;
import java.util.List;

import java.util.Set;


import com.nataliapena.Grupo2.modelos.Producto;

public class ProductoDTO {
	 private Long id;
	 private String titulo;
	 private String descripcion;
	 private Long valor_referencia;
	 private String estado;
	 private UsuarioDTO usuario;

	 private List<ImagenesDTO> imagenes;

	 private Set<CategoriaDTO> categorias;


	 public ProductoDTO(Producto producto) {
	    this.id = producto.getId();
	    this.titulo = producto.getTitulo();
	    this.descripcion = producto.getDescripcion();
	    this.valor_referencia = producto.getValor_referencia();
	    this.estado = producto.getEstado();
	    this.usuario = new UsuarioDTO(producto.getUsuario()); 

        this.setImagenes(producto.getImagenes().stream()
        		.map(imagen -> new ImagenesDTO(imagen.getId(), imagen.getUrl()))
                .collect(Collectors.toList()));

	    this.categorias = producto.getCategorias().stream()
                .map(categoria -> new CategoriaDTO(categoria))
                .collect(Collectors.toSet());

	 }
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public UsuarioDTO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}



	public List<ImagenesDTO> getImagenes() {
		return imagenes;
	}


	public void setImagenes(List<ImagenesDTO> imagenes) {
		this.imagenes = imagenes;
	}


	public Set<CategoriaDTO> getCategorias() {
		return categorias;
	}


	public void setCategorias(Set<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}


	
	 
	 
	 
	 
}
