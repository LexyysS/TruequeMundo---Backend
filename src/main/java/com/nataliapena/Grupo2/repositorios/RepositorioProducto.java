package com.nataliapena.Grupo2.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nataliapena.Grupo2.modelos.Producto;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {
	List<Producto> findByUsuarioId(Long usuarioId);
	List<Producto> findTop6ByOrderByCreatedAtDesc();


}


