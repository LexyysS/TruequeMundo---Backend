package com.nataliapena.Grupo2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nataliapena.Grupo2.modelos.Estado;
import com.nataliapena.Grupo2.repositorios.RepositorioEstado;

public class ServicioEstado {

	@Autowired
	private final RepositorioEstado repositorioEstado;

	public ServicioEstado(RepositorioEstado repositorioEstado) {
		this.repositorioEstado = repositorioEstado;
	}

	public List<Estado> obtenerEstado() {
		return repositorioEstado.findAll();
	}

	public Estado obtenerEstadoPorId(Long id) {
		return repositorioEstado.findById(id).orElse(null);
	}

	public Estado agregarEstado(Estado estado) {
		return this.repositorioEstado.save(estado);
	}

	public Estado actualizarEstado(Estado estado) {
		return this.repositorioEstado.save(estado);
	}

	public void eliminarEstado(Long id) {
		this.repositorioEstado.deleteById(id);
	}

}
