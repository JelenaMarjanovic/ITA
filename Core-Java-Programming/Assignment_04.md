<h1>Objektno orijentisano programiranje u Javi</h1>

Zamislite da je potrebno da modelujete informacioni sistem jedne trgovine. Potrebno je da napravite klasu <strong>Product</strong> koja će predstavljati osnovu za dalje nasleđivanje i neće se moći instancirati. Ovu klasu nasleđuju dve klase koje predstavljaju grupe proizvoda: <strong>Chocolate</strong> i <strong>Wine</strong>.

Svaki proizvod poseduje određene osobine:
<ul>
  <li>naziv proizvoda,
  <li>bar-kod,
  <li>osnovnu cenu,
  <li>porez.
</ul>

Takođe, svaki proizvod poseduje i metodu za računanje cene, koja se izračunava kada se osnovna cena i uveća za iznos poreza. Porez (PDV) za svaki proizvod je 20% i ovo je podatak koji je konstantan i neće se menjati.

Ipak, proizvodi iz grupe vina, imaju i dodatni porez, pošto spadaju u grupu alkoholnih pića i on iznosi 10% od cene već uvećane za iznos PDV-a. I ovo je podatak koji je konstantan i neće se menjati.

Zbog ovoga je potrebno redefinisati metodu za računanje cene u okviru klase <em>Wine</em>.

Pored ovoga, klasa <em>Wine</em> treba da poseduje atribut koji definiše zapreminu boce, a klasa <em>Chocolate</em> atribut koji definiše težinu.

U klasama <em>Chocolate</em> i <em>Wine</em>, potrebno je napraviti parametrizovane konstruktore za kreiranje objekata.

Potrebno je, takođe, u klasama redefinisati metodu <code>toString</code> za prikaz informacija o objektu.

Na kraju je potrebno kreirati po jedan objekat ovih klasa i na izlazu prikazati informacije o proizvodima, kao i iznos finalne cene definisane kroz metodu za izračunavanje cene. Za osnovnu cenu uzeti proizvoljan iznos.
