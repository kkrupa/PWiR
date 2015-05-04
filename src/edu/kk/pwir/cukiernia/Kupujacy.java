package edu.kk.pwir.cukiernia;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Kupujacy extends Konsument implements Runnable {

	// private int czasZakupu; // chyba juz niepotrzebne
	private long czasBezJedzenia; // czas jaki kupujacy wytrzyma bez zjedzenia
									// nastepnego paczka
	Random random = new Random();
	private long startTime; // moment w jakim kupujacy zjadl paczka
	private long timeLimit; // moment, do ktorego kupujacy wytrzyma bez paczka
	private long czasJedzenia; // czas spedzony na zjadaniu paczka
	private Cukiernia cukiernia;
	private String nazwa;
	private static int numer = 0;

	public Kupujacy(int czasJedzenia, int czasBezJedzenia, Cukiernia cukiernia) {
		// this.czasZakupu = random.nextInt((int) (0.4 * czasZakupu)) + (int)
		// 0.8
		// * czasZakupu;
		this.czasBezJedzenia = random.nextInt((int) (0.4 * czasBezJedzenia))
				+ (int) 0.8 * czasBezJedzenia;
		stan = stanNajedzenia.NAJEDZONY;
		startTime = System.currentTimeMillis();
		timeLimit = startTime + (this.czasBezJedzenia * 1000);
		this.cukiernia = cukiernia;
		this.czasJedzenia = czasJedzenia;
		nazwa = "Paczkojad " + ++numer;
	}

	public void resetTime() {
		startTime = System.currentTimeMillis();
		timeLimit = startTime + (this.czasBezJedzenia * 1000);

	}

	private long getPozostalyCzas() {
		return (timeLimit - startTime);
	}

	public synchronized stanNajedzenia getStanNajedzenia() {
		return stan;
	}

	public boolean isAlive() {
		if (stan == stanNajedzenia.NIE_ZYJE)
			return false;
		else
			return true;
	}

	public long getCzasJedzenia() {
		return czasJedzenia * 1000;
	}

	public synchronized String getNazwa() {
		return nazwa;
	}

	public synchronized void zjedzPaczka() {
		try {
			System.out.println(getNazwa()
					+ " bedzie jadl paczka. Stan najedzenia: "
					+ getStanNajedzenia());
			Thread.sleep(getCzasJedzenia());
			resetTime();
			System.out.println(getNazwa() + " zjadl paczka.");
			this.stan = stanNajedzenia.NAJEDZONY;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println(getNazwa() + " pojawil sie na horyzoncie");
		while (timeLimit >= startTime) {

			if (getPozostalyCzas() > 0.6 * (czasBezJedzenia*1000)) {
				this.stan = stanNajedzenia.NAJEDZONY;
			}

			if (getPozostalyCzas() <= 0.6 * (czasBezJedzenia*1000)) {
				this.stan = stanNajedzenia.W_KOLEJCE;
				if(!cukiernia.checkIfInQueue(this)) cukiernia.dodajDoKolejki(this);
			}
			
			try {
				TimeUnit.SECONDS.sleep(2);
				startTime = System.currentTimeMillis();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.stan = stanNajedzenia.NIE_ZYJE;
		System.out.println(getNazwa() + " zmarł z głodu.");
	}
}
