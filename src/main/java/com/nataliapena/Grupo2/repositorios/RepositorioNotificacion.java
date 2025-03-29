package com.nataliapena.Grupo2.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nataliapena.Grupo2.modelos.Notificacion;
import com.nataliapena.Grupo2.modelos.Trueque;

import jakarta.transaction.Transactional;

public interface RepositorioNotificacion extends JpaRepository<Notificacion, Long>{
	@Query("SELECT n FROM Notificacion n WHERE n.usuario.id = :usuarioId AND n.leida = false AND n.tipo = 'SOLICITUD_TRUQUE'")
	List<Notificacion> findPendientesByUsuario(@Param("usuarioId") Long usuarioId);
    Optional<Notificacion> findByTruequeAndTipo(Trueque trueque, Notificacion.TipoNotificacion tipo);

	List<Notificacion> findByUsuarioId(Long usuarioId);
	@Query("SELECT n FROM Notificacion n WHERE n.usuario.id = :usuarioId AND n.leida = false")
    List<Notificacion> findByUsuarioIdAndLeidaFalse(@Param("usuarioId") Long usuarioId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Notificacion n WHERE n.trueque IN :trueques")
    void deleteByTruequeIn(@Param("trueques") List<Trueque> trueques);
	
	
}
