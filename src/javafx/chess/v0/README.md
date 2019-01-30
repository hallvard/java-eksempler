# Version 0 av sjakk med JavaFX og FXML

For de fleste app-er er det lurt å starte med å tegne skjermbildet i SceneBuilder. Senere redigering kan gjøres direkte i FXML-en, om man er komfortabel med det. Det vil variere hvor mye av app-innholdet som kan tegnes, noe må kanskje lages vha. Java-kode.

<img src="Chess.png" title="Skjermbilde" width="600"/>

Sjakk-appen har først og fremst et sjakkbrett, som består 64 ruter. Hver rute utgjøres av et **Rectangle**-objekt, som fungerer som bakgrunnen til ruta, og en **Label**, som viser frem en evt. brikke vha. unicode-tegn (se [Chess symbols in Unicode](https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode)). Her kunne vi nok brukt **ImageView** med et bilde/ikon, men det er mer lettvint med unicode-tegn. Over og på siden av rutenettet er det **Label**-objekter for rute-koordinatene.

Strukturen av objektene som utgjør sjakkbrettet, kunne nok være laget vha. Java-kode, med en dobbel løkke som oppretter både **Rectangle**- og **Label**-objektene og gir dem riktige skjermkoordinater og visuelle egenskaper. Men det er greiere å komme i gang og enklere å se hvordan det tar seg ut om en gjør det manuelt i SceneBuilder, og med smart bruk av duplisering så gikk det nokså raskt.

Helt øverst er det to innfyllingsfelt, som skal brukes for å skrive inn flytt, og en knapp for å utføre flyttet. Dette er greit for å teste den underliggende logikken, inntil vi implementerer dra-og-slipp av brikker.

For å få et egnet utlegg av GUI-elementene, så brukes to såkalte *container*-elementer, altså GUI-elementer som først og fremst inneholder andre elementer og gir dem et bestemt utlegg. Her har vi brukt **BorderPane**, som gjør det enkelt å omkranse et hovedområde i midten, som her er sjakkbrettet, med andre elementer, som her er innfyllingsfeltene øverst. Alle sjakkbrett-elementene er dessuten lagt i en **Pane**, og siden den *ikk* håndterer utlegg, så har alle **Rectangle**- og **Label**-elementene eksplisitte  koordinater.

Under ser du grovstrukturen til FXML-koden, som altså er skrevet av SceneBuilder og ikke av oss. Ulempen ved å tegne i SceneBuilder er at FXML-koden kan bli litt vanskeligere å finne frem i, men hvis en lager objektene i en ryddig rekkefølge, så er det ikke så vanskelig å finne tilsvarende FXML-elementer.

```fxml
<BorderPane prefHeight="800.0" prefWidth="800.0" xmlns:fx="http://javafx.com/fxml/1" xmlns="http://javafx.com/javafx/9">
   <center>
      <!-- Sjakkbrett i midten -->
      <Pane prefHeight="200.0" prefWidth="200.0" BorderPane.alignment="CENTER">
         <children>
            ... Rectangle- og Label-objekter for sjakkbrettet her ...
         </children>
      </Pane>
   </center>
   <top>
      <!-- Område for håndtering av flytt-komandoer øverst -->
      <Pane prefHeight="54.0" prefWidth="800.0" BorderPane.alignment="CENTER">
         <children>
            <TextField layoutX="66.0" layoutY="9.0" prefHeight="27.0" prefWidth="61.0" />
            <Label layoutX="14.0" layoutY="14.0" text="From" />
            <TextField layoutX="186.0" layoutY="9.0" prefHeight="27.0" prefWidth="61.0" />
            <Label layoutX="154.0" layoutY="14.0" text="To" />
            <Button layoutX="276.0" layoutY="9.0" mnemonicParsing="false" prefHeight="27.0" prefWidth="83.0" text="Move"/>
         </children>
      </Pane>
   </top>
</BorderPane>

```

For å starte app-en, så trengs en egen app-klasse. Den gjør ingenting annet enn å laste inn og putte innholdet i FXML-fila inn i app-vinduet og så gjøre det synlig.
