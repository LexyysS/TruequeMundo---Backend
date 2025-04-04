package com.nataliapena.Grupo2.controladores;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nataliapena.Grupo2.Dto.ProductoDTO;
import com.nataliapena.Grupo2.Dto.RegistroDTO;
import com.nataliapena.Grupo2.modelos.Producto;
import com.nataliapena.Grupo2.modelos.Usuario;
import com.nataliapena.Grupo2.servicios.ServicioUsuario;
import com.nataliapena.seguridad.JwtUtil;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ControladorUsuario {

	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/verificar")
	public ResponseEntity<?> verificarToken(@RequestHeader("Authorization") String token) {
	    try {
	        String jwt = token.replace("Bearer ", "");
	        if (jwtUtil.validateToken(jwt)) {
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> ObtenerUsuario(@PathVariable Long id) {
		return ResponseEntity.ok(servicioUsuario.obtenerUsuarioPorId(id));
	}
	
	
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
	    Usuario usuario = servicioUsuario.actualizarUsuario(id, usuarioActualizado);
	    if (usuario != null) {
	        return ResponseEntity.ok(usuario);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	@PostMapping("/registro")
	public ResponseEntity<?> registro(@Valid @RequestBody RegistroDTO usuarioDTO, BindingResult result) {
	    if (result.hasErrors()) {
	        List<String> errores = result.getFieldErrors()
	                .stream()
	                .map(error -> error.getField() + ": " + error.getDefaultMessage())
	                .collect(Collectors.toList());
	        return ResponseEntity.badRequest()
	                .body(Map.of("errores", errores, "mensaje", "Errores de validación"));
	    }

	   
	    Usuario usuario = new Usuario();
	    usuario.setNombre(usuarioDTO.getNombre());
	    usuario.setApellido(usuarioDTO.getApellido());
	    usuario.setEmail(usuarioDTO.getEmail());
	    usuario.setContraseña(usuarioDTO.getContraseña());
	    usuario.setRegion(usuarioDTO.getRegion());
	    usuario.setComuna(usuarioDTO.getComuna());

	    servicioUsuario.agregarUsuario(usuario);
	    return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado exitosamente"));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody RegistroDTO registro) {
	    Usuario usuario = servicioUsuario.validarLogin(registro.getEmail(), registro.getContraseña());
	    
	    if (usuario != null) {
	        String token = jwtUtil.generateToken(usuario.getId(), usuario.getEmail());
	        return ResponseEntity.ok().body(
	            Map.of(
	                "token", token, // Solo envías el token
	                "mensaje", "Inicio de sesión exitoso"
	            )
	        );
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", "Credenciales inválidas para acceder a los trueques"));
	    }
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Saliste de la sesion");
	}
	
	@GetMapping("/favoritos/{usuarioId}")
	public ResponseEntity<List<ProductoDTO>> obtenerFavoritos(@PathVariable Long usuarioId) {
	    List<Producto> favoritos = servicioUsuario.obtenerFavoritosPorUsuario(usuarioId);
	    if (favoritos.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    List<ProductoDTO> favoritosDTO = favoritos.stream()
	        .map(ProductoDTO::new)
	        .collect(Collectors.toList());
	    return ResponseEntity.ok(favoritosDTO);
	}


	@GetMapping("/favoritos/{usuarioId}/{productoId}")
	public void agregarFavorito(@PathVariable Long usuarioId, @PathVariable Long productoId) {
		servicioUsuario.agregarFavorito(usuarioId, productoId);
	}
	
	@DeleteMapping("/favoritos/{usuarioId}/{productoId}")
	public void eliminarFavorito(@PathVariable Long usuarioId, @PathVariable Long productoId) {
		servicioUsuario.eliminarFavorito(usuarioId, productoId);
	}

}
