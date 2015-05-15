package edu.kk.pwir.cukiernia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CukierniaTest {

	public static void main(String[] args) {

		long t = 50; // Czas jaki klient moze przezyc bez potrzeby zjedzenia
						// paczka w sekundach.
		long c = 1; // Czas zakupu paczka
		int i = 20; // Ilosc klientow
		int k = 10; // Dlugosc kolejek
		int d = 1; // Ilosc paczkow dostarczanych w kazdej sekundzie

		Options options = new Options();
		options.addOption("t", true,
				"Czas jaki klient moze przezyc bez potrzeby zjedzenia paczka w sekundach");
		options.addOption("c", true, "Czas zakupu paczka");
		options.addOption("i", true, "Ilosc klientow");
		options.addOption("k", true, "Dlugosc kolejek");
		options.addOption("d", true,
				"Ilosc paczkow dostarczanych w kazdej sekundzie");
		options.addOption("help", false, "Drukuje ten ekran");

		HelpFormatter formatter = new HelpFormatter();

		ExecutorService dostawca = Executors.newSingleThreadExecutor();
		ExecutorService deQueuer = Executors.newSingleThreadExecutor();

		try {
			CommandLineParser parser = new BasicParser();
			CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("t"))
				t = (long) Long.parseLong(cmd.getOptionValue("t"));
			if (cmd.hasOption("c"))
				c = (long) Long.parseLong(cmd.getOptionValue("c"));
			if (cmd.hasOption("i"))
				i = (int) Integer.parseInt(cmd.getOptionValue("i"));
			if (cmd.hasOption("k"))
				k = (int) Integer.parseInt(cmd.getOptionValue("k"));
			if (cmd.hasOption("d"))
				d = (int) Integer.parseInt(cmd.getOptionValue("d"));
			if (cmd.hasOption("help")) {
				formatter.printHelp("CukierniaTest", options);
				System.exit(0);
			} else {
				System.out
						.println("Aby otrzymac pomoc oraz instrukcje uzycia uzyj parametru -help. "
								+ "\nJesli nie podales innych argumentow program przyjmuje wartosci domyslne.");
			}

			System.out
					.println("Parametry startowe: \nCzas zycia bez zjedzenia paczka: "
							+ t + " sekund");
			System.out.println("Dlugosc kupowania paczka: " + c + " sekund");
			System.out.println("Ilość klientów: " + i);
			System.out.println("Dopuszczalna długość kolejek: " + k);
			System.out
					.println("Ilość paczków dostarczanych w każdej sekundzie: "
							+ d);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Cukiernia cukiernia = new Cukiernia(k);

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int j = 1; j <= i; j++) {
			executor.execute(new Kupujacy(c, t, cukiernia));
		}
		executor.shutdown();

		final int d1 = d;

		dostawca.execute(new Runnable() {
			public void run() {
				while (!executor.isTerminated()) {
					try {
						cukiernia.dostawaPaczkow(d1);
						System.out.println("Dostarczono " + d1 + " paczkow.");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		deQueuer.execute(new Runnable() {
			public void run() {
				while (!executor.isTerminated()) {
					try {
						cukiernia.usunZKolejki();
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		dostawca.shutdown();
		deQueuer.shutdown();
	}
}
