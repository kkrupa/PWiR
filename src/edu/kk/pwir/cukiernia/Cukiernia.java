package edu.kk.pwir.cukiernia;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cukiernia {

	private BlockingQueue<Kupujacy> okienko1;
	private BlockingQueue<Kupujacy> okienko2;
	private int paczki;

	public Cukiernia() {
		okienko1 = new LinkedBlockingQueue<Kupujacy>();
		okienko2 = new LinkedBlockingQueue<Kupujacy>();
		paczki = 0;
	}

	public synchronized void dostawaPaczkow(int iloscPaczkow) {
		paczki = paczki + iloscPaczkow;
	}

	public synchronized void dodajDoKolejki(Kupujacy kupujacy) {
		try {
			if (okienko1.size() > okienko2.size()) {
				okienko2.put(kupujacy);
				System.out.println(Thread.currentThread().getName() + " stanal w kolejce 2. Stan najedzenia: " + kupujacy.getStanNajedzenia());
			} else {
				okienko1.put(kupujacy);
				System.out.println(Thread.currentThread().getName() + " stanal w kolejce 1. Stan najedzenia: " + kupujacy.getStanNajedzenia());
			}
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void usunZKolejki() {
		if (!okienko1.isEmpty()) {
			if (okienko1.peek().isAlive()) {
				okienko1.peek().zjedzPaczka();
				paczki--;			
			}
			okienko1.poll();
		}

		if (!okienko2.isEmpty()) {
			if (okienko2.peek().isAlive()) {
				okienko2.peek().zjedzPaczka();
				paczki--;
			}
			okienko2.poll();
		}
	}

}
