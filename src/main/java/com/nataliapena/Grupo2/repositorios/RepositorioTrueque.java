package com.nataliapena.Grupo2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.modelos.Trueque;

@Repository
public interface RepositorioTrueque extends JpaRepository<Trueque, Long> {
	List<Trueque> findAll();
	List<Trueque> findByUsuarioInteresadoIdOrUsuarioPublicadorId(Long usuarioInteresadoId, Long usuarioPublicadorId);
	List<Trueque> findByUsuarioInteresadoIdOrUsuarioPublicadorIdAndEstadoId(Long usuarioInteresadoId, Long usuarioPublicadorId, Long estadoId);
	List<Trueque> findByEstadoNombre(String estado);
	
	List<Trueque> findByProductoPublicadorId(Long productoId);
    
   
    List<Trueque> findByProductoInteresadoId(Long productoId);
    List<Trueque> findByProductoPublicador(Producto producto);
    List<Trueque> findByProductoInteresado(Producto producto);
   
    List<Trueque> findByProductoPublicadorOrProductoInteresado(Producto publicador, Producto interesado);
    
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Trueque t WHERE t.productoPublicador = :producto")
    void deleteByProductoPublicador(@Param("producto") Producto producto);
    
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Trueque t WHERE t.productoInteresado = :producto")
    void deleteByProductoInteresado(@Param("producto") Producto producto);

}
