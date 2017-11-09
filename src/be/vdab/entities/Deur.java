package be.vdab.entities;

import java.io.Serializable;

public class Deur implements Serializable {
	
	// membervariabelen
	
	private static final long serialVersionUID = 1L;
	private boolean open;
	private boolean metFriet;
	
	public Deur() {
	}
	
	public Deur(boolean metFriet) {
		this.open = false;
		this.metFriet = metFriet;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean isMetFriet() {
		return metFriet;
	}
	
	public void setMetFriet(boolean metFriet) {
		this.metFriet = metFriet;
	}	
}
