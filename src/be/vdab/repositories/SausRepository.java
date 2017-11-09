package be.vdab.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import be.vdab.entities.Saus;

public class SausRepository implements Serializable {
	private final static long serialVersionUID = 1L;
	private final static Map<Long,Saus> SAUZEN = new ConcurrentHashMap<>();
	static {
		SAUZEN.put(1L, (new Saus(1L, "cocktail", Arrays.asList("ketchup","mayonaise","whisky"))));
		SAUZEN.put(2L, (new Saus(2L,"mayonaise", Arrays.asList("ei","mosterd","citroen","olijfolie"))));
		SAUZEN.put(3L, (new Saus(3L,"mosterd", Arrays.asList("mosterdzaad","olie"))));
		SAUZEN.put(4L, (new Saus(4L,"tartare", Arrays.asList("ei","mosterd","olijfolie","basilicum"))));
		SAUZEN.put(5L, (new Saus(5L,"vinaigrette", Arrays.asList("mosterd","olijfolie","azijn","basilicum"))));		
	}
	
	public List<Saus> findAll(){
		return new ArrayList<>(SAUZEN.values());
	}
	
	public List<Saus> findByIngredi�nt(String ingredi�nt){
		return SAUZEN.values().stream()
				.filter(saus -> saus.getIngredi�nten().stream().anyMatch(ingred -> ingred.equalsIgnoreCase(ingredi�nt)))
				.collect(Collectors.toList());
	}
	
	public void removeById(long id) {
		SAUZEN.remove(id);
	}
	
	public Saus findById(long id) {
		return SAUZEN.get(id);
	}
}
