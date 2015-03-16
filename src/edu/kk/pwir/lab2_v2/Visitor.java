package edu.kk.pwir.lab2_v2;

public class Visitor {

	private String name;
	private int index;

	Visitor(String name, int index) {
		super();
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name + index;
	}
}
