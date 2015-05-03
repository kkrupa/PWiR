package edu.kk.pwir.cukiernia;

import java.util.Random;

public class DostawcaPaczkow implements Runnable {
	Random random;
	private Cukiernia cukiernia;
	private int ilosc;

	public DostawcaPaczkow(Cukiernia cukiernia) {
		random = new Random();
		this.cukiernia = cukiernia;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10000);
			ilosc = random.nextInt(20);
			cukiernia.dostawaPaczkow(ilosc);
			System.out.println("Dostarczono " + ilosc + " paczkow.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
