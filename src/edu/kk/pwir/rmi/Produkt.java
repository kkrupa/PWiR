package edu.kk.pwir.rmi;

public class Produkt {
	private static int id = 0;
	private String nazwa;
	private String marka;
	private double cena;
	
	public Produkt(String nazwa, String marka, double cena) {
		this.nazwa = nazwa;
		this.marka = marka;
		this.cena = cena;
		++id;
	}

	public static int getId() {
		return id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getMarka() {
		return marka;
	}

	public double getCena() {
		return cena;
	}
}
