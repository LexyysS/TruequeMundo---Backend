package com.nataliapena.Grupo2.Dto;

import com.nataliapena.Grupo2.modelos.Usuario;

public class ImagenesDTO {
    private Long id;
    private String url;

    public ImagenesDTO(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public ImagenesDTO(Usuario usuario) {
		
	}

	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}