package edu.kk.pwir.lab2;

import java.util.concurrent.BlockingQueue;

public class EntranceGate {

	private BlockingQueue<Visitor> queue;

	EntranceGate(BlockingQueue<Visitor> queue) {
		super();
		this.queue = queue;
	}

	public void enter(Visitor visitor) throws InterruptedException {
		synchronized (queue) {
			this.queue.put(visitor);
			System.out.println("Entering -> " + visitor.getName() + ". Occupied seats -> " + this.queue.size());
			Thread.sleep(ExhibitionHall.DELAY_ON_ENTER);
		}
	}
}