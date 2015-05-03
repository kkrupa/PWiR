package edu.kk.pwir.lab2;

public class ExhibitionHall {

	private int liczbaWolnychMiejsc;
	private int iloscGosci;
	private int iloscGosciInside;

	public ExhibitionHall(int miejsca, int goscie) {
		liczbaWolnychMiejsc = miejsca;
		iloscGosci = goscie;
		iloscGosciInside = 0;
	}
	
	public synchronized boolean isVisitorsWaiting() {
		return (iloscGosci>0);
	}
	
	public synchronized boolean isVisitorsRemaining() {
		if (iloscGosci>0 || iloscGosciInside>0) return true;
		else return false;
	}

	public synchronized void visitorEntering() {
		if (liczbaWolnychMiejsc > 0 && iloscGosci > 0) {
			liczbaWolnychMiejsc--;
			iloscGosci--;
			iloscGosciInside++;
//			System.out.println("Watek: " + Thread.currentThread().getName()
//					+ "Gosc wszedl do muzeum" + "\n");
			System.out.println("GoscieInside: " + iloscGosciInside
					+ " ; Miejsca wolne: " + liczbaWolnychMiejsc + " ; Pozostali: "
					+ iloscGosci);
		}

	}

	public synchronized void visitorExiting() {
		if (iloscGosciInside > 0) {
			liczbaWolnychMiejsc++;
			iloscGosciInside--;
//			System.out.println("Watek: " + Thread.currentThread().getName()
//					+ "Gosc wyszedl z muzeum");
			System.out.println("GoscieInside: " + iloscGosciInside
					+ " ; Miejsca wolne: " + liczbaWolnychMiejsc + " ; Pozostali: "
					+ iloscGosci);
		}
	}

}
