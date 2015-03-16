package edu.kk.pwir.lab1;

public class WorldViewAndClean implements Runnable {
	
	private World world;

	public WorldViewAndClean(World world) {
		this.world = world;
	}
	
	public void run() {
		while(true) {
		try {
			Thread.sleep(3000);
			world.getInteger();			
			world.cleanArray();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
}
