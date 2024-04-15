Obavezan zadatak: Eastet egg

Kratak postupak: U "About us" stranici sam kliknuo na linkovani teksts "Check out our boring boring terms of use if you are interested in such lame stuff". Nakon toga umesto http://localhost:3000/ftp/legal.md sam promenio u http://localhost:3000/ftp , pokusao sam da skinem "eastere.gg", medjutim nije dozvoljeno s obzirom da ne ispunjava format fajla koji sme da se skine, ali promenuom u http://localhost:3000/easter.gg%2500.md fajl se moze skinuti gde se nalazi string koji treba da se dekodira pomocu Base64 dekodera, a nakon toga dekodirati pomocu ROT-13 dekodera, cime se dobija string koji dodajemo na http://localhost:3000 koji nas vodi na stranicu eastegga (planeta neka).

Klasa napada - "Broken Access Control" (Slomljena kontrola pristupa): Ova klasa napada obuhvata situacije u kojima softver ne sprovodi efikasnu kontrolu pristupa, omogućavajući korisnicima da pristupe resursima ili izvrše akcije koje nisu namenjene njima.
Uticaj iskorištenja ranjivosti: Iskorištenje ove ranjivosti može imati ozbiljne posledice, uključujući:

    • Neovlašćeni pristup osetljivim informacijama ili funkcijama sistema.
    • Modifikaciju ili brisanje podataka koji nisu namenjeni korisniku.
    • Povećanje privilegija korisnika, omogućavajući im da izvrše akcije koje nisu deo njihovih ovlašćenja.

Ranjivosti u softveru koje su dozvolile napad: Ove ranjivosti mogu biti rezultat nedostatka adekvatnih mehanizama za autentifikaciju, autorizaciju i kontrolu pristupa. Neadekvatno implementirane sesije, nedostatak provere ovlašćenja pri izvršavanju akcija ili neispravno konfigurisane postavke pristupa mogu omogućiti napadačima da iskoriste ovu ranjivost.
Primerene kontramere:

    • Adekvatna autentifikacija i autorizacija: Softver bi trebao koristiti pouzdane mehanizme autentifikacije kako bi identifikovao korisnike, kao i autorizaciju kako bi odredio koje akcije su dozvoljene za svakog korisnika.
    • Princip najmanjih privilegija: Korisnicima bi trebalo dodeliti samo neophodne privilegije koje su im potrebne za obavljanje njihovih zadataka.
    • Redovno ažuriranje i nadogradnja: Softver bi trebao biti redovno ažuriran kako bi se popravile poznate ranjivosti i otklonile potencijalne rupe u kontrolama pristupa.
    • Bezbednosno testiranje: Redovno sprovoditi testiranje bezbednosti kako bi se otkrile eventualne ranjivosti u kontroli pristupa i osiguralo da su implementirane odgovarajuće mere zaštite.


Zadatak 2: Admin Registration

Kratak postupak: Kada zelim da se login-ujem unosom" ' or 1=1--" i bilo koje sifre se mogu ulogovati kao admin, zato sto je on prvi kreirani korisnik.
Klasa napada - "Improper Input Validation" (Nedovoljna validacija unosa): Ova klasa napada se odnosi na situacije kada softver ne sprovodi dovoljno strogu validaciju korisničkih unosa, što omogućava napadačima da ubace zlonamerni ili neadekvatan unos kako bi izazvali neželjene efekte.
Uticaj iskorištenja ranjivosti: Iskorištenje ove ranjivosti može imati ozbiljne posledice, uključujući:

    • Neovlašćen pristup sistemima ili funkcijama za koje korisnik nije ovlašćen.
    • Izazivanje neželjenih promena u podacima ili konfiguraciji sistema.
    • Preuzimanje kontrole nad korisničkim nalozima ili privilegijama.
    • Ometanje normalnog rada aplikacije ili sistema.

Ranjivosti u softveru koje su dozvolile napad: U ovom slučaju, nedovoljna validacija unosa omogućava korisniku da izvrši neovlašćen pristup kao admin korisnik. Softver nije adekvatno proverio korisničko ime i lozinku, što omogućava napadaču da izvrši SQL injection napad i pristupi kao admin korisnik.

Primerene kontramere:

    • Parametrizovani upiti: Koristite parametrizovane upite prilikom izvršavanja SQL upita kako bi se sprečila injekcija SQL-a.
    • Korišćenje pripremljenih izjava: Umesto da direktno konkatenirate korisnički unos u SQL upit, koristite pripremljene izjave ili ORM (Object-Relational Mapping) biblioteke koje automatski sanitizuju korisnički unos.
    • Validacija unosa: Adekvatno validirajte sve korisničke unose kako biste osigurali da su oni u skladu sa očekivanjima i ograničenjima aplikacije.
    • Princip najmanjih privilegija: Dodeljujte korisnicima samo neophodne privilegije koje su im potrebne za obavljanje njihovih zadataka, kako bi se smanjio rizik od neovlašćenog pristupa.

Zadatak 3: Client-Side XSS protection

Kratak postupak: Poslao sam post upit na http://localhost:3000/api/Users, ovo je u body: {"email": "<iframe src=\"javascript:alert(`xss`)\">", "password": "xss"}. Kad se ulogujem kao administrator u email sekciji stoji xss fajl sto ne bi smelo da bude tako, nego samo email string

Klasa napada - "XSS" (Cross-Site Scripting): Ova klasa napada obuhvata situacije kada napadač ubacuje zlonameran JavaScript kod u web stranicu ili aplikaciju, koji se potom izvršava u browser-u korisnika.
Uticaj iskorištenja ranjivosti: Iskorištenje ove ranjivosti može imati ozbiljne posledice, uključujući:


    • Krađu sesija korisnika.
    • Skidanje ili manipulacija sadržaja stranice.
    • Preusmeravanje korisnika na zlonamerni sajt.
    • Izvršavanje neželjenih akcija u ime korisnika, kao što su slanje podataka na druge servere.
    
Ranjivosti u softveru koje su dozvolile napad: U ovom slučaju, ranjivost je posledica nedovoljne sanitizacije korisničkog unosa prilikom prikaza na web stranici. Softver nije adekvatno filtrirao ili izbegavao izvršavanje HTML ili JavaScript koda koji se unosi u polje za email adresu.
Primerene kontramere:

    • Sanitizacija unosa: Pre nego što se korisnički unos prikaže na web stranici, treba ga sanitizovati kako bi se uklonili ili neutralisali potencijalno opasni elementi kao što su HTML i JavaScript kod.
    • Upotreba sigurnih biblioteka: Koristite sigurne biblioteke i okvire za manipulaciju korisničkim unosima, koji automatski vrše sanitizaciju i sprečavaju XSS napade.
    • Content Security Policy (CSP): Konfigurišite CSP kako biste ograničili koje vrste sadržaja mogu biti izvršene na stranici, smanjujući tako površinu napada za XSS.
    • Validacija korisničkog unosa: Validirajte sve korisničke unose kako biste osigurali da sadrže samo očekivane vrednosti.
    • Redovno ažuriranje softvera: Redovno ažurirajte softver kako biste popravili poznate XSS ranjivosti i smanjili rizik od napada.

Zadatak 4:  NoSQL Dos

Kratak postupak: Znaci kad sam kreirao review za proizvod, nasao sam u inspektu da ga je ubacio u http://localhost:3000/rest/products/1/reviews. Medjutim kad sam promenio u http://localhost:3000/rest/products/sleep(2000)/reviews. Server sam pauzirao tj stavio u "sleep" mode.
Klasa napada - "Injection" (Injekcija): Ovaj tip napada obuhvata situacije kada napadač ubacuje zlonamerni kod ili komande u aplikaciju ili sistem putem nevalidnih ili nebezbednih korisničkih unosa.
Uticaj iskorištenja ranjivosti: Iskorištenjem ove ranjivosti, napadač može izazvati različite štetne efekte, uključujući:

    • Prekid normalnog rada sistema ili usluge.
    • Izvršavanje neovlašćenih komandi ili skriptova na serverskoj strani.
    • Prikupljanje osetljivih informacija iz baze podataka ili drugih izvora podataka.
    • Povećanje privilegija ili izazivanje neželjenih promena u aplikaciji ili sistemu.
    
Ranjivosti u softveru koje su dozvolile napad: Ove ranjivosti mogu biti rezultat nedostatka adekvatne provere korisničkog unosa ili neispravne obrade unosa od strane softvera. U ovom slučaju, aplikacija nije adekvatno validirala ili sanitizovala korisnički unos, omogućavajući napadaču da ubaci zlonamerni kod poput sleep(2000) direktno u URL.
Primerene kontramere:

    • Input Validation: Softver bi trebao pažljivo validirati sve korisničke unose kako bi se osiguralo da ne sadrže zlonamerni kod ili nebezbedne karaktere.
    • Sanitizacija unosa: Dodatno, korisnički unosi bi trebali biti sanitizovani kako bi se uklonili potencijalno opasni karakteri ili komande.
    • Korišćenje parametrizovanih upita: Umesto direktnog uključivanja korisničkih unosa u upite ka bazi podataka ili u druge delove aplikacije, trebalo bi koristiti parametrizovane upite kako bi se izbegla injekcija SQL-a ili drugih skriptova.
    • Pravilna konfiguracija servera: Serveri bi trebalo da budu konfigurisani na način koji ograničava pristup i sprečava izvršavanje zlonamernih skriptova.
    • Edukacija korisnika: Korisnici bi trebalo da budu obučeni da prepoznaju potencijalne opasnosti od ubrizgavanja i da budu svesni kako da koriste aplikaciju na siguran način.

Zadatak 5: Vurnalable Components

Kratak postupak: Istrazio sam i video da chatbot da manipulacijom unosa tj admin"); process=null; users.addUser("1337", "test moze doci do greske tj gasenja chatbota.

Klasa napada - "Vulnerable Components" (Ranjive komponente): Ova klasa napada obuhvata situacije kada aplikacija koristi ranjive ili nesigurne komponente, što omogućava napadaču da izazove neželjene efekte ili izvrši zlonamerne akcije.
Uticaj iskorištenja ranjivosti: Iskorištenje ove ranjivosti može imati ozbiljne posledice, uključujući:
    • Mogućnost izvršavanja zlonamernog koda ili komandi na serveru.
    • Ometanje normalnog rada aplikacije ili čak kompromitovanje celokupnog sistema.
    • Neovlašćen pristup ili manipulacija podacima korisnika ili sistema.
    
Ranjivosti u softveru koje su dozvolile da napad uspe: U ovom slučaju, ranjivost je posledica nebezbednog korišćenja npm modula 'juicy-chat-bot'. Funkcija koja procesuira korisničke poruke koristi VM kontekst, i omogućava izvršavanje proizvoljnog JavaScript koda, uključujući i zlonamerni.

Primerene kontramere:

    • Korišćenje bezbednih komponenti: Pazite da koristite samo pouzdane i bezbedne komponente i biblioteke u vašem softveru.
    • Smanjenje privilegija: Ako je moguće, ograničite privilegije koje komponente imaju u sistemu, kako bi se smanjio potencijalni uticaj ranjivosti.
    • Provera ranjivosti komponenti: Redovno pratite bezbednosne obaveštenja i ažurirajte komponente na najnovije verzije kako biste popravili poznate ranjivosti.
    • Upotreba sigurnih konfiguracija: Konfigurišite komponente i biblioteke na način koji smanjuje površinu napada i ograničava izvršavanje zlonamernog koda.
    • Bezbedno korišćenje eval funkcije: Izbegavajte korišćenje eval funkcije ili sličnih mehanizama koji izvršavaju proizvoljan kod, jer su oni često izvor ranjivosti. Umesto toga, koristite bezbednije alternative za dinamičko izvršavanje koda.
