package com.nataliapena.Grupo2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nataliapena.Grupo2.Dto.ProductoDTO;
import com.nataliapena.Grupo2.Dto.ProductoRequest;
import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.repositorios.RepositorioCategoria;
import com.nataliapena.Grupo2.repositorios.RepositorioUsuario;
import com.nataliapena.Grupo2.servicios.ServicioProducto;

@RestController
@RequestMapping("/api/productos")
public class ControladorProducto {
	
	@Autowired
	private ServicioProducto servProducto;
	
	@Autowired
	private RepositorioCategoria repoCategoria;
	
	@Autowired
	private RepositorioUsuario repoUsuario;
	

	@GetMapping
    public List<ProductoDTO> obtenerProductos() {
        return servProducto.obtenerProductos();
    }
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<ProductoDTO>> obtenerProductosPorUsuario(@PathVariable Long usuarioId) {
	    List<ProductoDTO> productos = servProducto.obtenerProductosPorUsuario(usuarioId);
	    if (productos.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(productos);
	}
	

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoId(@PathVariable Long id) {
        return servProducto.obtenerProductoId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/agregar")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoRequest productoRequest) {
        try {
            Producto productoCreado = servProducto.crearProducto(productoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ProductoDTO(productoCreado));
        } catch (Exception ex) {
            ex.printStackTrace(); // Log del error
            return ResponseEntity.badRequest().body("Error al crear el producto: " + ex.getMessage());
        }
    }
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        try {
            
            Producto productoActualizado = servProducto.actualizarProducto(id, productoRequest);

           
            return ResponseEntity.ok(new ProductoDTO(productoActualizado));
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return servProducto.eliminarProducto(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/recientes")
    public List<ProductoDTO> obtenerProductosRecientes() {
        return servProducto.obtenerUltimos6Productos();
    }
	

}
