package com.nataliapena.Grupo2.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nataliapena.Grupo2.modelos.Estado;

@Repository
public interface RepositorioEstado extends JpaRepository<Estado, Long> {

	List<Estado> findAll();
	Optional<Estado> findByNombre(String nombre);
}
