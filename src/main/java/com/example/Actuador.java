package com.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonObject escribirEnActuador(@PathParam("id_actaudor") String idActuador, JsonObject body) {
		try {
			Integer idActuadorInt = Integer.parseInt(idActuador);
			// Integer valueInt =
			// Integer.parseInt(body.get("valor").toString());
			Integer valueInt = body.getInt("valor");
			this.arduino.cambiarActuador(idActuadorInt.intValue(), valueInt.intValue());
			return Json.createObjectBuilder().add("idActuador", idActuadorInt).add("valor", valueInt).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
