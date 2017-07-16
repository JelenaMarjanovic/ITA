<h1>Parsiranje teksta i kreiranje kolekcije objekata</h1>

Dat je sledeći string koji predstavlja zapis pročitan iz nekog skladišta:<br>
<code>String text = 'John.Davidson/05051988/Belgrade Michael.Barton/01011968/Krakov Ivan.Perkinson/23051986/Moscow'</code>.

Potrebno je napraviti logiku koja će iz ovog zapisa izvući pojedinačne podatke:
<ul>
      <li>ime,
      <li>prezime,
      <li>datum rođenja,
      <li>mesto rođenja.
</ul>

Pročitane podatke potrebno je iskoristiti za popunjavanje polja objekta tipa <em>Person</em>; stoga je potrebno kreirati klasu <strong>Person</strong>, kao i adekvatna polja u okviru nje.

Nakon kreiranja objekta i popunjavanja polja, objekat tipa <em>Person</em> smestiti u kolekciju i na kraju izvršiti prolazak kroz kolekciju i ispis podataka o osobama.

Polje za datum rođenja je potrebno da bude tipa <code>LocalDate</code>. Prilikom ispisa podataka o osobama, na kraju, poželjno bi bilo formatirati ispis, tako da se datum prikazuje u obliku: 05. maj 1988.
