package edu.kk.pwir.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Warehouse extends Remote {
	
	List<Produkt> getProductsList() throws RemoteException;

	List<Produkt> searchForProducts(String string) throws RemoteException;

	void addProduct(Produkt product) throws RemoteException;
}
