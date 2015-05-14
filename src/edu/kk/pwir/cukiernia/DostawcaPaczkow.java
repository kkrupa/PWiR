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
			ilosc = random.nextInt(10) + 5;
			cukiernia.dostawaPaczkow(ilosc);
			System.out.println("Dostarczono " + ilosc + " paczkow.");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
