<h1>Kreiranje tajmer aplikacije</h1>

Potrebno je napraviti Java aplikaciju sa Swing grafičkim korisničkim okruženjem. Aplikacija treba da se pokreće u system tray-u operativnog sistema. Klikom na ikonicu u system tray-u, pojavljuje se pop-up menu sa dve opcije, a to su <em>Settings</em> i <em>Close</em>. Klikom na <em>Close</em>, aplikacija se zatvara. Klikom na <em>Settings</em>, otvara se prvi prozor, koji treba da sadrži sve kontrole prikazane na <strong>slici</strong> (primer je samo ilustrativan i vaša aplikacija ne mora izgledati ovako).

Nakon definisanog vremena ili u definisano vreme, potrebno je da se pojavi drugi prozor, čija će pozadina menjati boju, pri čemu će boja pozadine i brzina promene zavisiti od podešavanja u prvom prozoru.

Drugi prozor treba da se prikaže nakon isteka tajmerom definisanog vremena čekanja. U prvom slučaju (<em>on time</em>) - u tačno podešeno vreme, ili u drugom slučaju (<em>countdown</em>) - nakon izabranog broja minuta. Naravno, jedna opcija isključuje drugu.

Taster <em>Choose color</em> prikazuje dijalog sa paletom za odabir boje, pri čemu će, nakon odabira, boja biti prikazana u labeli pored. Dovoljno je boju prikazati u RGB formatu ili obojiti labelu. Može se koristiti jedna boja po izboru ili više različitih boja. Bitno je samo da se izabrana boja smenjuje sa belom ili nekom drugom bojom kako bi se postigao efekat "blinkanja".

<em>Slider</em> kontrola služi za odabir brzine promene boje u drugom prozoru. Za minimum se može uzeti 100 milisekundi, a za maksimum 3 sekunde.

Taster <em>Start</em> služi za pokretanje tajmera, pri čemu sve kontrole u prvom prozoru postaju neaktivne, sem tastera <em>Stop</em>. Prvi prozor treba da nestane ukoliko se odabere taster <em>Close (x)</em>. Aplikaciju je jedino moguće zatvoriti iz pop-up menija tray ikonice.

Kada je tajmer aktivan (dok traje čekanje na pojavu drugog prozora ili tokom njegovog prikaza), sve kontrole prvog prozora, osim <em>Stop</em> tastera su onemogućene.

Pritiskom na taster <em>Stop</em>, tajmer se isključuje, drugi prozor nestaje i kontrole u prvom prozoru opet postaju dostupne.
