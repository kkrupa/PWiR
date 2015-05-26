package edu.kk.pwir.rmi;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServerService {
	public static void main(String[] args) throws RemoteException, NamingException {
		System.out.println("Uruchamianie serwera testowego...");
		Magazyn magazynGlowny = new Magazyn();
		
		System.out.println("Wiązanie implementacji serwera do rejetru...");
		Context namingContext = new InitialContext();
		namingContext.bind("rmi:magazyn_glowny", magazynGlowny);
		
		System.out.println("Oczekiwanie na wywołania klientów...");
		
	}
}
