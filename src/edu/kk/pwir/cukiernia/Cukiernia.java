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
		System.out.println("W magazynie jest " + paczki + " paczkow.");
	}

	public synchronized void dodajDoKolejki(Kupujacy kupujacy) {
		try {
			if (okienko1.size() > okienko2.size()) {
				okienko2.put(kupujacy);
				System.out.println(kupujacy.getNazwa()
						+ " stanal w kolejce 2. Stan najedzenia: "
						+ kupujacy.getStanNajedzenia());
			} else {
				okienko1.put(kupujacy);
				System.out.println(kupujacy.getNazwa()
						+ " stanal w kolejce 1. Stan najedzenia: "
						+ kupujacy.getStanNajedzenia());
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean checkIfInQueue(Kupujacy kupujacy) {
		if (okienko1.contains(kupujacy) || okienko2.contains(kupujacy))
			return true;
		else
			return false;
	}

	public synchronized void usunZKolejki() {
		if (!okienko1.isEmpty() && paczki > 0) {
			if (okienko1.peek().isAlive()) {
				okienko1.peek().zjedzPaczka();
				paczki--;
				System.out.println(okienko1.peek().getNazwa()
						+ " opuscil kolejke 1.");
				okienko1.poll();
				System.out.println();
			} else {
				System.out.println(okienko1.peek().getNazwa()
						+ " opuscil kolejke 1 na noszach.");
				okienko1.poll();
			}
		}

		if (!okienko2.isEmpty() && paczki > 0) {
			if (okienko2.peek().isAlive()) {
				okienko2.peek().zjedzPaczka();
				paczki--;
				System.out.println(okienko2.peek().getNazwa()
						+ " opuscil kolejke 2.");
				okienko2.poll();
				System.out.println();
			} else {
				System.out.println(okienko2.peek().getNazwa()
						+ " opuscil kolejke 2 na noszach.");
				okienko2.poll();
			}
		}
		
		if(paczki<=0) System.out.println("Brak paczkow.");
	}

}
