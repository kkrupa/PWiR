package edu.kk.pwir.cukiernia;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Kupujacy extends Konsument implements Runnable {

//	private int czasZakupu; // chyba juz niepotrzebne
	private long czasBezJedzenia;  // czas jaki kupujacy wytrzyma bez zjedzenia nastepnego paczka
	Random random = new Random();
	private long startTime; // moment w jakim kupujacy zjadl paczka
	private long timeLimit; // moment, do ktorego kupujacy wytrzyma bez paczka
	private long czasJedzenia; // czas spedzony na zjadaniu paczka
	private Cukiernia cukiernia;

	public Kupujacy(int czasZakupu, int czasBezJedzenia, Cukiernia cukiernia) {
//		this.czasZakupu = random.nextInt((int) (0.4 * czasZakupu)) + (int) 0.8
//				* czasZakupu;
		this.czasBezJedzenia = random.nextInt((int) (0.4 * czasBezJedzenia))
				+ (int) 0.8 * czasBezJedzenia;
		stan = stanNajedzenia.NAJEDZONY;
		// pozostalyCzas = this.czasBezJedzenia;
		startTime = System.nanoTime() / 1000000;
		timeLimit = System.nanoTime() / 1000000 + this.czasBezJedzenia;
		this.cukiernia = cukiernia;
	}

	public void resetTime() {
		startTime = System.nanoTime() / 1000000;
		timeLimit = System.nanoTime() / 1000000 + this.czasBezJedzenia;
	}

	private int getPozostalyCzas() {
		return (int) (timeLimit - startTime);
	}

	public stanNajedzenia getStanNajedzenia() {
		return stan;
	}
	
	public boolean isAlive() {
		if(stan == stanNajedzenia.NIE_ZYJE) return false;
		else return true;
	}
	
	public long getCzasJedzenia() {
		return czasJedzenia;
	}
	
	public void zjedzPaczka() {
		try {
			System.out.println(Thread.currentThread().getName() + " bedzie jadl paczka. Stan najedzenia: " + getStanNajedzenia());
			Thread.sleep(getCzasJedzenia());
			stan = stanNajedzenia.NAJEDZONY;
			resetTime();
			System.out.println(Thread.currentThread().getName() + " zjadl paczka.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Paczkojad " + Thread.currentThread().getName() + " pojawil sie na horyzoncie");
		while (timeLimit >= startTime) {

			if (getPozostalyCzas() >= 0.5 * czasBezJedzenia) {
				stan = stanNajedzenia.NAJEDZONY;
			}

			if (getPozostalyCzas() < 0.5 * czasBezJedzenia) {
				cukiernia.dodajDoKolejki(this);
				stan = stanNajedzenia.W_KOLEJCE;
			}
			
			try {
				TimeUnit.SECONDS.sleep(2);
				startTime = System.nanoTime() / 1000000;  // TODO: POPRAWIC !!!
				System.out.println(startTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stan = stanNajedzenia.NIE_ZYJE;
		System.out.println("Kupujący " + Thread.currentThread().getName()
				+ " zmarł z głodu.");
	}

}
