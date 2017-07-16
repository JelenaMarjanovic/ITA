<h1>Rukovanje fajl sistemom</h1>

Potrebno je napraviti aplikaciju koja će da obavlja funkcije file manager-a. Od funkcionalnosti, aplikacija treba da obezbedi sledeće funkcije:
<ul>
  <li>pregled foldera,
  <li>prikaz informacija o fajlovima/folderima u tabelarnom obliku:
  <ul>
    <li>ime,
    <li>putanja,
    <li>veličina,
    <li>datum kreiranja,
    <li>datum poslednje izmene,
  </ul>
  <li>kreiranje foldera,
  <li>promena imena fajlova/foldera,
  <li>kopiranje fajlova/kompletnih foldera,
  <li>premeštanje fajlova/kompletnih foldera,
  <li>brisanje fajlova/foldera.
</ul>

Preporuka je da se za ove operacije koriste komande, redom: LIST, INFO, CREATE_DIR, RENAME, COPY, MOVE, DELETE.

Nakon što se u konzoli unese određena komanda, aplikacija prikazuje poruku o prepoznatoj komandi (ili neprepoznatoj, ako je komanda neadekvatna) i nastavlja sa prikupljanjem potrebnih informacija za obavljanje navedene operacije (putanje i slično).

Potrebno je pokrivanje svih mogućih scenarija kako aplikacija ne bi pukla.
