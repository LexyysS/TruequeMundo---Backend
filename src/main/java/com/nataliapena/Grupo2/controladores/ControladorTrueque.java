package com.nataliapena.Grupo2.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nataliapena.Grupo2.Dto.TruequeDTO;
import com.nataliapena.Grupo2.Dto.TruequeRequest;
import com.nataliapena.Grupo2.modelos.Estado;
import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.modelos.Trueque;
import com.nataliapena.Grupo2.modelos.Usuario;
import com.nataliapena.Grupo2.repositorios.RepositorioProducto;
import com.nataliapena.Grupo2.repositorios.RepositorioUsuario;
import com.nataliapena.Grupo2.servicios.ServicioTrueque;

@RestController
@RequestMapping("/api/trueques")
public class ControladorTrueque {

	@Autowired
	ServicioTrueque servicioTrueque;
	
	 @Autowired
	 private RepositorioUsuario repositorioUsuario;

	 @Autowired
	 private RepositorioProducto repositorioProducto;

	@GetMapping("/mistrueques/{usuarioId}")
	public ResponseEntity<?> misTrueques(@PathVariable Long usuarioId) {
		List<TruequeDTO> trueques = servicioTrueque.misTrueques(usuarioId);

		if (trueques != null) {
			return ResponseEntity.ok(trueques);
		} else {
			return ResponseEntity.badRequest().body("No existen trueques aún");
		}
	}

	@GetMapping("/vertrueques/{usuarioId}")
	public ResponseEntity<?> verTrueques(@PathVariable Long usuarioId) {
		List<TruequeDTO> trueques = servicioTrueque.verTrueques(usuarioId);

		if (trueques != null) {
			return ResponseEntity.ok(trueques);
		} else {
			return ResponseEntity.badRequest().body("No existen trueques aún");
		}
	}
	

	@PostMapping("/{truequeId}/responder")
    public ResponseEntity<?> responderTrueque(
        @PathVariable Long truequeId, 
        @RequestParam(name = "aceptar") boolean aceptar 
    ) {
        try {
            servicioTrueque.responderTrueque(truequeId, aceptar);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


	

	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody TruequeRequest truequeRequest) {
        try {
           
            Trueque truequeCreado = servicioTrueque.crearTrueque(truequeRequest);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(new TruequeDTO(truequeCreado));
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "mensaje", e.getMessage(),
                "errores", List.of(e.getMessage())
            ));
        }
    }

	@PutMapping("/cambiarestado")
	public ResponseEntity<?> cambiarEstado(@RequestBody Map<String, Object> request) {
	    Long estado = ((Number) request.get("estado")).longValue();
	    Long truequeId = ((Number) request.get("truequeId")).longValue();
	    
		Trueque trueque = servicioTrueque.obtenerTruequePorId(truequeId);

		Estado nuevoEstado = new Estado();
		nuevoEstado.setId(estado);
	    trueque.setEstado(nuevoEstado);
	    servicioTrueque.actualizarTrueque(trueque);

	    return ResponseEntity.ok().body(null);
		
	}
	
	private Trueque convertirRequestAEntidad(TruequeRequest request) {
	    Trueque trueque = new Trueque();
	    
	    // Obtener entidades desde los IDs
	    Usuario usuarioPublicador = repositorioUsuario.findById(request.getUsuarioPublicadorId()).orElse(null);
	    Usuario usuarioInteresado = repositorioUsuario.findById(request.getUsuarioInteresadoId()).orElse(null);
	    Producto productoPublicador = repositorioProducto.findById(request.getProductoPublicadorId()).orElse(null);
	    Producto productoInteresado = repositorioProducto.findById(request.getProductoInteresadoId()).orElse(null);
	    
	    // Asignar al trueque
	    trueque.setUsuarioPublicador(usuarioPublicador);
	    trueque.setUsuarioInteresado(usuarioInteresado);
	    trueque.setProductoPublicador(productoPublicador);
	    trueque.setProductoInteresado(productoInteresado);
	    
	    return trueque;
	}
	
	
}
