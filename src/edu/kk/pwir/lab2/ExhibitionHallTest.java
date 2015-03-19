package edu.kk.pwir.lab2;

public class ExhibitionHallTest {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Jako argumenty podaj kolejno ilość miejsc "
					+ "w muzeum oraz ilość gości.");
			System.exit(0);
		}

		int iloscMiejsc = Integer.parseInt(args[0]);
		int iloscGosci = Integer.parseInt(args[0]);
		ExhibitionHall museum = new ExhibitionHall(iloscMiejsc, iloscGosci);
		Thread entrance = new Thread(new EntranceGate(museum));
		Thread entrance2 = new Thread(new EntranceGate(museum));
		Thread exit = new Thread(new ExitGate(museum));

		entrance.start();
		entrance2.start();
		exit.start();

	}

}
