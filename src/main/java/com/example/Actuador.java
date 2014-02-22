package com.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
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
	@Path("/valor")
	@Produces(MediaType.TEXT_PLAIN)
	public String escribirEnActuador(@PathParam("id_actaudor") String idActuador,
			@QueryParam("value") String value) {
		Integer idActuadorInt = Integer.parseInt(idActuador);
		Integer valueInt = Integer.parseInt(value);
		this.arduino.cambiarActuador(idActuadorInt.intValue(), valueInt.intValue());
		return "Escritura del valor '" + value + "' en actuador '" + idActuador + "': OK";
	}

	@GET
	@Path("/valor")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject obtenerValorDeActuador(@PathParam("id_actaudor") String idActuador) {
		Integer idActuadorInt = Integer.parseInt(idActuador);
		Integer valor = this.arduino.leerActuador(idActuadorInt.intValue());

		return Json.createObjectBuilder().add("idActuador", idActuadorInt).add("valor", valor).build();
	}

}
