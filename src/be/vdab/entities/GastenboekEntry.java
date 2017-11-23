package be.vdab.entities;

import java.time.LocalDateTime;

public class GastenboekEntry {

	private long id;
	private LocalDateTime datum;
	private String naam;
	private String bericht;
	
	public GastenboekEntry(long id, LocalDateTime datum, String naam, String bericht) {
		this.id = id;
		this.datum = datum;
		this.naam = naam;
		this.bericht = bericht;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBericht() {
		return bericht;
	}

	public void setBericht(String bericht) {
		this.bericht = bericht;
	}	
}
