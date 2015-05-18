package edu.kk.pwir.rmi;

public class Produkt {
	private static int id = 0;
	private String nazwa;
	private String marka;
	private float cena;
	
	public Produkt(String nazwa, String marka, float cena) {
		this.nazwa = nazwa;
		this.marka = marka;
		this.cena = cena;
		++id;
	}
}
