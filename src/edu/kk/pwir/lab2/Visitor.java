package edu.kk.pwir.lab2;

public class Visitor {

	private String name;
	private int index;

	Visitor(String name, int index) {
		super();
		this.name = name;
		this.index = index + 1;
	}

	public String getName() {
		return name + index;
	}
}
