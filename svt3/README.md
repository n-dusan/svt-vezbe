# Serverske veb tehnologije - Vežbe 3

## Pristup relacionoj bazi podataka

Primeri u projektu prikazuju osnovne elemente JDBC API-ija za pristup relacionim bazama podataka. U primerima se koristi H2 baza podataka, u in-memory konfiguraciji.

### Sadržaj primera

Klase od `Primer01` do `Primer05` su konzolne aplikacije iz kojih se pristupa H2 bazi podataka.
Metode `initializeDatabaseProperties` i `loadDatabasePropertiesFromFile` služe da inicijalizuju bazu sa nekim sadržajem kako bi se naredni primeri lakše pratili. 
Skripe za kreiranje baze i učitavanje podataka su u fajlovima `schema.sql` i `data.sql`.
Unutar `application.properties` zadaju se konfiguracioni parametri potrebni za konekciju sa H2 bazom podataka. 

- Primer01 prikazuje slanje SELECT komande na server i čitanje rezultata.
- Primer02 prikazuje `preparedStatement` gde se šalje više uzastopnih INSERT naredbi na server.
- Primer03 prikazuje `callableStatement` koji prikazuje pozivanje uskladištene procedure na serveru. Uskladištena procedura je definisana metodom `helloWorld` odnosno skriptom unutar `schema.sql`. Pošto se koristi H2 baza, uskladištene procedure se pišu u Javi umesto u nekom od proceduralnih dijalekata SQL-a.
- Primer04 prikazuje kreiranje dve istovremene konekcije ka bazi i implementaciju naivne transakcije. Pomoću niti se šalju INSERT i SELECT komande na server. Obe niti spavaju (sleep) fiksan vremenski period. Nit 1 dodaje novi podatak u bazu tek nakon ručnog pozove metode `connection.commit()`. Nakon buđenja niti 2 i izvršavanja SELECT naredbe najnoviji podaci postaju vidljivi.
- Primer05 prikazuje upotrebu `jdbcTemplate`. JDBC Template je komponenta Spring-a koja pojednostavljuje upotrebu JDBC-a za operacije nad bazom podataka. Pruža viši nivo apstrakcije nad JDBC API-jem, smanjujući količinu boilerplate koda potrebnog za obavljanje operacija baze. Sa JDBC Template-om, mogu da se izvršavaju SQL upiti, dodaju, ažuriraju i brišu torke iz baze podataka koristeći jednostavne metode, bez potrebe za ručnim upravljanjem konekcijama, transakcijama ili izuzecima.

## Više informacija

- https://www.baeldung.com/spring-jdbc-jdbctemplate
- https://spring.io/guides/gs/relational-data-access/
- https://www.baeldung.com/sql-injection