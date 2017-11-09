package be.vdab.repositories;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ZoekDeFrietRepositoryVerkeerd implements Serializable {
	// membervariabelen
	
	private static final long serialVersionUID = 1L;
	private final Map<Long, String> zoekDeFrietRepository = new HashMap<>();
	
	// constructor
	
	public ZoekDeFrietRepositoryVerkeerd() {
		long friet = (long)(Math.random() * 7 + 1);
		for(long i=1;i<=7;i++) {
			if(i==friet) {
				zoekDeFrietRepository.put(i, "gevonden");
			}
			else {
				zoekDeFrietRepository.put(i, "deuropen");
			}
		}
	}
	
	public Map<Long,String> findAll(){
		return zoekDeFrietRepository;
	}
	
	public String findByVolgnummer(long volgnummer) {
		return zoekDeFrietRepository.get(volgnummer);
	}
}
