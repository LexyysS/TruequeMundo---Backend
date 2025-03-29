package com.nataliapena.Grupo2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nataliapena.Grupo2.modelos.Imagen;

@Repository
public interface RepositorioImagen extends JpaRepository<Imagen, Long>{
	List<Imagen> findByProductoId(Long productoId);
}
