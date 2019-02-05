# Versjon 5 av sjakk med JavaFX og FXML

Det nye i versjon 5 er undo og redo, som ikke gir så mye mening for et ekte spill, men er nyttig hvis en prøver ut varianter for egen del. Det er dessuten relevant i mange app-er, så det er greit å vite hvordan en generelt implementerer det.

## Undo- og redo-logikken

Undo og redo fungerer konseptuelt ved at nok data om brukerhandlinger lagres unna til at de kan reverseres og evt. utføres på nytt. Informasjonen lagres på to stacker, én for hver operasjon. Tre tilfeller må håndteres:
1. Når en handling gjøres *første gang* så legges den på undo-stacken, og redo-stacken tømmes.
2. Ved undo så fjernes en handling fra undo-stacken, effekten av den reverseres, og så legges den på redo-stacken.
3. Redo gjør det motsatte, den fjerner en handling fra redo-stacken, utfører den og legger den på undo-stacken.
Hvis en stack er tom når et element skal fjernes, så skjer selvsagt ingenting.

Det som er mest kinkig er å lagre alle relevant data om handlingen, slik at den både kan reverseres og gjøres på nytt. Heldigvis kan en anta at handlingen er gyldig å utføre, ellers ville den ikke blitt utført første gangen, så en trenger ikke validere dataene senere. I et sjakk-spill med våre forenklinger, må en lagre både brikkene og rutene (koordinatene) som er involvert.

## Move-klassen

**Move**-klassen lagrer alle nødvendige data om et flytt. Siden dataene nødvendigvis må oppgis, så settes alle verdiene i konstruktøren. Og siden de ikke skal kunne endres, så har vi bare **get**-metoder.

## Endringer i Chess

Chess-klassen utvides med metoder tilsvarende bruker-funksjonene undo og redo, slik at kontrolleren kan kalles disse nokså direkte. Vi har også lagt til metoder som sier om disse funksjonene er tilgjengelige (kan kalles), da disse trengs for aktivering/deaktivering av funksjonene i GUI-et. En kan argumentere med at Chess da blir litt UI-spesifikk, men på den annen side blir det bedre innkapslingsmessig.
Til slutt gjør vi en liten forenkling: Siden undo-stacken i praksis er en liste over trekkene som er gjort, så kan vi *beregne* hvem sin tur det er, i stedet for å lagre det (i **whitesTurn**).  

### undo

Undo-metoden tar det øverste Move-objektet fra undo-stacken og reverserer effekten av flyttet ved å se på hvilken brikke som ble flyttet, hvor den flyttet fra og om den tok en motstander-brikke.
Metoden må også bytte på hvem sin tur det er, og legge Move-objektet på redo-stacken.

### redo

Redo-metoden tar det øverste Move-objektet fra redo-stacken og utfører det på nytt ved å se på hvilken brikke som flyttet, hvor den flyttet til og om den tok en motstander-brikke.
Metoden må også bytte på hvem sin tur det er, og legge Move-objektet på undo-stacken.

## Endringer i FXML-koden

FXML-koden utvides med en menylinje med Edit-meny med Undo- og Redo-funksjoner og et panel med egne Undo- og Redo-knapper. Disse har referanser til tilsvarende metoder i kontrolleren, og har også en **fx:id**, så de kan oppdateres til å være aktive eller ikke når tilsvarende funksjon er tilgjengelig eller ikke.

## Endringer i kontrolleren

De viktigste endringene er metodene som som håndterer undo og redo, om de nå trigges av meny-elementer eller knapper. De kalles tilsvarende metoder i **Chess** og sørger for å oppdatere både brettet og aktiveringen av meny-elementene og knappene.
