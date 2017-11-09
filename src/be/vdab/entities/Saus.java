package be.vdab.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Saus implements Serializable {
	private static final long serialVersionUID = 1L;
	private long nummer;
	private String naam;
	private List<String> ingrediënten = new ArrayList<>();
	
	public Saus() {
	}

	public Saus(long nummer, String naam, List<String> ingrediënten) {
		this.nummer = nummer;
		this.naam = naam;
		this.ingrediënten.addAll(ingrediënten);
	}

	public long getNummer() {
		return nummer;
	}

	public void setNummer(long nummer) {
		this.nummer = nummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public List<String> getIngrediënten() {
		return ingrediënten;
	}

	public void addIngrediënt(String ingrediënt) {
		ingrediënten.add(ingrediënt);
	}
}