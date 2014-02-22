package com.example;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Arduino {

	private final Lock LOCK = new ReentrantLock();

	private final long defaultSleep;

	public Arduino(long defaultSleep) {
		this.defaultSleep = defaultSleep;
	}

	public int leer(int pin) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			return new Random().nextInt();
		} finally {
			LOCK.unlock();
		}
	}

	public void escribir(int pin, int valor) {
		try {
			LOCK.lock();
			this.simularOperacionConAndroid();
			return;
		} finally {
			LOCK.unlock();
		}
	}

	private void simularOperacionConAndroid() {
		try {
			Thread.sleep(this.defaultSleep);
		} catch (InterruptedException e) {
		}
	}

}
