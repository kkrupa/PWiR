package edu.kk.pwir.cukiernia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CukierniaTest {

	public static void main(String[] args) {
		Cukiernia cukiernia = new Cukiernia();
		Thread dostawca = new Thread(new DostawcaPaczkow(cukiernia));
		dostawca.start();

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 1; i <= 10; i++) {
			try {
				executorService.execute(new Kupujacy(2, 50, cukiernia));
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executorService.shutdown();

		ExecutorService deQueuer = Executors.newSingleThreadExecutor();
		deQueuer.execute(new Runnable() {
			public void run() {
				while (!executorService.isTerminated()) {
					try {
						cukiernia.usunZKolejki();
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		deQueuer.shutdown();
	}
}
