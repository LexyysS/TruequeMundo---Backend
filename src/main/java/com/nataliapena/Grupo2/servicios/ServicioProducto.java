package com.nataliapena.Grupo2.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nataliapena.Grupo2.Dto.ProductoDTO;
import com.nataliapena.Grupo2.Dto.ProductoRequest;
import com.nataliapena.Grupo2.modelos.Categoria;
import com.nataliapena.Grupo2.modelos.Imagen;
import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.modelos.Trueque;
import com.nataliapena.Grupo2.modelos.Usuario;
import com.nataliapena.Grupo2.repositorios.RepositorioCategoria;
import com.nataliapena.Grupo2.repositorios.RepositorioImagen;
import com.nataliapena.Grupo2.repositorios.RepositorioNotificacion;
import com.nataliapena.Grupo2.repositorios.RepositorioProducto;
import com.nataliapena.Grupo2.repositorios.RepositorioTrueque;
import com.nataliapena.Grupo2.repositorios.RepositorioUsuario;

import jakarta.transaction.Transactional;

@Service
public class ServicioProducto {

	@Autowired
	private RepositorioProducto repoProducto;
	

	@Autowired
	private RepositorioCategoria repoCategoria;
	
	@Autowired
	private RepositorioUsuario repoUsuario;
	
	@Autowired
	private RepositorioImagen repoImagen;
	
	@Autowired
	private RepositorioTrueque repoTrueque;
	
	@Autowired
	private RepositorioNotificacion repoNotificacion;

	public List<ProductoDTO> obtenerProductosPorUsuario(Long usuarioId) {
	    List<Producto> productos = repoProducto.findByUsuarioId(usuarioId);
	    return productos.stream()
	            .map(producto -> new ProductoDTO(producto))
	            .collect(Collectors.toList());
	}


	public List<ProductoDTO> obtenerProductos() {
		List<Producto> productos = repoProducto.findAll();
        return productos.stream().map(ProductoDTO::new).collect(Collectors.toList());
	}

	public Optional<ProductoDTO> obtenerProductoId(Long id) {
		return repoProducto.findById(id).map(ProductoDTO::new);
	}
	
	@Transactional 
	public Producto crearProducto(ProductoRequest productoRequest) {
	    
	    Optional<Usuario> usuarioOpt = repoUsuario.findById(productoRequest.getUsuarioId());
	    if (!usuarioOpt.isPresent()) {
	        throw new RuntimeException("Usuario no encontrado");
	    }

	   
	    Set<Categoria> categorias = productoRequest.getCategorias().stream()
	        .map(repoCategoria::findById)
	        .filter(Optional::isPresent)
	        .map(Optional::get)
	        .collect(Collectors.toSet());

	    //️Crear el producto
	    Producto producto = new Producto();
	    producto.setTitulo(productoRequest.getTitulo());
	    producto.setDescripcion(productoRequest.getDescripcion());
	    producto.setValor_referencia(productoRequest.getValor_referencia());
	    producto.setEstado(productoRequest.getEstado());
	    producto.setUsuario(usuarioOpt.get());
	    producto.setCategorias(categorias);

	   
	    Producto productoGuardado = repoProducto.save(producto);

	  
	    if (productoRequest.getImagenes() != null && !productoRequest.getImagenes().isEmpty()) {
	        List<Imagen> imagenes = productoRequest.getImagenes().stream()
	            .map(url -> {
	                Imagen img = new Imagen();
	                img.setUrl(url);
	                img.setProducto(productoGuardado);
	                return img;
	            })
	            .collect(Collectors.toList());
	        repoImagen.saveAll(imagenes);
	    }

	    return productoGuardado;
	}
	
	public Producto actualizarProducto(Long id, ProductoRequest productoRequest) {
      
        Optional<Producto> productoOpt = repoProducto.findById(id);
        if (!productoOpt.isPresent()) {
            throw new RuntimeException("Producto no encontrado");
        }

     
        Producto producto = productoOpt.get();

        // Verificar que el usuario existe
        Optional<Usuario> usuarioOpt = repoUsuario.findById(productoRequest.getUsuarioId());
        if (!usuarioOpt.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Obtener las categorías por sus IDs
        Set<Categoria> categorias = new HashSet<>();
        for (Long categoriaId : productoRequest.getCategorias()) {
            Optional<Categoria> categoria = repoCategoria.findById(categoriaId);
            categoria.ifPresent(categorias::add);  // Agregar solo si la categoría existe
        }

        // Actualizar los detalles del producto
        producto.setTitulo(productoRequest.getTitulo());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setValor_referencia(productoRequest.getValor_referencia());
        producto.setEstado(productoRequest.getEstado());
        producto.setUsuario(usuarioOpt.get());  
        producto.setCategorias(categorias);  

       
        return repoProducto.save(producto);
    }
	
	 @Transactional
	 public boolean eliminarProducto(Long id) {
	        return repoProducto.findById(id).map(producto -> {
	            
	            producto.getCategorias().clear();
	            repoProducto.saveAndFlush(producto);

	        
	            List<Imagen> imagenes = repoImagen.findByProductoId(producto.getId());
	            if (!imagenes.isEmpty()) {
	                repoImagen.deleteAll(imagenes);
	            }

	          
	            List<Trueque> truequesPublicador = repoTrueque.findByProductoPublicador(producto);
	            List<Trueque> truequesInteresado = repoTrueque.findByProductoInteresado(producto);
	            List<Trueque> allTrueques = new ArrayList<>();
	            allTrueques.addAll(truequesPublicador);
	            allTrueques.addAll(truequesInteresado);

	         
	            if (!allTrueques.isEmpty()) {
	                repoNotificacion.deleteByTruequeIn(allTrueques);
	            }

	            if (!truequesPublicador.isEmpty()) {
	                repoTrueque.deleteAll(truequesPublicador);
	            }
	            if (!truequesInteresado.isEmpty()) {
	                repoTrueque.deleteAll(truequesInteresado);
	            }

	        
	            repoProducto.delete(producto);

	            return true;
	        }).orElse(false);
	    }

	   
	
	public List<ProductoDTO> obtenerUltimos6Productos() {
        List<Producto> productos = repoProducto.findTop6ByOrderByCreatedAtDesc();
        return productos.stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

}
