package edu.kk.pwir.lab2;

import java.util.concurrent.BlockingQueue;

public class ExitGate {

	private BlockingQueue<Visitor> queue;

	ExitGate(BlockingQueue<Visitor> queue) {
		super();
		this.queue = queue;
	}

	public void exit() throws InterruptedException {
		synchronized (queue) {
			Visitor visitor = this.queue.poll();
			if (visitor != null) {
				System.out.println("Exiting -> " + visitor.getName() + ". Occupied seats -> " + this.queue.size());
				Thread.sleep(ExhibitionHall.DELAY_ON_EXIT);
			}
		}
	}
}
