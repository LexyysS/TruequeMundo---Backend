package com.nataliapena.Grupo2.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nataliapena.Grupo2.modelos.Categoria;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long>{
	Optional<Categoria> findById(Long id);
}
