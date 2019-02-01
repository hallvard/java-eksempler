# Versjon 4 av sjakk med JavaFX og FXML

Så langt har en kunne flytt alle brikkene når som helst, men nå innfører vi hvilke spiller sin tur det er, og legger til muligheten for å flytte brikker ved å klikke på dem og på til-ruta.

## Endringer i Chess

Hvilken spiller sin tur det er, regnes som spill-logikk, som må inn i **Chess**-klassen. Vi innfører et **boolean**-felt kalt **whitesTurn** og en tilsvarende **is**-metode. Det legges til en ekstra sjekk i **move**-metoden på fargen på brikken som flyttes, og hvis trekket gjennomføres så byttes hvilken spiller som har turen.

## Endringer i FXML-koden

For å kunne støtte klikking på både rute og brikke, så må vi fikse litt på FXML-koden. Som med flytt-knappen, må vi si fra hvilken kontroller-metode som skal trigges av klikk på rute og brikke. Klikking tilsvarer **mouseClicked**-hendelsen, så vi legger til et **mouseClicked**-attributt på *alle* **Rectangle**- og **Label**-elementer som tilsvarer ruter og brikker:

```fxml
   ...
   <Rectangle fx:id="a8s" onMouseClicked="#handleClickSquare" ... />
   ...
   <Label fx:id="a8" ... onMouseClicked="#handleClickPiece">
   ...
```
Vi bruker ulike metoder for de to typene klikk. Merk også at vi har lagt inn et **fx:id** på **Rectangle**-elementet, slik at vi kan referere til det fra kontrolleren vha. en variabel med samme navn.

## Endringer i kontrolleren

Kontrolleren må utvides med metoder for håndtering av klikk, i tråd med endringene gjort i FXML-koden. Når man klikker på en brikke, så kalles **handleClickPiec**-metoden automagisk, og det skal tilsvare å oppgi ruta den står på som fra-rute i et flytt. Problemet er å finne rute-koordinatene, når vi har lagt opp til at samme metode trigges av klikk på alle **Label**-objektene.

Løsningen er todelt:
1. Vi lar **handleClickPiec**-metoden ta inn et **MouseEvent**-objekt, som er et såkalt *hendelsesobjekt* med informasjon om hvilket GUI-objekt klikket var på. Dermed kan én og samme metode håndtere klikk på flere GUI-objekter og likevel skille dem fra hverandre. GUI-objektet får en tak i ved å kalle hendelsesobjektet sin **getSource**-metode.
2. Vi har allerede en liste av **Label**-objekter i en egnet rekkefølge, og lager oss en tilsvarende for **Rectangle**-objektene som tilsvarer rutene. Posisjonen i lista hvor vi finner objektet som ble klikket på, kan brukes til å beregne både kolonnen og raden til ruta. Logikken blir den motsatte av den som brukes i **updateBoar**-metoden for å finne **Label**-objektet for en gitt brikke.

Når en har funnet rute-koordinatene så kan en legge dem inn i det tilsvarende innfyllingsfeltet, klikket blir på en måte en svarvei for å fylle det inn. Klikker man på en av brikkene til den som har turen, så registreres hvilken brikke som er valgt vha. **setSelectedPiece**-metoden. **updateBoard**-metoden er oppdatert til å endre bakgrunnsfargen til brikken som er valgt. Hvis en klikker på en motstander-brikke (eller en rute), så oppdateres til-feltet og trekket utføres (om det er lovlig).
