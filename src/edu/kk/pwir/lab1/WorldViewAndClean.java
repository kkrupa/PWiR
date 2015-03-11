package edu.kk.pwir.lab1;

public class WorldViewAndClean implements Runnable {
	
	public void run() {
		while(true) {
		try {
			Thread.sleep(3000);
			World.getInteger();			
			World.cleanArray();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
}
