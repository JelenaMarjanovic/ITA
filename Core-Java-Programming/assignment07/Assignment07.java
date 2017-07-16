package assignment07;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Assignment07 {

	public enum Komande {
		LIST, INFO, CREATE_DIR, RENAME, COPY, MOVE, DELETE, EXIT, UNSPECIFIED
	}

	public static void main(String[] args) {

		Komande komande = Komande.UNSPECIFIED;

		System.out.println("Dobrodošli u File Manager!");
		System.out.println("--------------------------");
		System.out.println("Dostupne komande su:\nLIST       - pregled sadržaja foldera"
				+ "\nINFO       - informacije o fajlu/folderu" + "\nCREATE_DIR - kreiranje foldera"
				+ "\nRENAME     - promena imena fajla/foldera\nCOPY       - kopiranje fajlova/foldera"
				+ "\nMOVE       - premeštanje fajlova/foldera\nDELETE     - brisanje fajlova/foldera"
				+ "\nEXIT       - izlaz iz programa\n");

		boolean kraj = false;

		Scanner sc = new Scanner(System.in);

		while (kraj != true) {

			while (komande == Komande.UNSPECIFIED) {

				System.out.println("Unesite komandu:");

				String komanda = sc.next().toUpperCase();

				try {
					komande = Komande.valueOf(komanda);
				} catch (Exception e) {
					komande = Komande.UNSPECIFIED;
					System.out.println("Nije uneta dostupna komanda!");
					System.out.println();
				}
			}

			switch (komande) {

			case LIST:
				System.out.println("Izabrali ste pregled sadržaja foldera.");
				sc = new Scanner(System.in);

				System.out.println("Unesite putanju foldera:");
				File putanjaList = new File(sc.nextLine());

				List(putanjaList);

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case INFO:
				System.out.println("Izabrali ste pregled informacija o fajlu/folderu.");
				sc = new Scanner(System.in);

				System.out.println("Unesite putanju fajla/foldera:");
				File putanjaInfo = new File(sc.nextLine());

                                
				try {
                                    if (putanjaInfo.exists()) {
					Info(putanjaInfo);

					System.out.println("Da li želite detaljnije informacije o sadržaju "
							+ "unete putanje? (DA / NE)");

					if (sc.next().equals("DA"))
						InfoDetaljno(putanjaInfo);
					else {
						System.out.println();
						komande = Komande.UNSPECIFIED;
						break;
					}
                                    } else
                                        System.out.println("Izabrana putanja ne postoji!");

				} catch (IOException e) {
                                    System.out.println("Izabrana putanja ne postoji!");
				}

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case CREATE_DIR:
				System.out.println("Izabrali ste kreiranje foldera.");
				sc = new Scanner(System.in);

				System.out.println("Unesite putanju foldera u kome želite da kreirate novi folder:");
				File putanjaCr = new File(sc.nextLine());

				System.out.println("Unesite naziv foldera koji želite da kreirate:");
				String nazivCreate = sc.nextLine();

				File putanjaCreate = new File(putanjaCr + "\\" + nazivCreate);
				Create(putanjaCreate);

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case RENAME:
				System.out.println("Izabrali ste promenu imena fajla/foldera.");
				sc = new Scanner(System.in);

				System.out.println("Unesite putanju foldera u kome se nalazi fajl/folder kome "
						+ "želite da promenite naziv:");
				File putanjaR = new File(sc.nextLine());

				System.out.println("Unesite naziv fajla/foldera kome želite da promenite naziv:");
				String nazivRename = sc.nextLine();
				File stariFRename = new File(putanjaR + "\\" + nazivRename);

				if (!stariFRename.exists()) {
					System.out.println("Ne postoji fajl/folder sa tim nazivom!");
					System.out.println();
					komande = Komande.UNSPECIFIED;
					break;
				} else {
					System.out.println("Unesite novi naziv foldera:");
					String noviNaziv = sc.nextLine();

					if (nazivRename.equals(noviNaziv)) {
						System.out.println("Stari i novi naziv su isti.");
						System.out.println();
						komande = Komande.UNSPECIFIED;
						break;
					} else {
						File noviFRename = new File(putanjaR + "\\" + noviNaziv);
						Rename(stariFRename, noviFRename);
					}
				}

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case COPY:
				System.out.println("Izabrali ste kopiranje fajlova/foldera.");
				sc = new Scanner(System.in);

				try {

					System.out.println("Unesite putanju foldera u kome se nalazi fajl/folder koji "
							+ "želite da kopirate:");
					File staraPutanjaCopy = new File(sc.nextLine());

					System.out.println("Unesite naziv fajla/foldera koji želite da kopirate:");
					String nazivCopy = sc.nextLine();
					File stariFCopy = new File(staraPutanjaCopy + "\\" + nazivCopy);

					if (!stariFCopy.exists()) {
						System.out.println("Ne postoji fajl/folder sa tim nazivom!");
						komande = Komande.UNSPECIFIED;
						break;
					} else {
						System.out.println("Unesite putanju foldera u koji želite da prekopirate fajl/folder:");
						File novaPutanjaCopy = new File(sc.nextLine());
						File noviFCopy = new File(novaPutanjaCopy + "\\" + nazivCopy);

						Copy(stariFCopy, noviFCopy);
						System.out.println("Fajl/folder \"" + nazivCopy + "\" je kopiran iz " + staraPutanjaCopy + " u "
								+ novaPutanjaCopy);
					}

				} catch (IOException e) {
					System.out.println("Izabrana putanja ne postoji!");
				}

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case MOVE:
				System.out.println("Izabrali ste premeštanje fajlova/foldera.");
				sc = new Scanner(System.in);

				try {

					System.out.println("Unesite putanju foldera u kome se nalazi fajl/folder koji "
							+ "želite da premestite:");
					File staraPutanjaMove = new File(sc.nextLine());

					System.out.println("Unesite naziv fajla/foldera koji želite da premestite:");
					String nazivMove = sc.nextLine();
					File stariFMove = new File(staraPutanjaMove + "\\" + nazivMove);

					if (!stariFMove.exists()) {
						System.out.println("Ne postoji fajl/folder sa tim nazivom!");
						komande = Komande.UNSPECIFIED;
						break;
					} else {

						System.out.println("Unesite putanju foldera u koji želite da premestite fajl/folder:");
						File novaPutanjaMove = new File(sc.nextLine());
						File noviFMove = new File(novaPutanjaMove + "\\" + nazivMove);

						Copy(stariFMove, noviFMove);
						Delete(stariFMove);

						System.out.println("Fajl/folder \"" + nazivMove +  "\" je premešten iz " + staraPutanjaMove + " u "
								+ novaPutanjaMove);
					}

				} catch (IOException e) {
					System.out.println("Izabrana putanja ne postoji!");
				}

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case DELETE:
				System.out.println("Izabrali ste brisanje fajlova/foldera.");
				sc = new Scanner(System.in);

				try {
                                    
                                    System.out.println("Unesite putanju fajla/foldera koji želite da obrišete:");
                                    File putanjaDelete = new File(sc.nextLine());
                                    
                                    if(!putanjaDelete.exists()) {
                                        System.out.println("Navedeni fajl/folder ne postoji!");
                                    } else {

					Delete(putanjaDelete);
                                        
					System.out.println("Fajl/folder je uspešno obrisan.");
                                    }

				} catch(Exception e) {
					System.out.println("Brisanje fajla/foldera nije izvršeno!");
				}

				System.out.println();
				komande = Komande.UNSPECIFIED;
				break;

			case EXIT:
				System.out.println("Izabrali ste izlazak iz programa.");

				kraj = true;

                                System.out.println("-------------------------------------");
				System.out.println("Hvala što ste koristili File Manager!");
				break;

			default:
				break;
			}

		}

		sc.close();
	}

	// Pregled foldera
	private static void List(File f) {

		String spisak = new String();

		if (f.exists() && f.isDirectory()) {

			File[] elementi = f.listFiles();

			for (File element : elementi)
				spisak += element.getName() + "\n";

			System.out.println(spisak);

		}

		else if (f.exists() && f.isFile())
			System.out.println("Izabrana putanja predstavlja fajl.");

		else
			System.out.println("Izabrana putanja ne postoji!");

	}

	// Prikaz informacija o fajlu/folderu u tabelarnom obliku
	private static void Info(File f) throws IOException {

			System.out.println("-------------------------------------------------------");

			DateTimeFormatter formatiranjeDatuma = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

			System.out.println("Ime                    | " + f.getName());
			System.out.println("Putanja                | " + f.getAbsolutePath());

			if (f.length() < 1024)
				System.out.println("Veličina               | " + f.length() + " bytes");
			else if (f.length() < 1048576)
				System.out.println("Veličina               | " + f.length() / 1024 + " kB");
			else
				System.out.println("Veličina               | " + f.length() / Math.pow(1024, 2) + " MB");

			BasicFileAttributes bfa = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
			FileTime datum = bfa.creationTime();
			Instant instantKreiranje = datum.toInstant();
			LocalDateTime datumKreiranja = LocalDateTime.ofInstant(instantKreiranje, ZoneId.systemDefault());

			System.out.println("Datum kreiranja        | " + datumKreiranja.format(formatiranjeDatuma));

			Instant instantIzmena = Instant.ofEpochMilli(f.lastModified());
			LocalDateTime datumIzmene = LocalDateTime.ofInstant(instantIzmena, ZoneId.systemDefault());

			System.out.println("Datum poslednje izmene | " + datumIzmene.format(formatiranjeDatuma));
			System.out.println("-------------------------------------------------------");

	}

	// Prikaz informacija kompletnog sadrzaja foldera u tabelarnom obliku
	private static void InfoDetaljno(File f) throws IOException {

		if (f.isDirectory()) {

			File[] elementi = f.listFiles();

			Info(f);

			System.out.println("-------------------------------------------------------");

			for (File element : elementi) {

				DateTimeFormatter formatiranjeDatuma = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

				System.out.println("Ime                    | " + element.getName());
				System.out.println("Putanja                | " + element.getAbsolutePath());

				if (element.length() < 1024)
					System.out.println("Veličina               | " + element.length() + " bytes");
				else if (element.length() < 1048576)
					System.out.println("Veličina               | " + element.length() / 1024 + " kB");
				else
					System.out.println("Veličina               | " + element.length() / Math.pow(1024, 2) + " MB");

				BasicFileAttributes bfa = Files.readAttributes(element.toPath(), BasicFileAttributes.class);
				FileTime datum = bfa.creationTime();
				Instant instantKreiranje = datum.toInstant();
				LocalDateTime datumKreiranja = LocalDateTime.ofInstant(instantKreiranje, ZoneId.systemDefault());

				System.out.println("Datum kreiranja        | " + datumKreiranja.format(formatiranjeDatuma));

				Instant instantIzmena = Instant.ofEpochMilli(element.lastModified());
				LocalDateTime datumIzmene = LocalDateTime.ofInstant(instantIzmena, ZoneId.systemDefault());

				System.out.println("Datum poslednje izmene | " + datumIzmene.format(formatiranjeDatuma));
				System.out.println("-------------------------------------------------------");
			}
		} else if (f.isFile())
                    Info(f);
	}

	// Kreiranje foldera
	private static void Create(File noviF) {

		String naziv = noviF.getName();

		try {
			if (!noviF.exists()) {

				if (naziv.contains("\\") || naziv.contains("/") || naziv.contains(":") || naziv.contains("*")
						|| naziv.contains("?") || naziv.contains("<") || naziv.contains(">") || naziv.contains("|")
						|| naziv.contains("\"")) {
					System.out.println("Naziv foldera ne može da sardži karaktere \\ / : * ? \" < > | ");
				} else {
					noviF.mkdir();
					System.out.println("Kreiran je folder pod nazivom " + naziv);
				}

			} else
				System.out.println("Folder sa nazivom " + naziv + " već postoji.");

		} catch (Exception e) {
			System.out.println("Nije moguće kreirati navedeni folder!");
		}

	}

	// Promena imena fajlova/foldera
	private static void Rename(File stariF, File noviF) {

		String noviNaziv = noviF.getName();

		if (noviF.exists()) {
			System.out.println("Na unetoj putanji već postoji fajl/folder sa tim nazivom!");
			return;
		}

		if (noviNaziv.contains("\\") || noviNaziv.contains("/") || noviNaziv.contains(":") || noviNaziv.contains("*")
				|| noviNaziv.contains("?") || noviNaziv.contains("<") || noviNaziv.contains(">")
				|| noviNaziv.contains("|") || noviNaziv.contains("\"")) {
			System.out.println("Naziv fajla/foldera ne može da sardži karaktere \\ / : * ? \" < > | ");
		} else {

			if (stariF.renameTo(noviF))
				System.out.println("Naziv je uspešno izmenjen.");
			else
				System.out.println("Promena naziva nije izvršena!");
		}

	}

	// Kopiranje fajlova/kompletnih foldera
	private static void Copy(File staraPutanja, File novaPutanja) throws IOException {
		if (staraPutanja.isDirectory()) {

			if (!novaPutanja.exists())
				novaPutanja.mkdir();

			String elementi[] = staraPutanja.list();

                    for (String element : elementi) {
                        Copy(new File(staraPutanja, element), new File(novaPutanja, element));
                    }

		} else {
                    OutputStream os;
                    try (InputStream is = new FileInputStream(staraPutanja)) {
                        os = new FileOutputStream(novaPutanja);
                        byte bs[] = new byte[1024];
                        int l;
                        while ((l = is.read(bs)) > 0)
                            os.write(bs, 0, l);
                    }
			os.close();
		}
	}

	// Premestanje fajlova/kompletnih foldera je izvedeno unutar switch/case, kao kombinacija Copy i Delete metoda

	// Brisanje fajlova/foldera
	private static void Delete(File f) {
		File[] elementi = f.listFiles();

		if (elementi != null)
			for (File element : elementi)
				Delete(element);

		f.delete();
	}

}
