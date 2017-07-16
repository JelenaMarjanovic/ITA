package assignment05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

	private String ime;
	private String prezime;
	private LocalDate datum_rodjenja;
	private String mesto_rodjenja;

	public Person(String ime, String prezime, LocalDate datum_rodjenja, String mesto_rodjenja) {
		this.ime = ime;
		this.prezime = prezime;
		this.datum_rodjenja = datum_rodjenja;
		this.mesto_rodjenja = mesto_rodjenja;
	}

	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy.");

		return "Ime i prezime: " + ime + " "+ prezime + "\nDatum rođenja: " + datum_rodjenja.format(formatter)
				+ "\nMesto rođenja: " + mesto_rodjenja + "\n";
	}

}
