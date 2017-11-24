package be.vdab.entities;

import java.time.LocalDateTime;

public class GastenboekEntry {

	private long id;
	private LocalDateTime datum;
	private String naam;
	private String bericht;
	
	public GastenboekEntry(long id, LocalDateTime datum, String naam, String bericht) {
		if (isIdValid(id)){
			this.id = id;
		}
		this.datum = datum;
		if(isNaamValid(naam)) {
			this.naam = naam;
		}
		if(isBerichtValid(bericht)) {
			this.bericht = bericht;
		}
	}

	public static boolean isNaamValid(String naam) {
		return naam != null && !naam.trim().isEmpty();
	}
	
	public static boolean isBerichtValid(String bericht) {
		return bericht != null && !bericht.trim().isEmpty();
	}
	
	public static boolean isIdValid(long id) {
		return id > 0;
	}
	
	public long getId() {
		return id;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public String getNaam() {
		return naam;
	}

	public String getBericht() {
		return bericht;
	}
}
