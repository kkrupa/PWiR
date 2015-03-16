package edu.kk.pwir.lab2_v3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExhibitionHall {

	private BlockingQueue<Visitor> queue;

	public ExhibitionHall(int seats) {
		super();
		queue = new ArrayBlockingQueue<Visitor>(seats);
	}

	public synchronized void visitorEnters(Visitor enteringVisitors) {
		try {
			queue.put(enteringVisitors);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void getVisitorsInside() {
		System.out.println("There are " + queue.size() + " visitors in the museum.");
		}
	
	public synchronized void visitorExits(Visitor exitingVisitor) {
		try {
			queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
