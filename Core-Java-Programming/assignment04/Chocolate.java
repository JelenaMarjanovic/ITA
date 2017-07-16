package assignment04;

public class Chocolate extends Product {

	private double tezina;

	public Chocolate(String naziv, String barKod, double osnovnaCena, double tezina) {
		// Prosledjivanje argumenata odgovarajucem parametrizovanom
		// konstruktoru u "parent" klasi
		super(naziv, barKod, osnovnaCena);
		this.tezina = tezina;
	}

	// Redefinisana toString metoda
	public String toString() {
		return "Proizvod: čokolada\n" + super.toString() + "Težina: " + tezina + "g\n";
	}

}