1. Hešovanje lozinki

Trenutno, neki od najbezbednijih algoritama za hešovanje su: SHA-256 i bcrypt. Odabrali smo bcrypt. Ovaj algoritam je specifično dizajniran za hešovanje lozinki jer je spor i sprečava brute force i rainbow table napade, za razliku od SHA-256 koji ima i širu upotrebu za hešovanje.

Bcrypt od parametara ima: salt, work factor i samu lozinku. Salt je dodatak nasumičnih podataka originalnoj lozinki pre hešovanja. Work factor je konfiguracioni parametar koji predstavlja broj iteracija hešovanja po formuli 2^work factor. Samim tim veći work factor čini lozinke sigurnijim, a sam algoritam sporijim. Obično se postavljaju vrednosti oko 10 pa naviše u skladu sa mogućnosti samog hardvera.

Unutar Spring Security Crypto modula čiji je provajder Spring Boot framework, nalazi se klasa org.springframework.security.crypto.bcrypt.BCrypt.

Poslednja verzija Spring Security Crypto modula objavljena 16.2.2024. je verzija 6.2.2 koja do sada nema otkrivenih ranjivosti. Verzije 6.1.x i 6.2.x do verzije 6.2.2 su imale direktnu ranjivost CVE-2024-22234, ali to nije ranjivost direktno povezana sa implementacijom bcrypt algoritma.

Zahtevi za bezbednu implementaciju heš algoritma:

- Izabrati bcrypt za hešovanje lozinki
- Definisati dobra pravila za kreiranje lozinki
- Salt vrednost je definisan po defaultu pri koriščenju bcrypt algoritma
- Work factor ukoliko nije definisan po defaultu, odabrati vrednost tako da se ostvari dobar balans između sigurnosti i brzine izvršavanja
- Ukoliko se koristi Spring Boot framework, koristiti verziju 6.2.2 za Spring Security Crypto modul jer nema pronađenih ranjivosti

2. Mehanizam revizije (auditing)

Jedan od mehanizama za logovanje događaja unutar Spring Boot framework-a jeste Log4j2. Ovaj mehanizam podržava neke od traženih zahteva, a neke je potrebno dodatno konfigurisati.

- Log4j2 možemo da konfigurišemo u xml datoteci na nivo logovanja INFO, koji će da obuhvati poruke o greškama, upozorenjima, ozbiljnim problemima i informacijama o radu aplikacije. Tako kreirana log datoteka ce sadržati sve potrebne informacije za rezrešavanje problema.
- Log4j2 nije direktno odgovoran za sadržaj koji se loguje u datoteci, već sami ga konfigurišemo tako da se upisuju bitne informacije o akterima i izvršenim akcijama. Od same konfiguracije zavisi koliko lako bismo mogli izdvojiti vrstu događaja i kako će sama datoteka biti formatirana. Sigurnost same datoteke možemo obezbediti slanjem na siguran server.
- Kao što je već navedeno, s obzirom da sami konfigurišemo sadržaj koji će biti upisan u datoteku, trebamo izbegavati upis osetljivih informacija ili koristiti neki vid enkripcije ukoliko je neophodno upisati takve informacije. Može se koristiti filters u xml datoteci za filtriranje poruka koje sadrže osetljive informacije, gde ako je filter on match = true onda odbijamo poruku.
- Log4j2 obezbeđuje dostupnost i integritet log datoteka pomoću funkcionalnosti kao što su rotiranje log datoteka (u xml datoteci konfigurisati Appender RollingFile koji ograničava veličinu i starost datoteka), arhiviranje logova i slanje logova na udaljeni server (u xml datoteci konfigurisati Appender Socket koji sadrži konfiguraciju servera).
- Log4j2 obezbeđuje precizne vremenske oznake za svaki logovani događaj, a u xml datoteci možemo specificirati pattern layout za ispis vremenske oznake.
- Urednost logova je obezbeđena pravilnom upotrebom prethodno navedenih konfiguracija.

3. Dodatne bezbednosne kontrole

U prethodnom projektu iz predmeta Informaciona bezbednost smo koristili bezbednosnu kontrolu za hešovanje lozinki pomoću bcrypt algoritma, poštovajući sve navedene zahteve, osim trenutne verzije. Tad je bila aktuelna neka od verzija 6.1.x za koje smo uvrdili da imaju ranjivost CVE-2024-22234.
