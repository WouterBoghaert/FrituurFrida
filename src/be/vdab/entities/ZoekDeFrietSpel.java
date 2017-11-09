package be.vdab.entities;

import java.io.Serializable;
import java.util.Random;

public class ZoekDeFrietSpel implements Serializable {
	
	// membervariabelen
	
	private static final long serialVersionUID = 1L;
	private static final int AANTAL_DEUREN = 7;
	private final Deur [] deuren = new Deur [AANTAL_DEUREN];
	
	// constructor
	
	public ZoekDeFrietSpel() {
		int metFriet = new Random().nextInt(AANTAL_DEUREN);
		for (int i = 0; i<AANTAL_DEUREN;i++) {
			deuren[i] = new Deur(i==metFriet);
		}
	}
	
	public Deur[] getDeuren() {
		return deuren;
	}
}
