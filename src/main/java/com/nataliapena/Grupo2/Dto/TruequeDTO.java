package com.nataliapena.Grupo2.Dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import com.nataliapena.Grupo2.modelos.Trueque;

public class TruequeDTO {
	private String usuarioPublicadorNombre;
	private String usuarioInteresadoNombre;
	private String productoPublicadorNombre;
	 private List<String> productoPublicadorImagenes;
	private String productoInteresadoNombre;
	private List<String> productoInteresadoImagenes;
	private String estadoNombre;
	private Date fechaCreacion;
	private Long trueque_id;
	private Long usuarioPublicadorId;
	private Long usuarioInteresadoId;

	public TruequeDTO(Trueque trueque) {
		this.usuarioInteresadoNombre = trueque.getUsuarioInteresado().getNombre();
		this.usuarioPublicadorNombre = trueque.getUsuarioPublicador().getNombre();
		this.productoInteresadoNombre = trueque.getProductoInteresado().getTitulo();
		this.productoPublicadorNombre = trueque.getProductoPublicador().getTitulo();
		this.usuarioPublicadorId = trueque.getUsuarioPublicador().getId();
		this.usuarioInteresadoId= trueque.getUsuarioInteresado().getId();
		this.trueque_id = trueque.getId();
		this.estadoNombre = trueque.getEstado() != null 
	            ? trueque.getEstado().getNombre() 
	            : "Estado no definido"; 
		this.fechaCreacion = trueque.getFechaCreacion();
		
       
        this.productoPublicadorImagenes = trueque.getProductoPublicador().getImagenes()
            .stream().map(imagen -> imagen.getUrl()).collect(Collectors.toList());
        this.productoInteresadoImagenes = trueque.getProductoInteresado().getImagenes()
            .stream().map(imagen -> imagen.getUrl()).collect(Collectors.toList());
	}
	
	
	

	public Long getUsuarioPublicadorId() {
		return usuarioPublicadorId;
	}




	public void setUsuarioPublicadorId(Long usuarioPublicadorId) {
		this.usuarioPublicadorId = usuarioPublicadorId;
	}




	public Long getUsuarioInteresadoId() {
		return usuarioInteresadoId;
	}




	public void setUsuarioInteresadoId(Long usuarioInteresadoId) {
		this.usuarioInteresadoId = usuarioInteresadoId;
	}




	public Long getTrueque_id() {
		return trueque_id;
	}




	public void setTrueque_id(Long trueque_id) {
		this.trueque_id = trueque_id;
	}




	public List<String> getProductoPublicadorImagenes() {
		return productoPublicadorImagenes;
	}

	


	public void setProductoPublicadorImagenes(List<String> productoPublicadorImagenes) {
		this.productoPublicadorImagenes = productoPublicadorImagenes;
	}




	public List<String> getProductoInteresadoImagenes() {
		return productoInteresadoImagenes;
	}




	public void setProductoInteresadoImagenes(List<String> productoInteresadoImagenes) {
		this.productoInteresadoImagenes = productoInteresadoImagenes;
	}




	public String getUsuarioPublicadorNombre() {
		return usuarioPublicadorNombre;
	}

	public void setUsuarioPublicadorNombre(String usuarioPublicadorNombre) {
		this.usuarioPublicadorNombre = usuarioPublicadorNombre;
	}

	public String getUsuarioInteresadoNombre() {
		return usuarioInteresadoNombre;
	}

	public void setUsuarioInteresadoNombre(String usuarioInteresadoNombre) {
		this.usuarioInteresadoNombre = usuarioInteresadoNombre;
	}

	public String getProductoPublicadorNombre() {
		return productoPublicadorNombre;
	}

	public void setProductoPublicadorNombre(String productoPublicadorNombre) {
		this.productoPublicadorNombre = productoPublicadorNombre;
	}

	public String getProductoInteresadoNombre() {
		return productoInteresadoNombre;
	}

	public void setProductoInteresadoNombre(String productoInteresadoNombre) {
		this.productoInteresadoNombre = productoInteresadoNombre;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	
}
