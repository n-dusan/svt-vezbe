# Serverske veb tehnologije - Vežbe 5

## Representational State Transfer (REST)

- Definiše principe softverske arhitekture na vebu
- Nije standard, ali se oslanja na upotrebu nekoliko standarda
- Predstavlja stil softverske arhitekture koji se zasnivana postojanju resursa i
  uniformnog upravljanjanjima putem skupa predefinisanih operacija

### REST principi

- Svaki entitet na vebu je **resurs** (HTML stranica, XML dokument, slika...)
- Svaki resurs mora da ima svoj jedinstveni identifikator putem kojeg mu se može pristupiti
- Isti resurs može biti predstavljen različitim formatima (HTML, XML, JSON...)
- Nad resursima se obavljaju jednostavne operacije
  - Operacije nad resursima pripadaju predefinisanom skupu: Create, Read, Update, Delete
- Najčešći protokol za komunikaciju je HTTP. Operacijama nad resursima odgovaraju HTTP metode
  - GET – za čitanje
  - POST – za kreiranje
  - PUT – za ažuriranje
  - DELETE – za brisanje

### Spring kontroler

- Kontroler je klasa anotirana sa `@Controller` ili `@RestController`
- Metode klase kontrolera su antoirane sa @RequestMapping anotacijom koja
  opisuje zahtev koji treba biti obrađen u toj metodi (URL i tip HTTP metode)
- @RequestMapping anotacija se može navesti i na nivou klase – tada sve metode
  unutar kontrolera imaju u svom URL-u prefiks koji je definisan u toj anotaciji
- Metode unutar istog kontrolera mogu biti definisane tako da imaju isti URL, ali se u
  tom slučaju moraju razlikovati po tipu HTTP metode koju obrađuju

  - Parametre je u kontrolere moguće poslati na dva načina:
    - kao parametar koji je promenljiva u URL-u zahteva – `@PathVariable`
      ```java
      @RequestMapping(value="api/greetings/{id}", method=RequestMethod.GET)
      public void getGreeting(@PathVariable Long id) { … }```
    - Vrednost promenljive `id` se automatski popunjava na osnovu vrednosti iz URL-a. Ukoliko se naziv parametra
      metode i promenljivog dela URL-a ne poklapa, u okviru `@PathVariable` anotacije je potrebno navesti atribut
      `name` čija vrednost će da odgovara promenljivom delu URL-a (između vitičastih zagrada)
  - kao parametar HTTP zahteva – `@QueryParam`
     ```java
     public void getGreeting(@RequestParam Long id) { … }```

- REST operacije GET, PUT, DELETE i POST se realizuju kroz kontrolere koji
obrađuju istoimene HTTP metode
- Identifikacija resursa na koji se operacija odnosi se vrši korišćenjem
  parametrizovanih URL-ova i `@PathVariable` anotacije

### ResponseEntity

- Metode kontrolera vraćaju `ResponseEntity` objekat koji sadrži više
  informacija o odgovoru
- `ResponseEntity` objekat može da sadrži:
  - telo (podatke) - metode anotirane sa @ResponseBody sadrže samo telo
  - zaglavlje (metapodatke)
  - HTTP status kod

## Validacija

U primeru je takođe obrađena i validacija bean-ova koji stižu sa klijentske strane u metode kontrolera.

Npr. imamo sledeći potpis metode kontrolera:
```
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Course> saveCourse(@Valid @RequestBody CourseDTO objekat) {
        ...
	}
```

Anotacijom ___@Valid___ ispred parametra naznačavamo da vrednosti atributa tog objekta koje se šalju sa klijentske strane moraju da zadovolje sva ograničenja koja su navedena u samom modelu, odnosno u klasi __Greeting__. Ova anotacija automatski pokreće validaciju podataka. Ukoliko se naruši bar jedno ograničenje, Spring će automatski da odbije zahtev klijenta i vraća HTTP odgovor sa status kodom __400 Bad Request__.

Ukoliko na nivou modela postoji ograničenje:
```
@NotEmpty(message = "Poruka je obavezna.")
private String text;
```
Ovo znači da ukoliko se sa klijentske strane prosledi objekat koji nema atribut _text_ (to npr. može biti neki JSON objekat {'ime':'pera'}), Spring će da odbije zahtev i vraća HTTP Response čiji je status kod 400, a poruka o grešci je _Poruka je obavezna_. Za svaku ostalu vrednost ovog atributa, zahtev bez problema prolazi do kontrolera u aplikaciju.