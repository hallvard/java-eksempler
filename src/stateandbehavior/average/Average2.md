# Average2

Dette eksemplet er, sammen med [Average2](Average2.md), ment å utvide forståelsen av sammenhengen mellom hvilke metoder et objekt har og hvilken intern tilstand i form av variabler, det trenger.

## Objekt-utforming

Denne gang ønsker vi oss et objekt som kan holde rede på både gjennomsnittetog median av en sekvens med desimaltall, som det mottar. Denne lille og uskyldige utvidelsen krever at vi revurderer hva data som må lagres i objektet:

- **Hva må objektet huske (på av data) for å kunne oppføre seg riktig?** Medianen er den *midterste* verdien når de sorteres (midt mellom de to midterste verdiene, når det er et like antall verdier). Nå må vi altså både huske *alle* verdiene og *sortere* dem.

## Koding

Klassedeklarasjonen blir som for **Average1**, men med **Average2** som klassenavn.

### Variabeldeklarasjoner

For å huske alle verdiene trenger vi en variabel som ikke representerer et enkelt desimaltall, men en (dynamisk) sekvens av flere desimaltall. Dette får vi til ved å bruke **List<Double>** (leses som "liste av Double") som type. (Av tekniske grunner vi ikke går inn på her, så må vi bruke **Double** og ikke **double** som liste-type. Tilsvarende må vi bruke **Integer** istedet for **int**, om vi ønsker en liste med heltall). Fra starten skal lista være tom, så vi bruker **new ArrayList<Double>()** for å initialisere variablen. En får da et **ArrayList**-objekt, som er kodet slik at det passer når typen til variablen er **List**. Dette tilsvarer omtrent en Python-lista, men Java har (dessverre) ikke en egen syntaks for å gjøre en **ArrayList** like lettvint i bruk. En **List** holder selv rede på hvor mange elementer den inneholder, så vi trenger ikke lenger **count**-variablen:

```java
List<Double> values = new ArrayList<Double>();
double sum = 0.0;
```

En viktig ting må nevnes: Både **List** og **ArrayList** er forhåndsdefinerte klasser i pakken **java.util**, og derfor må vi egentlig skrive **java.util.List>** og **java.util.ArrayList** for å være presis, f.eks. for å unngå sammenblanding med andre klasser med samme navn i andre pakker. Faktisk finnes det en klasse som heter **java.awt.List** og koden over er sånn sett tvetydig: Hvilken (av de to) **List**-klasse(ne) mener vi egentlig? For å slippe å måtte skrive pakkenavnet foran klassenavnet overalt, så legger vi til **import**-setninger under **package**-setningen:

```java
package stateandbehavior.average;

import java.util.List;
import java.util.ArrayList;

class Average1 {

   // først kommer variabel-deklarasjoner
   List<Double> values = new ArrayList<Double>();
   double sum = 0.0;
   
   // så konstruktører
   // deretter metoder
}
```

Med disse import-setningene så vil all bruk av **List** og **ArrayList** (uten pakkenavn foran) tolkes som (å referere til) henholdsvis **java.util.List** og **java.util.ArrayList**. **List** blir en slags *kortform* for **java.util.List**, slik at koden blir raskere å lese og skrive. Merk at hvis en nå faktisk ønsker å referere til **java.awt.List**, så må en nå bruke det *fulle klassenavnet* med pakkenavnet foran.

Det kan virke tungvint å måtte legge inn import-setninger for alle klasser en bruker, men heldigvis har verktøy som Eclipse en snarvei for å gjøre det automatisk. Første gang en skriver **List** (f.eks. i en variabel-deklarasjon) så trykker en **<ctrl>-<space>** når markøren står bakom, velger **java.util.List** fra menyen som dukker opp, og vips så legges **import**-setningen til øverst.

### Objektdiagram

Opprettelse av et **Average2**-objekt med **new Average2()** gir følgende objekt(diagram):

![Objektdiagram for objekt laget med **new Average2()**](Average2-object.png)

Du ser at en tom liste angis med "[]". I objekttilstandsdiagrammet nedenfor viser lister med en eller flere elementer.

### Metoder

Klassen må forøvrig utvides på to måter, vi trenger en ny **getMedian**-metode, og **acceptValue**-metoden må legge den nye verdien inn i **values**-lista på riktig plass. Her er koden, med noen kommentarer for å gjøre den litt enklere å forstå.

```java
double getMean() {
   // deler sum på antall verdier, som hentes fra liste-objektet
   return sum / values.size();
}

double getMedian() {
   // hjelpevariabler
   int count = values.size(), middle = count / 2;
   // hvis antallet verdier er et partall (resten når vi deler på 2 er 0)
   if (count % 2 == 0)
      // returner gjennomsnittet av de to midterste verdiene
      return (values.get(middle - 1) + values.get(middle)) / 2;
   else
      // ellers returnes den midterste verdien
      return values.get(middle);
}

void acceptValue(double value) {
   // vi finner først posisjonen der den nye verdien skal legges til
   int i = 0;
   // vi lar i løpe fra 0 til (men ikke med) lista sin lengde
   while (i < values.size()) {
      // hvis verdien på posisjon i er større enn den nye,
      // så har i kommet langt nok og vi avbryter løkka
      if (values.get(i) > value) {
         break;
      }
      i++;
   }
   // be lista om å legge verdien til i posisjon i
   values.add(i, value);
   // som før	
   sum += value;
}
```

### Testing med main-metoden og objekttilstandsdiagram

```java
public static void main(String[] args) {
   Average2 average = new Average2();
   average.acceptValue(4.0);
   average.acceptValue(5.0);
   System.out.println("Verdier: " + average.values);
   System.out.println("Gjennomsnitt: " + average.getMean());
   System.out.println("Middelverdi: " + average.getMedian());
   average.acceptValue(3.0);
   System.out.println("Verdier: " + average.values);
   System.out.println("Gjennomsnitt: " + average.getMean());
   System.out.println("Middelverdi: " + average.getMedian());
   average.acceptValue(0.0);
   System.out.println("Verdier: " + average.values);
   System.out.println("Gjennomsnitt: " + average.getMean());
   System.out.println("Middelverdi: " + average.getMedian());
}
```

![Objekttilstandsdiagram for Average2-objekt opprettet i **main**-metoden](Average2-object-states.png)
