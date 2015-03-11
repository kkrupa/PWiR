package edu.kk.pwir.lab1;

public class WorldTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		World world = new World();
		Thread add = new Thread(new WorldAdder());
		Thread add2 = new Thread(new WorldAdder());
		Thread add3 = new Thread(new WorldAdder());
		Thread viewAndClean = new Thread(new WorldViewAndClean());
		add.start();
		add2.start();
		add3.start();
		viewAndClean.start();
	}

}
