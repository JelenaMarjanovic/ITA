package assignment04;

public class Wine extends Product {

	private double zapremina;

	private final double TAKSA = 1.1;

	public Wine(String naziv, String barKod, double osnovnaCena, double zapremina) {
		super(naziv, barKod, osnovnaCena);
		this.zapremina = zapremina;
	}

	// Redefinisana metoda za racunanje cene, sa dodatnom taksom od 10%
	public double racunanjeCene() {
		return super.racunanjeCene() * TAKSA;
	}

	// Redefinisana toString metoda
	public String toString() {
		return "Proizvod: vino\n" + super.toString() + "Zapremina: " + zapremina + "l\n";
	}

}