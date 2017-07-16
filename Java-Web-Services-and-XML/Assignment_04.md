<h1>Veb servisi</h1>

Potrebno je kreirati web servis za prevođenje između dva jezika po izboru. Na servisu, baza podataka reči je potrebno da bude smeštena u jednom XML fajlu, proizvoljne strukture. Kada klijent zahteva određenu reč na prevođenje, servis proverava postojanje reči u svojoj XML bazi. Ukoliko reč postoji, servis kao izlaz klijentu emituje prevedenu reč. Ukoliko reč ne postoji u XML fajlu, servis emituje adekvatnu poruku. Zbog testiranja u XML fajl je potebno dodati par reči.

Klijent koristi opisani servis tako što poziva metodu <code>translate</code>, sa tri string parametra. Prvi parametar predstavlja reč za prevod, drugi parametar je izvorni jezik, a treći je ciljni jezik. Poziv te metode na klijentu bi mogao da izgleda ovako:<br>
<code>translate("butterfly", "english", "russian");</code>
