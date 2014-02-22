package com.example;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tests {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());

		target = c.target(Main.BASE_URI);
	}

	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testLecturaSensor() {
		String responseMsg = target.path("/sensor/33/leer").request().get(String.class);
		assertTrue(responseMsg.contains("Lectura de sensor '33' devolvio"));
	}

	@Test
	public void testEscrituraSensor() {
		String responseMsg = target.path("/actuador/12/escribir").queryParam("value", "77").request()
				.post(null, String.class);
		assertTrue(responseMsg.contains("Escritura del valor '77' en actuador '12': OK"));
	}
}
