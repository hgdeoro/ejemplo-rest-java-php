package com.example;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Representa un actuador (ej: un rele)
 */
@Path("/actuador/{id_actaudor}/")
public class Actuador {

	private final Arduino arduino;

	public Actuador(Arduino arduino) {
		this.arduino = arduino;
	}

	@POST
	@Path("/escribir")
	@Produces(MediaType.TEXT_PLAIN)
	public String escribirEnActuador(@PathParam("id_actaudor") String idActuador,
			@QueryParam("value") String value) {
		Integer idActuadorInt = Integer.parseInt(idActuador);
		Integer valueInt = Integer.parseInt(value);
		this.arduino.escribir(idActuadorInt.intValue(), valueInt.intValue());
		return "Escritura del valor '" + value + "' en actuador '" + idActuador + "': OK";
	}
}
