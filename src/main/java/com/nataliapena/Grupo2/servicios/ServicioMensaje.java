package com.nataliapena.Grupo2.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nataliapena.Grupo2.modelos.Mensaje;
import com.nataliapena.Grupo2.repositorios.RepositorioMensaje;

@Service
public class ServicioMensaje {
	
	@Autowired
    private RepositorioMensaje repoMensaje;

    public Mensaje guardarMensaje(Mensaje mensaje) {
    	mensaje.setFechaEnvio(new Date());
        return repoMensaje.save(mensaje);
    }

    public List<Mensaje> obtenerMensajes(String usuario1, String usuario2) {
    	return repoMensaje.findByEmisorAndReceptorOrReverse(usuario1, usuario2);
    }
}
