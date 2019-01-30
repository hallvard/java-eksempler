# Versjon 1 av sjakk med JavaFX og FXML

I denne versjonen er målet å representere brettet logisk, som en objektstruktur som er skilt fra, men koordinert med den visuelle representasjonen. Dette er typisk for app-er, en har en logisk objektstruktur, som passer til det underliggende problemet, og som brukes for å oppdatere den visuelle.

## Representasjon av brikker

Det finnes mange alternative representasjoner, og vi har valgt å bruke (en liste av) **Piece**-objekter for å lagre informasjon om brikker og hvor de er på brettet. Inni **Piece**-objektene ligger det informasjon om brikketype (**kind**), hvor den er på brettet i form av to rute-koordinater (**x** og **y**) og unicode-symbolet som brukes for visning (**symbol**):

```java
public class Piece {
// R(ook), B(ishop), (k)N(ight), Q(ueen), K(ing), P(awn)
char kind;
// a-h
char x;
// 1-8
int y;
// use unicode symbols, see https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode
char symbol;

public Piece(final char kind, final char x, final int y, final char symbol) {
   this.kind = kind;
   this.x = x;
   this.y = y;
   this.symbol = symbol;
}

@Override
public String toString() {
   return "" + kind + "@" + x + y;
}
}
```

Vi har en konstruktør som brukes for å sette alle variabel-verdiene, og etter at **Piece**-objektet er laget, så er det bare meningen at koordinatene skal endres. Vi har imidlertid ikke kapslet inn dataene for å sikre at de er og forblir gyldige, det gjør vi i en senere versjon.

Det kan kanskje virke rart at vi bruker **char**-typen for kolonne-koordinatet, men **char** er teknisk sett en tall-type, og når en bruker tegnene **'a'**-**'h'**, så kan en også *regne*, med dem. F.eks. kan en finne kolonne-nummeret (0-7) ved å trekke fra **'a'**, som i **int colNum = piece.x - 'a'**, eller finne avstanden mellom to kolonner ved å trekke den ene fra den andre, som i **int dist = piece1.x - piece2.x**).

## Kontroller-klassen

Rollen til kontrolleren i en app, er å koordinere det logiske laget med det visuelle:
- **fra logisk til visuell representasjon**: Den logiske representasjonen av brikker og brett bestemmer hva som vises gjennom den visuelle representasjonen. Altså må lista av **Piece**-objekter brukes for å sette innholdet i rutene på sjakkbrettet.
- **fra UI-input til logiske operasjoner**: Når brukeren gir inn input, så må det oversettes til operasjoner på den logiske representasjonen, f.eks. endring av koordinatene til brikker som flyttes eller fjerning av brikker som slås.

Kontroller-klassen for en app oppgis i det ytterste elementet i FXML-fila, som verdien av fx:controller-attributtet:

```fxml
<BorderPane ... andre attributter her ... fx:controller="javafx.chess.v1.ChessController">
    ... alt innholdet her ...
</BorderPane>
```

### Representasjon og initialisering av brikke-lista

Kontrolleren (av typen **ChessController**) trenger følgelig variabler som holder styr på både den logiske representasjonen og den visuelle. Den logiske består (foreløpig) av lista av brikker, som initialiseres i konstruktøren vha. en liste som inneholder hvilke brikker som er med fra starten og hvor de er plassert.

```java
 List<Piece> allPieces = new ArrayList<>();
 
 List<String> startPieces = Arrays.asList(
         "Ra8♜", "Nb8♞", "Bc8♝", "Qd8♛", "Ke8♚", "Bf8♝", "Ng8♞", "Rh8♜",
         "Pa7♟", "Pb7♟", "Pc7♟", "Pd7♟", "Pe7♟", "Pf7♟", "Pg7♟", "Ph7♟",
         "Pa2♙", "Pb2♙", "Pc2♙", "Pd2♙", "Pe2♙", "Pf2♙", "Pg2♙", "Ph2♙",
         "Ra1♖", "Nb1♘", "Bc1♗", "Qd1♕", "Ke1♔", "Bf1♗", "Ng1♘", "Kh1♖"
);

public ChessController() {
   for (var s : startPieces) {
      var pieceKind = s.charAt(0);
      var x = s.charAt(1);
      var y = s.charAt(2) - '0';
      var symbol = s.charAt(3);
      var piece = new Piece(pieceKind, x, y, symbol);
      allPieces.add(piece);
      // koden over tilsvarer følgende én-linjer:
      // allPieces.add(new Piece(s.charAt(0), s.charAt(1), s.charAt(2) - '0', s.charAt(3)))
}
}
 ```

**allPieces**-variablen inneholder alle brikkene (av typen **Piece**), og ved hvert flytt endres koordinatene til brikken som flyttes og brikken som evt. slås fjernes fra lista.
**startPieces**-variablen inneholder en liste med **String**-objekter som vha. fire tegn angir brikketype, koordinatene og unicode-tegnet, altså den informasjon som **Piece**-konstruktøren tar inn. 
Løkka i konstruktøren går gjennom hvert **String**-objekt, lager et tilsvarende **Piece**-objekt og putter det inn i lista.
Siden initialiseringskoden er i konstruktøren, så kjøres den som en del av opprettelsen av kontroller-objektet. På dette tidspunktet er *ikke* kontrolleren koblet til noe GUI, så vi kan *ikke* samtidig oppdatere GUI-objektene. Se lenger ned for mer om hva som skjer under oppstart av app-en.

### Initialisering og oppdatering av brettet iht. brikkelista

Med **allPieces**-lista med **Piece**-objekter så vet en hvor alle brikkene er, og kan oppdatere GUI-objektene som tilsvarer brikkene slik at de stemmer overens. Det krever at kontrolleren også har oversikt over de visuelle brikke-objektene, som for er en drøss **Label**-objekter definert i [FXML-fila](Chess.fxml). For å gjøre disse tilgjengelig i kontrolleren kreves to ting:
- alle **Label**-elementene i FXML-fila må ha et **fx:id**-attributt som gir det et unikt navn 
- kontrolleren må ha variabler med tilsvarende type (**Label**) og navn, og **@FXML**-*annotasjonen* må stå foran alle variabeldeklarasjonene 

Vi har valgt å rute-koordinatene som navn på tilsvarende **Label**-objekt, så da må kontrolleren ha følgende variabel-deklarasjoner.

```java
@FXML Label a1, a2, a3, a4, a5, a6, a7, a8;
@FXML Label b1, b2, b3, b4, b5, b6, b7, b8;
... tilsvarende for c1-g8
@FXML Label h1, h2, h3, h4, h5, h6, h7, h8;

List<Label> allLabels;
```

Ja, vi må faktisk ha én variabel pr. rute, hele 64 stk. Det finnes veier utenom, men det krever mer komplisert programmering, som vi heller vil unngå. For videre bruk er det enklere om vi har en liste med disse referansene i en bestemt rekkefølge, derfor har vi også deklarert **allLabels**-variablen. Vi kan hente ut **Label**-objektet for et gitt **Piece**-objekt vha. et relativt enkelt uttrykk: **var label = allLabels.get((piece.x - 'a') * 8 + piece.y - 1)**.

Over ble **allLabels**-variablen deklarert, men initialiseringen må utsettes pga. rekkefølgen ting skjer i når en app som dette starter opp:
- Først lastes all FXML-koden inn, og alle GUI-objektene og én kontroller (instans av kontroller-klassen) opprettes automagisk iht. FXML-koden.
- Under opprettelse av kontrolleren så utføres først initialiseringskoden i deklarasjonene, og deretter kjøres koden i konstruktøren (uten argumenter).
- Kontrolleren og GUI-objektene kobles deretter samme automagisk ved at alle **@FXML**-annoterte variabler settes til tilsvarende GUI-objekter.
- Til slutt kjører en evt. **@FXML**-annotert **initialize**-metode.

Det betyr at initialisering av **allLabels**-lista må skje i **initialize**-metoden:

```java
@FXML
void initialize() {
   allLabels = List.of(
         a1, a2, a3, a4, a5, a6, a7, a8,
         b1, b2, b3, b4, b5, b6, b7, b8,
         c1, c2, c3, c4, c5, c6, c7, c8,
         d1, d2, d3, d4, d5, d6, d7, d8,
         e1, e2, e3, e4, e5, e6, e7, e8,
         f1, f2, f3, f4, f5, f6, f7, f8,
         g1, g2, g3, g4, g5, g6, g7, g8,
         h1, h2, h3, h4, h5, h6, h7, h8
      );
   updateBoard();
}

void updateBoard() {
   for (var label : allLabels) {
      label.setText("");
   }
   for (var piece : allPieces) {
      var label = allLabels.get((piece.x - 'a') * 8 + piece.y - 1);
      label.setText(String.valueOf(piece.symbol));
   }
}
```

**updateBoard**-metoden, som kalles etter at **allLabels**-lista er satt, sikrer at **Label**-objektene svarer til lista med **Piece**-objekter: Først blankes alle **Label**-objektene og så går den gjennom hvert **Piece**-objekt, finner tilsvarende **Label** og setter det til å vise brikkens symbol, altså unicode-tegn.

Det viktigste er skillet mellom konstruktøren og **initialize**-metoden: I konstruktøren kan alt som ikke bruker eller berører GUI-objektene initialiseres, mens i **initialize**-metoden så kan alt/resten initialiseres. Sånn sett kunne vi flyttet kode fra konstruktøren til (først i) **initialize**-metoden, men vi kan ikke flytte


```java
@FXML
void initialize() {
   allLabels = List.of(
         a1, a2, a3, a4, a5, a6, a7, a8,
         b1, b2, b3, b4, b5, b6, b7, b8,
         c1, c2, c3, c4, c5, c6, c7, c8,
         d1, d2, d3, d4, d5, d6, d7, d8,
         e1, e2, e3, e4, e5, e6, e7, e8,
         f1, f2, f3, f4, f5, f6, f7, f8,
         g1, g2, g3, g4, g5, g6, g7, g8,
         h1, h2, h3, h4, h5, h6, h7, h8
      );
   updateBoard();
}

void updateBoard() {
   for (var label : allLabels) {
      label.setText("");
   }
   for (var piece : allPieces) {
      var label = allLabels.get((piece.x - 'a') * 8 + piece.y - 1);
      label.setText(String.valueOf(piece.symbol));
   }
}
```

Det siste som gjenstår er å håndtere flytting av brikker når flytte-knappen trykkes, basert på innholdet i de to innfyllingsfeltene. FXML-kode må oppdateres på to måter:
- Knappetrykk må trigge kjøring av en kontroller-metode, så vi legger inn et **onAction** attributt **Button**-elementet med en referanse til **handleMove**-metoden. 
- Kontrolleren må kunne hente ut teksten fra de to innfyllingsfeltene (av typen **TextField**), så vi legger inn et **fx:id**-attributt i hver av dem.

```fxml
   <TextField fx:id="from" ... />
   ...
   <TextField fx:id="to" ... />
   ...
   <Button ... onAction="#handleMove"/>
```

I kontrolleren må en ha tilsvarende kode-elementer, to **TextField**-variabler med samme navn som fx:id-ene og en **handleMove**-metode, alle sammen **@FXML**-annotert:

```fxml
@FXML TextField from;
@FXML TextField to;

@FXML
void handleMove() {
   ...
}
```

**handleMove**-metoden må hente ut og *dekode* tekstene i de to innfyllingsfeltene. Hvis den finner en brikke i fra-ruta, så må koordinatene endres, og hvis den i tillegg finner en brikke i til-ruta, så må denne fjernes fra brettet. Etterpå må GUI-objektene oppdateres med et kall til **updateBoard**, ellers vil jo den visuelle representasjonen være inkonsistent med den logiske. 

```fxml
@FXML
void handleMove() {
   var from = this.from.getText();
   var fromX = from.charAt(0);
   var fromY = from.charAt(1) - '0';
   var fromPiece = findPieceAt(fromX, fromY);
   if (fromPiece != null) {
      var to = this.to.getText();
      var toX = to.charAt(0);
      var toY = to.charAt(1) - '0';
      var toPiece = findPieceAt(toX, toY);
      if (toPiece != null) {
         allPieces.remove(toPiece);
      }
      fromPiece.x = toX;
      fromPiece.y = toY;
   }
   updateBoard();
   this.from.setText("");
   this.to.setText("");
   this.from.requestFocus();
}

Piece findPieceAt(char x, int y) {
   for (var piece : allPieces) {
      if (piece.x == x && piece.y == y) {
         return piece;
      }
   }
   return null;
}
```

En alvorlig mangel ved koden er at den ikke sjekker om sjakkreglene overholdes. Det fikser vi i [versjon 2](../v2/README.md) av app-en.
