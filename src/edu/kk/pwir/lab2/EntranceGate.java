package edu.kk.pwir.lab2;

import java.util.Random;

public class EntranceGate implements Runnable {

	private ExhibitionHall museum;
	Random random;

	public EntranceGate(ExhibitionHall museum) {
		this.museum = museum;
		random = new Random();
	}

	public void run() {
		try {
			while(museum.isVisitorsWaiting()) {
				Thread.sleep(random.nextInt(200));
				museum.visitorEntering();
			
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
