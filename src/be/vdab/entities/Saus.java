package be.vdab.entities;

public class Saus {
	private long nummer;
	private String naam;
	private String[] ingrediënten;
	
	public Saus() {
	}

	public Saus(long nummer, String naam, String[] ingrediënten) {
		this.nummer = nummer;
		this.naam = naam;
		this.ingrediënten = ingrediënten;
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

	public String[] getIngrediënten() {
		return ingrediënten;
	}

	public void setIngrediënten(String[] ingrediënten) {
		this.ingrediënten = ingrediënten;
	}
}