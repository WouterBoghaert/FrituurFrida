package be.vdab.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.vdab.repositories.SausRepository;

public class SausRadenSpel implements Serializable {
	
	// membervariabelen
	
	private static final long serialVersionUID = 1L;
	private String output;
	private final String sausNaam;
	private final SausRepository sausRepository = new SausRepository();
	private int aantalVerkeerd;
	
	// constructor
	
	public SausRadenSpel () {
		int randomInt = new Random().nextInt((sausRepository.findAll().size()-1));
		sausNaam = sausRepository.findAll().get(randomInt).getNaam();
		setOutput();
		aantalVerkeerd = 0;
	}
	
	// setters
	
	private void setOutput() {
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<sausNaam.length(); i++) {
			builder.append('.');
		}
		output = builder.toString();
	}
	
	public void setOutput(String letter) {
		if (sausNaam.contains(letter)) {
			List <Integer> indices = new ArrayList<>();
			int huidigeIndex = 0;
								
			do {
				huidigeIndex = sausNaam.indexOf(letter, huidigeIndex);
				if (huidigeIndex != -1) {
					indices.add(huidigeIndex);
				}
				++huidigeIndex;
			}		
			while (huidigeIndex != 0);
			
			StringBuilder builder =  new StringBuilder(output);
			indices.stream().forEach(index -> builder.setCharAt(index, letter.charAt(0)));
			output = builder.toString();
		}
		else {
			++aantalVerkeerd;
		}
	}
	
	// getters
	
	public String getOutput() {
		return output;
	}
	
	public int getAantalVerkeerd() {
		return aantalVerkeerd;
	}
	
	public String getSausNaam() {
		return sausNaam;
	}	
}
