package com.nataliapena.Grupo2.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.modelos.Usuario;
import com.nataliapena.Grupo2.repositorios.RepositorioProducto;
import com.nataliapena.Grupo2.repositorios.RepositorioUsuario;

@Service
public class ServicioUsuario {
	@Autowired
	RepositorioUsuario repositorio; 
	@Autowired
	RepositorioProducto repositorioProducto;
	
	public List<Usuario> obtenerUsuarios(){
		return (List<Usuario>) repositorio.findAll();
	}
	
	public List<Producto> obtenerFavoritosPorUsuario(Long usuarioId) {
	    Usuario usuario = repositorio.findById(usuarioId)
	        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    return new ArrayList<>(usuario.getFavoritos());
	}
	
	public Usuario agregarUsuario(Usuario usuario) {
		
		if(repositorio.findByEmail(usuario.getEmail()) == null) {
			usuario.setContraseña(BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt()));
			return repositorio.save(usuario);
		}else {
			throw new RuntimeException("Usuario ya existe");
		}
		
		
	}
	
	public Usuario validarLogin(String email, String password) {
		Usuario usuarioExistente = this.repositorio.findByEmail(email);
		if(usuarioExistente == null) {
			return null;
		} else {
			if(! BCrypt.checkpw(password, usuarioExistente.getContraseña())) {
				return null;
			}
		}
		return usuarioExistente;
	}
	
	
    public void agregarFavorito(Long usuarioId, Long productoId) {
    	Usuario usuario = repositorio.findById(usuarioId).orElse(null);
    	Producto producto = repositorioProducto.findById(productoId).orElse(null);
        usuario.getFavoritos().add(producto);
        repositorio.save(usuario);

    }
    

    public void eliminarFavorito(Long usuarioId, Long productoId) {
    	Usuario usuario = repositorio.findById(usuarioId).orElse(null);
    	Producto producto = repositorioProducto.findById(productoId).orElse(null);
        usuario.getFavoritos().remove(producto);
        repositorio.save(usuario);

    }
    
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistente = repositorio.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setComuna(usuarioActualizado.getComuna());
            usuario.setRegion(usuarioActualizado.getRegion());
           
            return repositorio.save(usuario);
        }
        return null;
    }
	
	
	public Usuario obtenerUsuarioPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public Usuario buscarUsuarioPorEmail(String email) {
	    return repositorio.findByEmail(email);
	}


}
