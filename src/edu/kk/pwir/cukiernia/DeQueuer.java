package edu.kk.pwir.cukiernia;

import java.util.concurrent.TimeUnit;

public class DeQueuer implements Runnable {

	private Cukiernia cukiernia;
	
	public DeQueuer(Cukiernia cukiernia) {
		this.cukiernia = cukiernia;
	}
	
	@Override
	public void run() {
		try {
			cukiernia.usunZKolejki();
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
