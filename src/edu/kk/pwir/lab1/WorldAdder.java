package edu.kk.pwir.lab1;

public class WorldAdder implements Runnable {
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				World.setInteger();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
