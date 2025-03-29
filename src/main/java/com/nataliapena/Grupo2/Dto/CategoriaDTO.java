package com.nataliapena.Grupo2.Dto;

import com.nataliapena.Grupo2.modelos.Categoria;

public class CategoriaDTO {
	private Long id;
    private String nombre;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
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
    
}
