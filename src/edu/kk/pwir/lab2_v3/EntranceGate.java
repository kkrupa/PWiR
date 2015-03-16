package edu.kk.pwir.lab2_v3;

import java.util.concurrent.BlockingQueue;

public class EntranceGate implements Runnable {

	private BlockingQueue<Visitor> seats;

	EntranceGate(BlockingQueue<Visitor> seats) {
		super();
		this.seats = seats;
	}

//	public void enter(Visitor visitor) throws InterruptedException {
//		this.seats.put(visitor);
//		System.out.println("Entering -> " + visitor.getName() + ". Occupied seats -> " + this.seats.size());
//		Thread.sleep(Application.DELAY_ON_ENTER);
//	}
	
	public void run() {
		seats.put();
		
	}
}