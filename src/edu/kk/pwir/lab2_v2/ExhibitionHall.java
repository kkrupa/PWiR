package edu.kk.pwir.lab2_v2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExhibitionHall {

	private final BlockingQueue<Visitor> seats;
	private final EntranceGate entranceGate;
	private final ExitGate exitGate;

	public ExhibitionHall(int capacity) {
		super();
		seats = new ArrayBlockingQueue<Visitor>(capacity, true);
		entranceGate = new EntranceGate(seats);
		exitGate = new ExitGate(seats);
	}

	public synchronized void enter(Visitor visitor) throws InterruptedException {
		entranceGate.enter(visitor);
	}

	public synchronized void exit() throws InterruptedException {
		exitGate.exit();
	}
}
