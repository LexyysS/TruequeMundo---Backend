package com.nataliapena.Grupo2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nataliapena.Grupo2.modelos.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository <Usuario, Long>{
	Usuario findByEmail(String email);

	

		
}
