package edu.kk.pwir.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Magazyn extends UnicastRemoteObject implements Warehouse {
	private List<Produkt> magazyn;
	private List<Produkt> wynik;

	public Magazyn() throws RemoteException {
		magazyn = new ArrayList<Produkt>();
		wynik = new ArrayList<Produkt>();
		magazyn.add(new Produkt("Samochodzik", "Melex", 1000.5));
		magazyn.add(new Produkt("Rower", "Pelikan", 300));
		magazyn.add(new Produkt("Przerzutki", "Shimano", 150.5));
		magazyn.add(new Produkt("Pasta do zebow", "Colgate", 11.2));
		magazyn.add(new Produkt("Buty", "Vans", 200));
		magazyn.add(new Produkt("Ksiazka", "Helion", 150));
	}

	public List<Produkt> getProductsList() throws RemoteException {
		return magazyn;
	}

	public synchronized void addProduct(Produkt produkt) throws RemoteException {
		magazyn.add(produkt);
	}

	public synchronized List<Produkt> searchForProducts(String lancuch)
			throws RemoteException {
		wynik = null;
		for (Produkt p : magazyn) {
			if ((p.getNazwa() != null && p.getNazwa().toLowerCase()
					.contains(lancuch.toLowerCase()))
					|| (p.getMarka() != null && p.getMarka().toLowerCase()
							.contains(lancuch.toLowerCase())))
				wynik.add(p);
		}
		return wynik;
	}

}
