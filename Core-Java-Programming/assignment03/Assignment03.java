package assignment03;

import java.util.ArrayList;
import java.util.Arrays;

public class Assignment03 {
    
    public static void main(String[] args) {

		int[] array = {12, 23, -22, 0, 43, 545, -4, -55, 43, 12, 0, -999, -87};
		int br_pozitivnih = 0, br_negativnih = 0, br_duplikata;

		// Prebrojavanje pozitivnih i negativnih elemenata niza celih brojeva array
		// Uzeto je da nula nije ni pozitivan, ni negativan broj
		for(int i = 0; i < array.length; i++)
			if(array[i] > 0)
				br_pozitivnih++;
			else if(array[i] < 0)
				br_negativnih++;

		// Deklaracija nizova pozitivnih i negativnih elemenata
		int[] pozitivniElementi = new int[br_pozitivnih];
		int[] negativniElementi = new int[br_negativnih];

		// Popunjavanje nizova pozitivnih i negativnih elemenata
		for(int i = 0, p = 0, n = 0; i < array.length; i++)
		{
			if(array[i] > 0)
			{
				pozitivniElementi[p] = array[i];
				p++;
			}
			else if(array[i] < 0)
			{
				negativniElementi[n] = array[i];
				n++;
			}
		}

		// Pozivanje metode za prebrojavanje duplikata u nizu array
		br_duplikata = brojanjeDuplikata(array);

		// Ispis nizova pozitivnih i negativnih elemenata prvobitno datog niza
		System.out.println("Pozitivni elementi niza su: " + Arrays.toString(pozitivniElementi));
		System.out.println("Negativni elementi niza su: " + Arrays.toString(negativniElementi));

		// Ispis broja pronadjenih duplikata
		System.out.println("Broj duplikata je: " + br_duplikata);

	}

	// Metoda za prebrojavanje duplikata u datom nizu
	private static int brojanjeDuplikata(int[] niz)
	{
		int broj_duplikata = 0;

		// Deklaracija ArrayList za smestanje pronadjenih duplikata
		// radi provere tokom prebrojavanja, kako bi se izbeglo
		// ponovno brojanje istih elemenata tokom prolaska kroz niz
		ArrayList<Integer> listaDuplikata = new ArrayList<Integer>();

		// Kada "pokupimo" prvi poredbeni element niza
		for(int i = 0; i < niz.length; i++)
			// Prodjemo kroz dinamicku listu duplikata
			for(int k = 0; k <= listaDuplikata.size(); k++)
				// Ako se izdvojeni element ne nalazi na toj listi
				if(!listaDuplikata.contains(niz[i]))
					// Uzimamo drugi poredbeni element niza
					for(int j = i + 1; j < niz.length; j++)
						// Ukoliko su jednaki, uvecavamo brojac duplikata
						// i dodajemo taj element na listu duplikata
						if(niz[i] == niz[j])
						{
							broj_duplikata++;
							listaDuplikata.add(niz[i]);
						}

		// Na kraju, vracamo broj duplikata pronadjenih u nizu
		return broj_duplikata;

	}
    
}
