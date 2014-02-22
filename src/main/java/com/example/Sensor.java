package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	@Path("/leer")
	@Produces(MediaType.TEXT_PLAIN)
	public String leerSensor(@PathParam("id_sensor") String idSensor) {
		Integer idSensorInt = Integer.parseInt(idSensor);
		return "Lectura de sensor '" + idSensor + "' devolvio: " + this.arduino.leer(idSensorInt.intValue());
	}

	@POST
	@Path("/escribir")
	@Produces(MediaType.TEXT_PLAIN)
	public String escribirEnSensor(@PathParam("id_sensor") String idSensor, @QueryParam("value") String value) {
		Integer idSensorInt = Integer.parseInt(idSensor);
		Integer valueInt = Integer.parseInt(value);
		this.arduino.escribir(idSensorInt.intValue(), valueInt.intValue());
		return "Escritura del valor '" + value + "' en sensor sensor '" + idSensor + "': OK";
	}
}
