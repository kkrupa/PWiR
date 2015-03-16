package edu.kk.pwir.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

	private static List<Integer> intArray;
	private static Random random;
	
	public World () {
		intArray = new ArrayList<>();
		random = new Random();
	}
	
	public synchronized void setInteger() {
		int i = random.nextInt();
		intArray.add(i);
		System.out.println("Element dodany do tablicy przez watek " + Thread.currentThread().getName()  +   ": "  + i);
	}
	
	public synchronized void getInteger() {
		if (intArray.isEmpty()) System.out.println("Tablica jest pusta.");
		else System.out.println("Zawartosc tablicy: " + intArray);
	}
	
	public synchronized void cleanArray() {		
		if (!intArray.isEmpty()) {
			intArray.clear();
			System.out.println("Tablica wyczyszczona.");
		}
	}
	
}
