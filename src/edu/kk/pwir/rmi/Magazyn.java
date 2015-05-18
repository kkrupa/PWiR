package edu.kk.pwir.rmi;

import java.util.HashSet;
import java.util.Set;

public class Magazyn {
	private Set<Produkt> magazyn;
	
	public Magazyn() {
		magazyn = new HashSet<Produkt>();
	}
	
	public synchronized void dodajProdukt(Produkt produkt) {
		
	}
	
	public synchronized Produkt[] wyszukajProduktyNazwa(String lancuch) {
		magazyn.
	}
}
