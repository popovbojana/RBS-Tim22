OWASP Juice Shop

Bojana Popov SV70/2020

\*Broken Authentication: Password Strenght

- **Rešenje**: Izazov je rešen tako što je pronađen adminov email u recenzijama korisnika ([admin@juice-sh.op](mailto:admin@juice-sh.op)). Lozinka je jednostavna i predvidiva: admin123.
- **Klasa napada**: Broken Authentication – Ovo je klasa napada koja se odnosi na slabe ili nesigurne mehanizme autentifikacije u softverskoj aplikaciji. Ove ranjivosti omogućavaju napadačima da na neovlašćen način pristupe nalogu korisnika ili privilegovanim funkcijama unutar aplikacije.
- **Iskorišćavanje ranjivosti**: Eksploatacija ovih ranjivosti može omogućiti napadačima da preuzmu kontrolu nad korisničkim nalozima, uključujući administratorske naloge. To može dovesti do gubitka poverljivosti, integriteta i dostupnosti podataka, kao i do neovlašćenog pristupa osetljivim informacijama.
- **Ranjivosti**: Ranjivosti se javljaju kada se koriste slabi ili predvidivi podaci za autentifikaciju, poput jednostavnih lozinki, koje su lako pogodljive. Takođe, nedostatak kontrole nad pokušajima prijavljivanja može omogućiti brute-force napade.
- **Kontramere**:
  - Ojačavanje lozinki: Koristite zahteve za jake lozinke koje uključuju kombinaciju slova, brojeva i specijalnih znakova, i postavite minimalnu dužinu lozinke.
  - Ograničavanje pokušaja prijavljivanja: Implementirajte mehanizme za ograničavanje broja pokušaja prijavljivanja sa jedne IP adrese kako biste sprečili brute-force napade.
  - Dvofaktorska autentifikacija: Omogućite dvofaktorsku autentifikaciju kako biste povećali nivo sigurnosti korisničkih naloga.
  - Praćenje sumnjivih aktivnosti: Pratite aktivnosti prijavljivanja i brzo reagujte na sumnjive ili neovlašćene pokušaje pristupa.

Injection: Login Jim

- **Rešenje**: Izazov je rešen korišćenjem SQL injekcije u procesu prijavljivanja. Konkretno, u recenzijama korisnika je pronađen Jim-ov email ‘<jim@juice-sh.op>’. Nakon toga pri loginu u polje za email je upisan zajedno sa ‘— što je omogućilo da se ostatak SQL upita posle email-a ignoriše, odnosno da se prijavi Jim bez lozinke.
- **Klasa napada**: Injection – SQL injekcija je vrsta napada na web aplikacije u kojoj napadač može uneti SQL upit u ulazno polje koje aplikacije kasnije koristi u SQL upitu prema bazi podataka. Kada ulazni podaci nisu pravilo obrađeni, napadač može manipulisati SQL upitom i izvršiti operacije nad bazom podataka koje nisu dozvoljene.
- **Iskorišćavanje ranjivosti**: Iskorišćavanje SQL injekcije može imati ozbiljne posledice, uključujući neovlašćeni pristup podacima, promenu ili brisanje podataka, preuzimanje kontrole nad aplikacijom, i slično.
- **Ranjivosti**: Ranjivost koja je omogućila uspeh ovog napada je nedostatak validacije i filtriranja ulaznih podataka. Aplikacije ne proverava da li korisnički unosi sadrže maliciozne SQL upite, što omogućava napadaču da unese SQL upit i izmeni ponašanje aplikacije.
- **Kontramere**:
  - Validacija i filtriranje ulaznih podataka: Svi ulazni podaci moraju se validirati i filtrirati pre upotrebe u SQL upitu. To uključuje i uvođenje pravila za dozvoljene karaktere, dužinu unosa i format.
  - Korišćenje pripremljenih upita: Umesto sastavljanja SQL upita na osnovu korisničkih podataka, koristiti pripremljene upite (prepared statements) i parametrisane upite koji sprečavaju napadača da ubaci maliciozni SQL kod.
  - Escaping ulaznih podataka: U situacijama gde pripremljeni upiti nisu mogući, ulazni podaci treba da budu pravilno escapovani pre upotrebe u SQL upitu.
  - Korišćenje ORM-a: Korišćenje objektnog-relacionog mapiranja (ORM) može pomoći u automatskoj prevenciji SQL injekcija tako što obezbeđuje sigurnu interakciju između aplikacije i baze podataka.
  - Zapisivanje napada: Implementacija zapisivanja napada (logging) i detekcije anomalija može pomoći u identifikaciji i reagovanju na potencijalne napade.

Broken Access Control: View Basket

- **Rešenje**: Izazov je rešen tako što je pristupljeno session storage-u i tamo je pronađen podatak bid:7, za koji se pretpostavlja da predstavlja identifikator korpe. Promenom vrednosti bid-a na 1 je dobijen pristup korpi sa id-em 1, odnosno korpi drugog korisnika.
- **Klasa napada**: Broken Access Control – Napad podrazumeva iskorišćavanje grešaka u kontroli pristupa kako bi se pristupilo resursima ili informacijama kojima napadač ne bi trebao imati pristup.
- **Iskorišćavanje ranjivosti**: Iskorišćavanje ove ranjivosti omogućava napadaču da pristupi privatnim informacijama drugih korisnika, što može dovesti do gubitka poverljivosti i potencijalno do zloupotrebe podataka.
- **Ranjivosti**: Ranjivost leži u lošoj kontroli pristupa, gde aplikacija ne proverava dovoljno detaljno da li korisnik ima dozvolu za pristup korpi drugog korisnika. Greška je u tome što se podaci o korpi ne adekvatno povezuju sa korisničkim identitetom ili pristup nije pravilno ograničen.
- **Kontramere**:
  - Implementacija stroge kontrole pristupa: Koristiti kontrolne mehanizme za verifikaciju da korisnik ima pravo pristupa samo svojoj korpi za kupovinu.
  - Validacija sesije: Proveriti da li je korisnik koji pristupa korpi za kupovinu isti onaj koji je pokrenuo sesiju.
  - Zaštita podataka sesije: Ograničiti pristup podacima sesije, uključujući bid identifikator, kako bi se sprečila direktna manipulacija.
  - Zapisivanje i monitoring aktivnosti: Pratiti aktivnosti korisnika kako bi se brzo otkrila sumnjiva ponašanja i potencijalne zloupotrebe.

Miscellaneous: Security Policy

- **Rešenje**: Izazov je rešen tako što je pristupljeno url-u ’<http://localhost:3000/.well-known/security.txt‘> i pronađene informacije o kontaktu, enkripciji, jezicima i drugim relevantnim smernicama za bezbednost aplikacije.
- **Klasa napada**: Miscellaneous – Ova klasa napada uključuje različite bezbednosne izazove koji nisu striktno povezani s određenim tehničkim ranjivostima, već s pravilnim ponašanjem prema sigurnosnim smernicama aplikacije. U ovom slučaju, pristup sigurnosnoj politici aplikacije omogućava ponašanje nalik white hat hakerima, koji su poznati i kao etički hakeri. Oni rade sa dozvolom vlasnika sistema kako bi otkrili i popravili ranjivosti pre nego što ih zlonamerni hakeri mogu iskoristiti.
- **Iskorišćavanje ranjivosti**: Rešavanjem ovog izazova se upoznaje sa pravilima sigurnosti i etičkog istraživanja.
- **Ranjivosti**: Ovaj izazov ne podrazumeva specifične ranjivosti u softveru, već nudi platformu za pristup informacijama o sigurnosnoj politici, što je normalno i deo dobrih praksi za etičko hakovanje.
- **Kontramere**:
  - Osiguravanje transparentnosti: Omogućiti jasan i transparentan pristup informacijama o sigurnosnoj politici i kontaktima za etičke istraživače.
  - Ažuriranje security.txt: Fajl security.txt treba da sadrži ažurirane informacije o kontaktima i sigurnosnim smernicama.
  - Promovisanje etičkog ponašanja: Podsticati istraživače da se pridržavaju sigurnosnih smernica i postupaju etično.

Sensitive Data Exposure: GDPR Data Theft

- **Rešenje**: Izazov je rešen tako što je kreiran novi nalog i napravljena nova porudžbina sa tog naloga. Zatim se presretne http zahtev za tu porudžbinu i uoči se da je email ispisan sa \* na mestima samoglasnika. Zatim se napravi novi nalog sa istim email-om samo različitim samoglasnicima. Kada se uradi export data sa tog novog profila dobiju se i informacije sa prvog profila (zbog takvih email adresa).
- **Klasa napada**: Sensitive Data Exposure – Ova klasa napada se odnosi na izlaganje osetljivih podataka korisnika. Napadač može pristupiti ličnim podacima drugih korisnika, čime krši njihove privatne podatke, bez upotrebe injekcija.
- **Iskorišćavanje ranjivosti**: Iskorišćavanje ove ranjivosti može dovesti do otkrivanja osetljivih podataka o drugim korisnicima, što može prouzrokovati kršenje privatnosti, zloupotrebu podataka i potencijalne zakonske posledice.
- **Ranjivosti**: Ranjivost je dozvoljena zbog neadekvatne zaštite i obrade podataka o korisnicima, kao i slabe validacije prilikom izvoza profila korisnika.
- **Kontramere**:
  - Validacija i zaštita podataka: Osigurati da se lični podaci korisnika adekvatno zaštite i ne izlažu neopravdano.
  - Ograničenje pristupa: Ograničiti pristup izvozima podataka samo na korisnika koji traži svoje podatke.
  - Korisnička autentifikacija: Primeniti snažnu autentifikaciju korisnika kako bi se osigurala njihova autentičnost prilikom izvoza podataka.

Vulnerable Components: Forged Signed JWT

- **Rešenje**: Izazov je rešen tako što je nakon prijavljivanja na nalog pronađen JWT token u local storage-u. Taj token je dekodiran koristeći online alat kako bi se razumela struktura tokena. U header delu tokena, promenjena je vrednost za type na none i kodiran je izmenjeni header u Base64 formatu. Što se tiče payload dela, zamenjen je postojeći e-mail sa željenim email-om i takođe kodiran taj deo. Novi token je kreiran po formatu ‘header.payload.’ bez signature dela. U local storage-u je zamenjen stari sa novokreiranim tokenom.
- **Klasa napada**: Vulnerable Components – Ova klasa se odnosi na korišćenje zastarelih, nesigurnih ili ranjivih komponenti u softverskoj aplikaciji. To uključuje biblioteke, pakete, frameworke i druge resurse koji su sastavni delovi softvera. Kada softver koristi takve komponente, rizik od sigurnosnih propusta i napada se značajno povećava.
- **Iskorišćavanje ranjivosti**: Eksploatacija ranjivih komponenti može omogućiti napadačima da preuzmu kontrolu nad aplikacijom, pristupe osetljivim podacima, pokrenu štetne skripte ili uzrokuju prekide u radu sistema. To može rezultovati gubitkom poverljivosti, integriteta i dostupnosti podataka i usluga.
- **Ranjivosti**: Ranjivost se odnosi na nedostatak validacije i sigurnosti JWT tokena, kao i dopuštanje napadaču da manipuliše podacima unutar tokena. Takođe, nedostatak adekvatne zaštite privatnih RSA ključeva može doprineti uspešnosti napada.
- **Kontramere**:
  - Upotreba sigurnijih algoritama potpisivanja: Koristiti sigurnije algoritme potpisivanja tokena i osigurati privatne ključeve.
  - Validacija tokena: Verifikovati token na serveru pre nego što se dozvoli pristup korisniku.
