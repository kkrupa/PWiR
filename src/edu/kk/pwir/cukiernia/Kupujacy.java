package edu.kk.pwir.cukiernia;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Kupujacy extends Konsument implements Runnable {

	// private int czasZakupu; // chyba juz niepotrzebne
	private final long czasBezJedzenia; // czas jaki kupujacy wytrzyma bez
										// zjedzenia
	// nastepnego paczka
	Random random = new Random();
	private long startTime; // moment w jakim kupujacy zjadl paczka
	private long timeLimit; // moment, do ktorego kupujacy wytrzyma bez paczka
	private final long czasJedzenia; // czas spedzony na zjadaniu paczka
	private final Cukiernia cukiernia;
	private final String nazwa;
	private static int numer = 0;

	public Kupujacy(long czasJedzenia, long czasBezJedzenia, Cukiernia cukiernia) {
		this.czasBezJedzenia = ((int) (0.8 * czasBezJedzenia))
				+ random.nextInt((int) (0.4 * czasBezJedzenia));
		stan = stanNajedzenia.NAJEDZONY;
		startTime = System.currentTimeMillis();
		timeLimit = startTime + (this.czasBezJedzenia * 1000);
		this.cukiernia = cukiernia;
		this.czasJedzenia = czasJedzenia;
		nazwa = "Paczkojad " + ++numer;
		System.out.println("Dopuszczalna dlugosc zycia " + getNazwa()
				+ " wynosi: " + this.czasBezJedzenia);
	}

	private synchronized long getPozostalyCzas() {
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
			startTime = System.currentTimeMillis();
			timeLimit = startTime + (this.czasBezJedzenia * 1000);
			this.stan = stanNajedzenia.NAJEDZONY;
			System.out.println(getNazwa() + " zjadl paczka.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println(getNazwa() + " pojawil sie na horyzoncie");
		while (timeLimit >= startTime) {

			try {
				if (getPozostalyCzas() <= 0.6 * (czasBezJedzenia * 1000)) {
					this.stan = stanNajedzenia.W_KOLEJCE;
					if (!(cukiernia.checkIfInQueue(this)))
						cukiernia.dodajDoKolejki(this);
				}
				TimeUnit.SECONDS.sleep(2);
				startTime = System.currentTimeMillis();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.stan = stanNajedzenia.NIE_ZYJE;
		System.err.println(getNazwa() + " zmarł z głodu.");
	}
}
