package edu.kk.pwir.rmi;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

public class Klient {

	public static void main(String[] args) throws NamingException,
			RemoteException {
		Context namingContext = new InitialContext();

		System.out.println("Wiązania rejetru RMI: ");
		Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
		while (e.hasMoreElements())
			System.out.println(e.nextElement().getName());
		
		String url = "rmi://localhost/magazyn_glowny";
		Magazyn magazynGlowny = (Magazyn) namingContext.lookup(url);
		
		System.out.println(magazynGlowny.getProductsList());

	}

}
