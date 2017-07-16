package assignment04;

public abstract class Product {

	private String naziv;
	private String barKod;
	private double osnovnaCena;

	private final double PDV = 1.2;

	// Parametrizovani konstruktor
	public Product(String naziv, String barKod, double osnovnaCena) {
		this.naziv = naziv;
		this.barKod = barKod;
		this.osnovnaCena = osnovnaCena;
	}

	// Racunanje cene sa PDV-om od 20%
	public double racunanjeCene() {
		return osnovnaCena * PDV;
	}

	// U ovom slucaju, nisu potrebni get*() i set*() metodi, kao ni copy-konstruktor

	/*
	 * // kopi-konstruktor
	 * public Product(final Product proizvod) {
	 * 		this(proizvod.naziv, proizvod.barKod, proizvod.osnovnaCena);
	 * }
	 *
	 * public String getNaziv() {
	 * 		return naziv;
	 * }
	 *
	 * public void setNaziv(String naziv) {
	 * 		this.naziv = naziv;
	 * }
	 *
	 * public String getBarKod() {
	 * 		return barKod;
	 * }
	 *
	 * public void setBarKod(String barKod) {
	 * 		this.barKod = barKod;
	 * }
	 *
	 * public double getOsnovnaCena() {
	 * 		return osnovnaCena;
	 * }
	 *
	 * public void setOsnovnaCena(double osnovnaCena) {
	 * 		this.osnovnaCena = osnovnaCena;
	 * }
	 */

	// String reprezentacija objekta
	public String toString() {
		return "Naziv: " + naziv + "\nBar-kod: " + barKod + "\nCena: " + racunanjeCene() + " RSD\n";
	}

}