package assignment05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Assignment05 {

	public static void main(String[] args) {

		String text = "John.Davidson/05051988/Belgrade Michael.Barton/01011968/Krakov Ivan.Perkinson/23051986/Moscow";

		ArrayList<Person> listaOsoba = new ArrayList<>();

		String[] izvuceniPodaci = text.split("[ ./]+");

		for (int i = 0; i < izvuceniPodaci.length; i += 4) {

			String ime = izvuceniPodaci[i].split(" ")[0];
			String prezime = izvuceniPodaci[i + 1].split(" ")[0];
			String datum = izvuceniPodaci[i + 2].split(" ")[0];

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
			LocalDate datum_rodjenja = LocalDate.parse(datum, formatter);

			String mesto_rodjenja = izvuceniPodaci[i + 3].split(" ")[0];

			Person osoba = new Person(ime, prezime, datum_rodjenja, mesto_rodjenja);

			listaOsoba.add(osoba);

		}

		for (Person osoba : listaOsoba)
			System.out.println(osoba);

	}

}
