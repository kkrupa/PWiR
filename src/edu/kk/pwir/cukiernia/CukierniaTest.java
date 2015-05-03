package edu.kk.pwir.cukiernia;

public class CukierniaTest {

	public static void main(String[] args) {
		Cukiernia cukiernia = new Cukiernia();
		Thread dostawca = new Thread(new DostawcaPaczkow(cukiernia));
		Thread deQueuer = new Thread(new DeQueuer(cukiernia));
		Thread k1 = new Thread(new Kupujacy(2, 30, cukiernia));
		Thread k2 = new Thread(new Kupujacy(2, 30, cukiernia));
		Thread k3 = new Thread(new Kupujacy(2, 30, cukiernia));
		Thread k4 = new Thread(new Kupujacy(2, 30, cukiernia));
		Thread k5 = new Thread(new Kupujacy(2, 30, cukiernia));
		
		dostawca.start();
		k1.start();
		k2.start();
		k3.start();
		k4.start();
		k5.start();
		
		deQueuer.start();
	}

}
