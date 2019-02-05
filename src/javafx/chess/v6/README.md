# Versjon 6 av sjakk med JavaFX og FXML

Denne versjonen gjør app-en *dokument-sentrisk* med støtte for å lagre og lese inn spill, basert på et filformat for sjakkspill. Filformatet bruker [den offisielle notasjonen for flytt](https://en.wikipedia.org/wiki/Algebraic_notation_%28chess%29). I tillegg kan denne notasjonen brukes ved angivelse av flytt.

Det gis også bedre feedback til brukeren, ved å markere hvilke ruter en brikke kan flyttest til og brikkes som evt. kan tas.

## Dokument-sentriske app-er

I en dokument-sentrisk app så tenker en at alt innholdet i (hele tilstanden til) appen er et *dokument* som kan knyttes til en *lokasjon* (typisk en fil), som det evt. er lest fra og som det kan lagres (tilbake) til. Dette passer godt til en sjakk-app, hvor (tilstanden til) brettet svarer til et dokument som kan lagres til en fil.

Dokument-sentriske app-er har standard-funksjoner for håndtering av dokumenter (de en gjerne finner i "Fil"-menyen):
- **New**: Lag et nytt dokument som *ikke* er knyttet til noen lokasjon (ennå). Ved lagring må lokasjonen oppgis.
- **Open...**: La brukeren velge en eksisterende lokasjon (fil) og opprett et dokument med innholdet lest derfra. Dokumentet vil da være knyttet til denne lokasjonen.
- **Save**: Lagre innholdet i gjeldende dokument i lokasjonen det er knyttet til. Hvis det ikke har noen lokasjon, så oppfører funksjonen seg som **Save As**.
- **Save As**: Be brukeren velge en lokasjon og skriv innholdet i gjeldende dokument til denne lokasjonen, som dokumentet nå vil være knyttet til.
- **Save Copy As**: Be brukeren velge en lokasjon og skriv innholdet i gjeldende dokument til denne lokasjonen, uten å endre lokasjonen dokumentet er knyttet til.

For å implementere denne logikken må vi definere og implementere et egnet filformat for (innholdet i) **Chess**-objekter og legge til felt og metoder i **Chess** for å håndtere en evt. knytning til en fil (av typen **File**).

## Filformat for Chess-objekter

Filformatet er basert på [den offisielle notasjonen for flytt](https://en.wikipedia.org/wiki/Algebraic_notation_%28chess%29). Generelt oppgis typen til brikken som flyttes og koordinatene til fra- og til-ruta og evt. en 'x' foran til-ruta, hvis en tar en brikke. Formatet er imidlertid fleksibelt, så en kan utelate brikketype og (deler av) fra-ruta, så lenge det er nok til å gjøre flyttet utvetydig. Som oftest er det nok med brikketypen evt. en x og så til-ruta, og for bondetrekk så kan brikketypen også utelates.

Eksempler:
- e4: Typisk åpningstrekk, kan ikke være annet enn bonden på e2
- Nf3: Også et (mulig) åpningstrekk, angir brikketypen for å skille fra bondetrekk
- Nxf3: Samme trekk, men tar en motstanderbrikke.
- R4g3: Tårntrekk, men også raden oppgis, fordi det andre tårnet (på en annen rad) også kan flytte til g3. Noen ganger er det kolonnen en må oppgi for å skille brikker av samme type, og i verste fall (med tre dronninger i en spesiell konfigurasjon) må både kolonne og rad må oppgis.

Ved lesning (og angivelse av flytt i GUI-et) så støttes det fleksible og kompakte formatet, mens ved skriving så brukes det fulle formatet med både brikketype og fra-rute i tillegg til til-ruta.

For å gjøre formatet litt mer lesbart så støttes også linjer med kommentarer, ved at alt som kommer etter #-tegnet fjernes og evt. tomme linjer ignoreres.

## MoveFormat-klassen

Det går fint an å legge logikken for filformatet i **Chess**-klassen, men siden den allerede er blitt nokså stor, så er det bedre å ha logikken i en egen klasse. Generelt ønsker en små klasser som gjør få ting, heller enn store klasser som gjør mange ting. Vi kaller klassen **MoveFormat** og **Chess** bruker den som hjelpeklasse, ved å ha et eget **MoveFormat**-objekt som den brukes når formatet skal kodes eller dekodes.

**MoveFormat** får to metoder, **String toString(Move)**, som lager en **String** med riktig format gitt et **Move**-objekt, og **Move parse(String)**, som gjøre det motsatte nemlig lager et **Move**-objekt gitt en **String**. Som nevnt over, så søtter lesing med **parse**-metoden det fleksible og kompakte formatet.
Fordi et **Move**-objekt inneholder referanser til faktiske brikker og disse finnes i **Chess**-objektet, så har **MoveFormat**-klassen en variabel som refererer til(bake) til **Chess**-objektet.

## Endringer i Chess-klassen

Knytningen til en lokasjon, håndteres av en **location**-variabel av typen **java.io.File**, og tilhørende **get**- og **set**-metoder. Merk at Java har to ulike klasser som brukes til å angi filnavn, **java.io.File** og **java.nio.file.Path**. **Path** er nyere og ment å erstatte **File**, men her er det mest praktisk å bruke **File**, siden JavaFX sine fildialoger bruker den klassen.
Les mer om dette [her](https://stackoverflow.com/questions/6903335/java-7-path-vs-file).

Som nevnt over, trenger **Chess** et eget **MoveFormat**-objekt for koding og dekoding av formatet. For at kontrolleren skal kunne dekode et flytt på samme format, så gis det tilgang til **MoveFormat**-objektet vha. en **get**-metode.
Kodingen gjør vi like godt i **toString**-metoden til **Chess**.
Dekoding av formatet trengs bare når det opprettes et nytt **Chess**-objekt, så **Chess**-klassen får en ny konstruktør, som tar inn en rekke tekstlinjer i form av en **Iterable** for **String**-typen. En **Iterable** er en slags liste, men det eneste den kan brukes til er å gå gjennom den med en **for**-løkke.
Denne konstruktøren går altså gjennom linjene, fjerner kommentarer og konverterer til **Move**-objekter, som så utføres vha. en hjelpemetode kalt **doMove** (som også kan brukes av **redo**-metoden til å utføre et flytt representert som et **Move**-objekt).

## Dokument-sentriske funksjoner i kontrolleren

FXML-koden er utvidet med meny-elementer tilsvarende de dokument-sentriske funksjonene, og da må kontrolleren ha tilsvarende metoder. Metodene som trenger å spørre om en fil bruker en instans av **FileChoose**-klassen, som er JavaFX sin fildialog. Samme instans kan brukes til både åpning og lagring, og følgende kode håndterer denne instansen:

```java
private FileChooser fileChooser;

private FileChooser getFileChooser() {
   if (fileChooser == null) {
      fileChooser = new FileChooser();
   }
   return fileChooser;
}
```
Her er poenget at vi utsetter å lage instansen til den faktisk trengs, og når vi koder **getFileChooser** som over og bruker den i alle andre metoder som trenger en fildialog, så blir denne logikken samlet ett sted. Dette kalles *intern innkapsling*, her er det håndtering av **fileChooser**-variablen som kapsles inn, og hensikten er å gjøre koden forøvrig mindre sårbar for endringer. Hvis vi f.eks. går over til å lage instansen på forhånd, så trenger vi bare endre **getFileChooser**-metoden.
