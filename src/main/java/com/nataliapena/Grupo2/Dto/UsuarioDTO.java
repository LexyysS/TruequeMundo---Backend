package com.nataliapena.Grupo2.Dto;

import com.nataliapena.Grupo2.modelos.Usuario;

public class UsuarioDTO {
	 private Long id;
	 private String nombre;
	 private String region;
	 private String comuna;
	

	 public UsuarioDTO(Usuario usuario) {
	    this.id = usuario.getId();
	    this.nombre = usuario.getNombre();
	    this.region = usuario.getRegion();
	    this.comuna = usuario.getComuna();
	    
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}


	
	
	
	 
	 
}
