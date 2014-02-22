package com.example;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentAccessTest {

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

	private void log(String msg) {
		System.out.println("[" + Thread.currentThread().getName() + "] " + msg);

	}

	@Test
	public void testAccesoConcurrente() throws InterruptedException {
		List<Thread> threads = new ArrayList<Thread>();
		final AtomicInteger threadsListos = new AtomicInteger();
		final Semaphore semaphore = new Semaphore(0);

		for (int i = 0; i < 30; i++) {
			threads.add(new Thread(new Runnable() {

				@Override
				public void run() {
					log("Iniciando thread...");
					final int sensorId = new Random().nextInt();

					try {
						log("Esperando semaforo...");
						threadsListos.incrementAndGet();
						semaphore.acquire();
						log("Semaforo adquirido.");
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}

					log("Enviando request...");
					String responseMsg = target.path("/sensor/" + sensorId + "/leer").request()
							.get(String.class);
					log("Respuesta recibida");
					assertTrue(responseMsg.contains("Lectura de sensor '" + sensorId + "' devolvio"));

				}
			}));
		}

		log("start() de threads...");
		for (Thread t : threads)
			t.start();

		log("Esperando que threads esten listos...");
		while (threadsListos.get() != 30)
			Thread.sleep(50);

		log("Listo! Liberando semaforo...");
		semaphore.release(100);

		OUTER: while (true) {
			for (Thread t : threads)
				if (t.isAlive()) {
					// Al menos hay 1 thread vivo... hacemos
					// continue de outer asi este for reinicia
					Thread.sleep(500);
					continue OUTER;
				}
			// No se hico 'continue' => todos los threads han finalizado
			break;
		}

		log("Fin!");
	}
}
