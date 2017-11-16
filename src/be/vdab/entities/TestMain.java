package be.vdab.entities;

import java.util.Scanner;

import be.vdab.repositories.SausRepository;

public class TestMain {

	public static void main(String[] args) {
		SausRadenSpel sausRadenSpel = new SausRadenSpel(new SausRepository().findAllSausNaam());
		
		System.out.println(sausRadenSpel.getOutput());
		System.out.println(sausRadenSpel.getSausNaam());
		
		try(Scanner scanner = new Scanner(System.in)){
			while (sausRadenSpel.getAantalVerkeerd() < 10 && !sausRadenSpel.getOutput().equals(sausRadenSpel.getSausNaam())) {
				System.out.println("Geef het volgend karakter in, aantal verkeerd: " + sausRadenSpel.getAantalVerkeerd());
				sausRadenSpel.setOutput(scanner.next());
				System.out.println(sausRadenSpel.getOutput());
			}
		}
	}

}
