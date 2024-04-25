---
subtitle: 1.Opis projekta na visokom nivou
title: |
  IKS i ISS UberApp

  analiza koda
---

UberApp je web aplikacija sa bekendom urađenim u SpringBoot frejmvorku i
Angular frontendom.

Ciljj aplikacije je da omogući korisnicima pozivanje i praćenje taksija
i pregled inforamcija o vožnji, prihvatanje i odbijanje vožnji za vozače
i oragnizaciju i kontrolu za admin korisnike. Iz toga slijedi da postoje
tri vrste korisničkih naloga koji sa sobom nose različita prava pristupa
i samim tim različite bezbjednosne rizike.

#### Moguće akcije korisnika:

1.  Korisnik (Neregistrovan):

    1.  Registracija

    2.  Procjena cijene i trajanja vožnje za unijete lokacije

    3.  Aktiviranje registrovanog naloga

2.  Korisnik (Registrovan):

    1.  Log In/Out

    2.  Zahtijev za vožnju

    3.  Pregled istorije vožnji

    4.  Ocjena vozača i vozila

    5.  Panic dugme tokom vožnje

    6.  Live podrpška putem poruka

    7.  Izmjene ličnih podataka

3.  Vozač:

    1.  Izmjene ličnih podataka -- slanje zahtijeva administratoru

    2.  Označavanje kraja i početka radnog vremena

    3.  Prihvatanje i odbijanje zahtijeva za vožnju

    4.  Pregled istorije vožnji

    5.  Panic dugme tokom vožnje

4.  Admin:

    1.  Izmjena podataka o vozačima

    2.  Pregled statistika svih vožnji i vozača

    3.  Reagovanje na panic dugme

    4.  Korisnička podrška

2.Razvojni tim:

-   Andrej Mitrović

-   Danilo Babić

3.Opis pronađenih defekata:

(x.x -- akcija korisnika navedena u opisu projekta)

-   1.1

    -   prilikom registracije korisnika nema dovoljno stroge kontrole
        kod odabira korisničkog imena i lozinke

    -   lozinka se ne enkoduje prije slanja serveru

    -   id korisnika u bazi podataka je samo redni broj njegovog upisa u
        bazu, koji se koristi dalje u aplikaciji kod zahtijeva tipa
        **request-useri-info?Id**

-   1.3

    -   link za aktivaciju koji se salje korisniku na email vodi do web
        stranice na lokaciji -- **activation?id**, gdje je id samo redni
        broj korisnika u bazi podataka

-   2.2

    -   nije ograničen broj zahtijeva koji korisnik može da šalje --
        baza podataka bi se samo punila sa „pending" zahtiejvima za
        vožnje, a vozači bi dobijali konstantne pop-up notifikacije

-   2.4

    -   korisnik može ostaviti bilo kakvu poruku ocjene za vozača ili
        vozilo, gdje je jedino ograničenje broj karaktera

    -   izmjena ocjene vozača je moguća i kreira novu ocjenu u bazi
        podatka

    -   korisnik može putem nasumičnog ID-a, ostavljati ocjene za vozače

-   2.5

    -   panic dugme nema ograničenje korišćenja i time adminu izlaze
        konstantna pop-up obavještenja

    -   panic poruka nije obavezna

-   2.6

    -   live podrška nema filter na poruke i ograničenja slanja poruka i
        njihove dužine

-   2.7

    -   izmjene ličnih podataka nisu ograničene ni vremenski ni
        sadržajem

    -   moguće je zaobići sva pravila sadržaja imena i lozinke kod
        registracije njihovom izmjenom na profilu

    -   putem nasumičnog Id-a korisnik može izmjeniti tuđe podatke

-   3.2 i 3.3

    -   nema ograničenja na korišćenje ovih akcija i nema potvrde
        njihove validnosti. Vozač može da prijavi radno vrijeme po želji
        ☺.

-   3.5

    -   isti problem panic dugmeta kao i kod putnika

-   4.x

    -   ne postoje ograničenja kod ovih akcija u vidu sadržaja ili broja
        izvođenja

-   4.1

    -   admin može da izmjeni podatke bilo kom vozaču putem nasumičnog
        Id-a, ali takođe i običnom korisniku

#### Dodatne ranjivosti:

-   Enkodovanje lozinke na serveru:

    -   tajna je hardkodovana unutar izvornog koda

    -   ista tajna se koristi i na drugim mjestima

-   Neki od endpoint-a ne provjeravaju da li je korisnik koji je poslao
    zahtijev isti kao i korisnik preko čijeg se Id-a pristupa podacima

-   Ne postoje ograničenja atributa DTO objekata

-   Authorization Guard na frontend dijelu ne porkiva sve putanje ruter
    komponente

4.Sažetk preporuka za poboljšanje koda:

Dosta ranjivosti ove aplikacije dolazi od nedovoljne ili pogrešne
implementacije autentifikacije i autorizacije, pa je potrebno provjeriti
sve akcije korisnika i primjeniti storže kontrole. Dalje je potrebno
uvesti ograničenja i pravila kod unosa sadržaja u forme i ograničiti
određene akcije od uzastopnog korišćenja. Sve tajne koje se koriste u
aplikaciji premjestiti u konfiguracione fajlove. Id korisnika treba
generisati na nesekvencijalan način.

5.Vrijeme provedeno na pregledanju koda:

-   Vrijeme: 2h 49min

-   Broj defekata: 23
