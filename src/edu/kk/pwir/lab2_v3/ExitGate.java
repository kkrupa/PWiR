package edu.kk.pwir.lab2_v3;

import java.util.concurrent.BlockingQueue;

public class ExitGate {

	private BlockingQueue<Visitor> seats;

	ExitGate(BlockingQueue<Visitor> seats) {
		super();
		this.seats = seats;
	}

	public void exit() throws InterruptedException {
		Visitor visitor = this.seats.poll();
		if (visitor != null) {
			System.out.println("Exiting -> " + visitor.getName() + ". Occupied seats -> " + this.seats.size());
			Thread.sleep(Application.DELAY_ON_EXIT);
		}
	}
}
