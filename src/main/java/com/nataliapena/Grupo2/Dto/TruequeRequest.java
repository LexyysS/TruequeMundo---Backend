package com.nataliapena.Grupo2.Dto;

public class TruequeRequest {
	private Long usuarioPublicadorId;
    private Long usuarioInteresadoId;
    private Long productoPublicadorId;
    private Long productoInteresadoId;
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
	public Long getProductoPublicadorId() {
		return productoPublicadorId;
	}
	public void setProductoPublicadorId(Long productoPublicadorId) {
		this.productoPublicadorId = productoPublicadorId;
	}
	public Long getProductoInteresadoId() {
		return productoInteresadoId;
	}
	public void setProductoInteresadoId(Long productoInteresadoId) {
		this.productoInteresadoId = productoInteresadoId;
	}
    
    
}
