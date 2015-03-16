package edu.kk.pwir.lab1;

public class WorldAdder implements Runnable {
	
	private World world;

	public WorldAdder(World world) {
		this.world = world;
	}
		
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				world.setInteger();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
