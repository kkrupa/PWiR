package edu.kk.pwir.lab2_v2;

import java.util.Random;

public class Application {

	public static final int DELAY_ON_ENTER = 2000;
	public static final int DELAY_ON_EXIT = 2000;

	private static final int HALL_CAPACITY = 3;
	private static final int RANDOM_RANGE = 6;

	public static void main(String[] args) throws InterruptedException {

		final ExhibitionHall exhibition = new ExhibitionHall(HALL_CAPACITY);

		Thread entranceBodyguard = new Thread(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();
				while (true) {
					int visitors = random.nextInt(RANDOM_RANGE);
					System.out.println("Random new visitors = " + visitors);
					for (int i = 1; i <= visitors; i++) {
						try {
							exhibition.enter(new Visitor("Visitor: " + visitors + ".", i));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		entranceBodyguard.start();

		Thread exitBodyguard = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						exhibition.exit();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
		exitBodyguard.start();
	}
}
