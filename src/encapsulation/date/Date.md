# Date

Dette eksemplet handler om hensikten med innkapsling og hvilke teknikker som bidrar til det.

## Innkapsling

Innkapsling har to formål:
1) Først og fremst skal det *hindre* at et objekt *brukes galt* og havner i en *tilstand som er ugyldig* og kan føre til feil (siden).
2) For det andre skal det gjøre det enklere å endre detaljer i (koden for) håndtering av intern tilstand, uten at koden i andre klasser også må endres.

Teknikken er å gjemme alle implementasjonsdetaljer og sikre at all tilgang til tilstand og spesielt endring av den, skjer ved å kalle metoder. Konstruktørene og endringsmetodene får ansvar for å sikre at tilstanden hele tiden er og forblir gyldig.

Eksempelobjektet vårt skal holde rede på dato-informasjon, altså et tidspunkt representert ved dag, måned og år. Hver av disse representeres med et heltall, med begrensninger i verdieområdet iht. vanlig kalenderlogikk.  

## Koden

### Variablene

Vi bestemmer oss først for hvordan tilstanden skal representeres i form av variabler:

```java
int year;  // ingen begrensninger
int month; // 1-12
int day    // 1-antall dager i måneden
```

**month**-variablen må altså begrenses til tallene 1-12 og gyldig verdiområde for **day** er avhengig av både måneden og året (pga. skuddår). Hvis **month** f.eks. er 2 (for februar) så må **day** begrenses til 1-28, med mindre **year** tilsvarer et skuddår, hvor også 29 tillates.

### Endringsmetoder

Det er koden som endrer tilstanden, som må hindre at tilstanden blir feil, altså sikre at variablene holdes innenfor gyldige verdiområder. Teknikken er enkel: Før variabler endres, så sjekkes det om de(n) nye verdien(e) vil være gyldig. Om de(n) ikke vil være det, så *utløses unntak*, som er en måte å "kræsje" et program på som gir informasjon om hva som gikk galt, med mulighet til å håndtere det (mer om det i andre eksempler).

Her er et eksempel på hvordan metoden for å endre måneden kan se ut:

```java
void setMonth(int month) {
   if (month < 1 || month > 12) {
      throw new IllegalArgumentException("The month must be between 1 (inclusive) and 12 (inclusive)");
   }
   this.month = month;
}   
```

Konvensjonen i Java er å navngi slike endringsmetoder med "set" foran det logiske navnet til verdien som endres, derav navnet **setMonth**. Derfor kaller en gjerne slike metoder for "settere".

Det sentrale her er **if**-setningen med en betingelse som identifiserer når argumentet er utenfor gyldig verdiområde. Hvis så er tilfelle, så utløses et unntak av en passende type. Typen er viktig, fordi det gjør det lettere å "diagnostisere" problem i etterkant. "**throw**" oversettes her til "utløse", fordi det virker snålt å si "kaste unntak". Det finnes andre unntakstyper enn **IllegalArgumentException** dette, men denne duger i de fleste tilfeller når hensikten er innkapsling. Når unntaket utløses, så utføres ikke resten av metoden (programmet kræsjer på en håndterbar måte) og tilstanden forblir uendret.

Koden over kan virke helt grei, men det er faktisk ikke så enkelt: Det er ikke nok å sjekke at argumentet er i verdiområdet 1-12, tilstanden kan fortsatt bli ugyldig fordi flere variabler henger sammen. Anta f.eks. at **month** og **day** er henholdsvis **1** og **31**, altså verdiene som tilsvarer 31. januar. Så kalles **setMonth(2)**, som vil endre datoen til 31. februar! Dette er opplagt galt og må forhindres.
Løsningen generelt sett er å både sjekke argumentene isolert sett og sammen med gjeldende verdi for andre variabler, slik at en vurderer dem samlet. Her må en sjekke **month**-argumentet sammen med **day**-variablen (i objektet). Eller vent litt, siden vi også må ta høyde for skuddår, så må vi også ta hensyn til **year**-variablen. Hvis gjeldende dato er 29. januar, så kan jo endring av **month** til 2 faktisk være greit, om det er skuddår og 29. februar faktisk finnes.

Her er et forslag til kode, hvor vi har forutsatt at det finnes en hjelpemetode kalt **daysInMonth**, som returnerer antall dager i måneden (og tar høyde for skuddår):

```java
void setMonth(int month) {
   if (month < 1 || month > 12) {
      throw new IllegalArgumentException("The month must be in the range [1, 12]");
   }
   int dayLimit = daysInMonth(year, month);
   if (day < 1 || day > dayLimit) {
      throw new IllegalArgumentException(String.format("The day must be in the range [1, %s], when the month is %s and the year is %s", dayLimit, month, year));
   } 
   this.month = month;
}   
```

Først sjekkes måneden (som over), og hvis den er grei fortsetter koden med en sjekk av dagen, etter å ha beregnet maks-verdien vha. hjelpemetoden **daysInMonth**.

### Valideringsmetoder

Ofte er det lurt å flytte koden med alle verdi-sjekkene over i en eller flere hjelpemetoder, såkalte *valideringsmetoder*. Det gjør koden lettere å lese, og siden de samme if-setningene ofte må brukes flere steder, så sparer en kode:
 
```java
void checkDate(int year, int month, int day) {
   if (month < 1 || month > 12) {
      throw new IllegalArgumentException("The month must be in the range [1, 12]");
   }
   int dayLimit = daysInMonth(year, month);
   if (day < 1 || day > dayLimit) {
      throw new IllegalArgumentException(String.format("The day must be in the range [1, %s], when the month is %s and the year is %s", dayLimit, month, year));
   } 
}

void setYear(int year) {
   checkDate(year, this.month, this.day);
   this.year = year;
}   

void setMonth(int month) {
   checkDate(this.year, month, this.day);
   this.month = month;
}

void setDay(int day) {
   checkDate(this.year, this.month, day);
   this.day = day;
}
```

Her har vi laget en **checkDate**-metode for å sjekke alt på én gang, og alle endringsmetoder som endrer en av de tre variablene, kaller denne. Hvis **checkDate** utløser unntak, så vil ikke tilordningen rett under blir utført, siden unntaket gjør at også metoden som kalte **checkDate** avbrytes. Akkurat det kan være litt forvirrende, men det er slik unntak virker. Bare prøv og se!

**checkDate**-metoden validerer og utløser unntak for å si fra om feil. Det hender man i stedet velger å la valideringsmetoden returne **true** om det er greit og **false** ellers, og så utløse unntak i endringsmetoden:

```java
boolean isValidDate(int year, int month, int day) {
   if (month < 1 || month > 12) {
      return false;
   }
   int dayLimit = daysInMonth(month);
   if (month == 2 && isLeapYear(year)) {
      dayLimit = dayLimit + 1;
   }
   if (day < 1 || day > dayLimit) {
      return false;
   }
   return true;
}

void setYear(int year) {
   if (! isValidDate(year, this.month, this.day)) {
      throw new IllegalArgumentException("This combination of year (" + year + "), this.month (" + month + ") and day (" + this.day + ") is illegal");
   }
   this.year = year;
}

// tilsvarende for setMonth og setDay
```

Fordelen er bl.a. at det er litt lettere å se at endringsmetoden faktisk kan utløse unntak, men her ser en også at det er flere ulemper: Det blir mer duplisering av kode, og unntaksteksten blir mindre spesifikk, siden en ikke vet akkurat hvilken betingelse som ikke ble oppfylt. Generelt kan en ha én eller flere valideringsmetoder av en eller begge typer, en må vurdere hva som er mest praktisk i hvert tilfelle.

### Konstruktører

Hittil har vi fokusert vi på *endring* av tilstand, gitt at tilstanden *før* endringen var gyldig. Vi må selvsagt også sjekke at den initielle tilstanden, som settes ved opprettelsen, er gyldig. I vårt tilfelle kan det gjøres ved å initialisere **month**- og **day**-variablene til **1**, slik at et nyopprettet **Date**-objekt tilsvarer 1. januar i år 0. Her er det nok bedre å ha en konstruktør som tar inn alle de tre "tidskoordinatene", slik at de valideres og settes samlet:

```java
Date(int year, int month, int day) {
   checkDate(year, month, day);
   this.year = year;
   this.month = month;
   this.day = day;
}
```

En kan forsåvidt også argumentere med at det er like greit å ha en tilsvarende set-metode. Da blir koden som følger:

```java
Date(int year, int month, int day) {
   setDate(year, month, day);
}

void setDate(int year, int month, int day) {
   checkDate(year, month, day);
   this.year = year;
   this.month = month;
   this.day = day;
}
```

Med en slik sett-alt-på-en-gang-metode kan konstruktøren bare kalle den og få valideringen på kjøpet.

### public- og private-modifikatorene

Hvis en koder endringsmetoder som over, så hindrer en at tilstanden blir ugyldig, men bare dersom metodene må brukes! Anta f.eks. at vi har en variabel av typen **Date** i en annen klasse, la oss si den heter **birthday**, og så skriver **birthday.day = 47**. Da blir jo tilstanden gal, på tross av endringsmetodene. Uten en mekanisme for å hindre direkte tilgang til variablene, så er en nesten like langt.

Java sin mekanisme er såkalte *synlighetsmodifikatorer*, som styrer hvilke variabler og metoder i en klasse, som kan refereres direkte til i andre klasser. Synlighetsmodifikatorer er spesielord som settes foran deklarasjonen: **private** sier at en variabel, metode eller konstruktør *ikke kan referere (direkte) til* fra en annen klasse, mens **public** sier at de kan refereres direkte til fra **alle** klasser. *Ingen* modifikator betyr at bare klasser i samme pakke (som gjerne hører sammen) kan bruke variablen, metoden eller konstruktøren.

Den generelle regelen blir å bruke **private** foran alle variabeldeklarasjoner og **public** foran *utvalgte* konstruktører og metoder. Hjelpemetoder som **daysInMonth** får som regel **private**-synlighet, med mindre de er så nyttige for andre klasser at en velger å gjøre dem helt (**public**) eller delvis (ingen modifikator) synlige.

Det viktigste er uansett at **public**-konstruktørene må sikre at tilstanden er gyldig fra starten av, og **public**-metodene må sikre at tilstanden *forblir* gyldig.

### Lesemetoder

Med regelen over om at variabler skal være **private**, så blir de helt usynlige utenifra, og da må en som oftest også ha en del **public**-metoder for å lese tilstanden. Slike *lesemetoder* er metoder som skal gi mulighet til å observere tilstanden til (variablene i) et objekt. I en klasse som **Date** er det naturlig å ha én metode tilsvarende hver variabel:

```java
public int getYear() {
   return year;
}
	
public int getMonth() {
   return month;
}
	
public int getDay() {
   return day;
}
```

Konvensjonen i Java er å navngi slike enkle lesemetoder ved å sette "get" foran det logiske navnet på verdien en leser. Derfor kaller en gjerne slike metoder for "gettere". I tilfeller hvor verdien er av type **boolean** så kan "is" brukes som prefiks" i stedet, når det gjør koden mer naturlig å lese.

## API-er og intern tilstand

**public**-konstruktørene og -metodene til en klasse utgjør klassens *grensesnitt* mot andre klasser, det som også kalles klassens API, for Application Programming Interface. Det er behovene andre klasser har, som skal være styrende for utforming av konstruktører og metoder, ikke hva som er mest lettvint for klassen selv. Hvis det f.eks. er nyttig for andre klasser å kunne justere et **Date**-objekt til å referere til forrige eller neste dag, så kan en legge til metoder som **setToPreviousDay** og **setToNextDay**:

```java
public void setToPreviousDay() {
   day = day - 1;
   if (day < 1) {
      month = month - 1;
      if (month < 1) {
         year = year - 1;
         month = 12;
      }
      day = daysInMonth(year, month);
   }
}

public void setToNextDay() {
   day = day + 1;
   if (day > daysInMonth(year, month)) {
      month = month + 1;
      if (month > 12) {
         year = year + 1;
         month = 1;
      }
      day = 1;
   }	
}
```

Her er det også et poeng at det vil være vesentlig mer jobb å kode tilsvarende metodene utenfor **Date**-klassen (prøv!). Da er det greit å gjøre andre klasser den tjenesten å inkludere dem i **Date** sitt API. Slike metoder, som ikke er nødvendige, men gjør klassen enklere å bruke, kan gjerne kalles *bekvemmelighetsmetoder* (eng: convenience methods).

I motsetning til API-et, så utformes den interne tilstanden slik at klassen selv er lettvint å kode. Det er greit, for den er jo likevel ikke synlig for andre klasser. I en godt innkapslet klasse så står en friere til å velge intern representasjon og evt. endre den, uten at koden i andre klasser blir påvirket.

Vi kunne f.eks valgt å representere måneden som et tall i verdiområdet 0-11, selv om lesemetoden for måneden returnerer et tall i området 1-12 og endringsmetoden tilsvarende tar inn et tall i samme verdiområde. Det kunne vi gjort ved en enkel omkoding, uten å påvirke andre klasser. Det er slett ikke uvanlig å ha get-metoder og variabler som hører sammen, men hvor verdien til variablen og returverdien til get-metoden ikke er lik. Det vesentlige er at get- og set-metodene bruker samme logikk.

Det kan godt være større forskjell mellom metoder og intern representasjon enn som så. En kan f.eks. ha en metode som heter **isLeapYearDay** som returnerer **true** kun dersom **Date**-objektet representerer 29. februar. Da vil det ut fra navnet se ut som det finnes en **leapYearDat**-variabel av type **boolean**, men det naturlige vil være å *beregne* verdien fra de tre virkelige variablene.

Generelt kan en lage getter/setter-par som gir inntrykk av at objektet har en tilsvarende variable, uten at en slik nødvendigvis finnes. En slik "virtuell" variabel som leses/endres med getter/setter-metoder kalles ofte en "property", uavhengig av om den er reell eller ikke. Dette er noe av styrken til innkapsling: API-et er "fasaden" til objektet, som kan være nokså ulik innsiden.
