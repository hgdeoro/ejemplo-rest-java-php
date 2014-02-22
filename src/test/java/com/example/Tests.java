package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

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

		assertEquals(obj.get("idSensor"), "33");
		assertNotNull(obj.get("valor"));
		Integer.parseInt(obj.get("valor"));
	}

	@Test
	public void testEscrituraActuador() {

		final Type type = new TypeToken<Map<String, String>>() {
		}.getType();

		Builder builder = target.path("/actuador/12/valor").request();
		builder.accept(MediaType.APPLICATION_JSON);

		// json_encode(array('valor' => 44))
		Map<String, String> src = new HashMap<String, String>();
		src.put("valor", "123");
		String jsonValue = new Gson().toJson(src, type);

		String responseMsg = builder.post(Entity.json(jsonValue), String.class);

		Map<String, String> obj = new Gson().fromJson(responseMsg, type);

		assertEquals(obj.get("idActuador"), "12");
		assertNotNull(obj.get("valor"));
		assertEquals(123, Integer.parseInt(obj.get("valor")));

	}
}
