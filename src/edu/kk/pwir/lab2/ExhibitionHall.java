package edu.kk.pwir.lab2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExhibitionHall {

	public static final int DELAY_ON_ENTER = 1000;
	public static final int DELAY_ON_EXIT = 2000;

	private static final int NUMBER_OF_VISITORS = 6;
	private static final int HALL_CAPACITY = 3;

	public static void main(String[] args) throws InterruptedException {

		final BlockingQueue<Visitor> exhibitionHall = new ArrayBlockingQueue<Visitor>(HALL_CAPACITY, true);
		final EntranceGate entranceGate = new EntranceGate(exhibitionHall);
		final ExitGate exitGate = new ExitGate(exhibitionHall);

		startEntering(entranceGate);
		startExiting(exitGate);
	}

	private static void startEntering(final EntranceGate entranceGate) {
		Thread entranceManager = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < NUMBER_OF_VISITORS; i++) {
					try {
						entranceGate.enter(new Visitor("Visitor ", i));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		entranceManager.start();
	}

	private static void startExiting(final ExitGate exitGate) {
		Thread exitManager = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						exitGate.exit();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
		exitManager.start();
	}
}
