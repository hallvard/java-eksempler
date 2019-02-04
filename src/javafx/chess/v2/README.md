# Versjon 2 av sjakk med JavaFX og FXML

I denne versjonen er målet å implementere (noen av) reglene for lovlige flytt, og gi tilbakemelding til brukeren om hva som evt. gjorde et trekk ulovlig.

## Meldingsfeltet for tilbakemelding

Siden vi skal gi tilbakemelding til brukeren om ugyldige/ulovlige flytt, så trenger vi et meldingsfelt (en **Label**) i FXML-fila. Dette legges bak flytt-knappen og gis et unikt **fx:id**-attributt, og en tilsvarende variabel deklareres i kontroller-klassen:

```fxml
   <Label fx:id="message" ... />
```
```java
   @FXML Label message;
```

## Regler for lovlige flytt

Ved siden av å validere koordinatene, vil vi i første omgang sjekke følgende regler for lovlige flytt:
- Kongen kan bare flytte/slå en rute diagonalt, horisontalt eller vertikalt.
- Løperen kan bare flytte/slå diagonalt.
- Tårnet kan bare flytte/slå horisontalt eller vertikalt.
- Dronningen kan flytte/slå diagonalt, horisontalt eller vertikalt.
- Springeren (hesten) kan flytte/slå 2 ruter horisontalt eller vertikalt og 1 rute vinkelrett på det. Bare springeren kan hoppe over andre brikker.
- Bonden kan flytte 1 rute frem eller 2 ruter frem fra utgangspunktet, men slår 1 rute frem og til siden.

Vi tar altså ikke med regler for rokade eller en passent.

## Implementasjon

Grovlogikken for sjekking av flyttet håndteres av **handleMove**-metoden, som trigges av flytte-knappen. Først dekodes og sjekket fra- og til-koordinatene. 
Deretter sjekkes det at det er en brikke i fra-ruta og at til-ruta er ulik fra ruta, før lovligheten av flyttet iht. brikken sjekkes av **isValidMove**-metoden. Denne tar inn brikken som flyttes, koordinatene til ruta den flyttes til og en evt. brikke som tas. Det siste er nødvendig siden bønder tar på en annen måte enn de flytter.
Hvis alt er greit så utføres flyttet, og hvis ikke så settes meldingsteksten til en passende tekst og metoden avsluttes.

**isValidMove**-logikken regner først ut noen hjelpeverdier, som gjør det enklere ved sjekk av den brikkespesifikke logikken. De fleste brikketypene trenger kun absoluttverdien til differansen mellom fra- og til-koordinatene. For et horisontalt eller vertikalt flytt må den ene være 0, for et diagonalt flytt må de være like. For springeren må den ene være 2 og den andre 1.
Regelen om å ikke kunne hoppe over andre brikker, sjekkes av **isPieceBetween**-hjelpemetodene. For hver brikke (andre enn den som flyttes og evt. tas), så sjekkes det vha. **isBetween**-metoden om den ikke befinner seg på en rute mellom fra- og til-ruta.

En svakhet ved **isValidMove** er at når den bare returnerer **false** når flyttet er ulovlig, så er det vanskelig å gi en spesifikk melding som sier presist hva som var galt med et flytt. Et alternativ er å la den returnere en **String** med en spesifikk melding, når flyttet er ulovlig, og **null** ellers. Dette er én av forbedringene vi gjør i [version 3](../v3/README.md). I tillegg skal vi rydde litt opp ved å flytte sjakk-logikken ut av kontrolleren og kapsle den bedre inn.
