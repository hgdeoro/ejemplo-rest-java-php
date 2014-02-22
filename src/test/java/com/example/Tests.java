package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Type;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Tests extends BaseTest {

	@Test
	public void testLecturaSensor() {
		String responseMsg = target.path("/sensor/33/valor").request().get(String.class);

		Type type = new TypeToken<Map<String, String>>() {
		}.getType();

		Map<String, String> obj = new Gson().fromJson(responseMsg, type);

		// assertTrue(responseMsg.contains("Lectura de sensor '33' devolvio"));
		assertEquals(obj.get("idSensor"), "33");
		assertNotNull(obj.get("valor"));
		Integer.parseInt(obj.get("valor"));
	}

	@Test
	public void testEscrituraSensor() {
		String responseMsg = target.path("/actuador/12/valor").queryParam("value", "77").request()
				.post(null, String.class);
		assertTrue(responseMsg.contains("Escritura del valor '77' en actuador '12': OK"));
	}
}
