package com.example;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Arduino {

	private final Lock LOCK = new ReentrantLock();

	/**
	 * Sleep, en milisegundos.
	 */
	private final long defaultSleep;

	/**
	 * Mantiene ultimo estado escrito en actuador.
	 */
	private HashMap<Integer, Integer> estadoActuadores = new HashMap<Integer, Integer>();

	public Arduino(long defaultSleep) {
		this.defaultSleep = defaultSleep;
	}

	public Arduino() {
		this.defaultSleep = 500;
	}

	/**
	 * Devuelve temperatura.
	 * 
	 * @param pin
	 * @return
	 */
	public int leerTemperatura(int pin) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			// Devolvemos valor entre -10 y 40
			return new Random().nextInt(50) - 10;
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Devuelve valor de pin digital (0 o 1)
	 * 
	 * @param pin
	 * @return
	 */
	public int leerPinDigital(int pin) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			// Devolvemos valor entre 0 y 1
			return new Random().nextInt(2);
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Devuelve valor de pin analogico (0 o 1023)
	 * 
	 * @param pin
	 * @return
	 */
	public int leerPinAnalogico(int pin) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			// Devolvemos valor entre 0 y 1023
			return new Random().nextInt(1024);
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Cambia el valor de un actuador.
	 * 
	 * @param pin
	 * @param valor
	 */
	public void cambiarActuador(int pin, int valor) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			estadoActuadores.put(pin, valor);
			return;
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Obtiene ultimo valor escrito en actuador.
	 * 
	 * Devuelve null si no hay datos para dicho actuador.
	 * 
	 * @param pin
	 * @param valor
	 */
	public Integer leerActuador(int pin) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			return estadoActuadores.get(pin);
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Simula que se ha realizado una operación con Android.
	 * 
	 * Basicamente, espera. Pero nos sirve para testear la sicronización de las
	 * llamadas.
	 * 
	 */
	private void simularOperacionConAndroid() {
		try {
			Thread.sleep(this.defaultSleep);
		} catch (InterruptedException e) {
		}
	}

}
