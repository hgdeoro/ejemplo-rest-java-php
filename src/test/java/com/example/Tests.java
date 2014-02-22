package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Tests extends BaseTest {

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
