package com.nataliapena.Grupo2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nataliapena.Grupo2.modelos.Mensaje;

public interface RepositorioMensaje extends JpaRepository<Mensaje, Long>{
	@Query("SELECT m FROM Mensaje m WHERE (m.emisor = :u1 AND m.receptor = :u2) OR (m.emisor = :u2 AND m.receptor = :u1) ORDER BY m.fechaEnvio ASC")
	List<Mensaje> findByEmisorAndReceptorOrReverse(@Param("u1") String u1, @Param("u2") String u2);


}
