package com.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Representa un sensor
 */
@Path("/sensor/{id_sensor}/")
public class Sensor {

	private final Arduino arduino;

	public Sensor(Arduino arduino) {
		this.arduino = arduino;
	}

	@GET
	@Path("/valor")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject leerSensor(@PathParam("id_sensor") String idSensor) {
		Integer idSensorInt = Integer.parseInt(idSensor);
		int temperatura = this.arduino.leerTemperatura(idSensorInt.intValue());
		return Json.createObjectBuilder().add("idSensor", idSensorInt).add("valor", temperatura).build();
	}

}
