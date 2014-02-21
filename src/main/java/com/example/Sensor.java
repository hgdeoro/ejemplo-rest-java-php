package com.example;

import java.util.Random;

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

	@GET
	@Path("/leer")
	@Produces(MediaType.TEXT_PLAIN)
	public String leerSensor(@PathParam("id_sensor") String idSensor) {
		return "Valor para sensor '" + idSensor + "': " + new Random().nextInt();
	}

}
