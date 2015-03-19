package edu.kk.pwir.lab2;

import java.util.Random;

public class ExitGate implements Runnable {

	private ExhibitionHall museum;
	Random random;

	public ExitGate(ExhibitionHall museum) {
		this.museum = museum;
		random = new Random();
	}

	public void run() {
		try {
			while(museum.isVisitorsRemaining()) {
				Thread.sleep(random.nextInt(400));
				museum.visitorExiting();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
