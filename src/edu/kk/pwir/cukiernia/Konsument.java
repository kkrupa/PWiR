package edu.kk.pwir.cukiernia;

public abstract class Konsument {
	public enum stanNajedzenia {
		NAJEDZONY, W_KOLEJCE, NIE_ZYJE
	};

	public stanNajedzenia stan;
}
