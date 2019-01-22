# Time interval

Dette eksemplet bygger på [encapsulation.TimeInterval](../../encapsulation/timeinterval/TimeInterval.md) og viser hvordan struktur av objekter fungerer for å representere et tidsintervall.

Vi skal her se på en fjerde (!) måte å representere tidsintervallet på, ved bruk av en hjelpeklasse for tidspunkt. I stedet for ett objekt for å representere et konkret tidsintervall så må en nå bruke tre. API-et, altså de offentlige metodene, skal være de samme.

## Koden

I denne varianten så representeres tidsintervallet med to variabler som refererer til to **TimePoint**-objekter:

```java
private TimePoint start;
private TimePoint end;
```

**TimePoint** er en enkel innkapsling av et tidspunkt representert med time og minutt. Koden under følger samme mønster som den for **TimeInterval**, først variabeldeklarasjonene, så en konstruktør som tar inn initielle verdier for begge variablene, **toString**-metoden, **get**- og **set**-metoder og hjelpemetode(r) for validering.

```java
public class TimePoint {

   private int hour;
   private int min;

   public TimePoint(int hour, int min) {
      checkInt(hour, 0, 24);
      checkInt(min, 0, 60);
      this.hour = hour;
      this.min = min;
   }

   @Override
   public String toString() {
      return String.format("[TimePoint %02d:%02d]", getHour(), getMinutes());
   }
	
   public int getHour() {
      return hour;
   }

   // hjelpemetode for å sjekke om et tall er i riktig intervall
   private void checkInt(int i, int min, int max) {
      if (i < min || i >= max) {
         throw new IllegalArgumentException(String.format("%d isn't between %d (inclusive) and %d (exclusive)", i, min, max));
      }
   }

   public void setHour(int hour) {
      checkInt(hour, 0, 24);
      this.hour = hour;
   }

   public int getMinutes() {
      return min;
   }

   public void setMinutes(int minutes) {
      checkInt(minutes, 0, 60);
      min = minutes;
   }
}
```

I praksis betyr dette at hele tilstanden for tidsintervallet er fordelt på tre objekter, to **TimePoint**-objekter holder på henholdsvis start- og slutt-tidspunktet og ett **TimeInterval**-objekt refererer til disse:

![Struktur av objekter](TimeInterval-object.png)

Koden for alle TimeInterval-metodene er nokså lik tilsvarende for den første varianten (**encapsulation.TimeInterval1**), en bare passer på å lese/endre **hour**- og **min**-variablene i **start**- og **end**-objektene i stedet for **startHour**, **startMin**, **endHour** og **endMin**. Her er f.eks. koden for getStartHour og setStartHour:

```java
public int getStartHour() {
   return start.getHour();
}

public void setStartHour(int hour) {
   checkInt(hour, 0, 24);
   // husk den gamle intervall-lengden
   int intervalLength = getIntervalLength();
   // sjekk om den nye kombinasjon av start og lengde er gyldig
   checkInt(intervalLength, 0, 24 * 60 - minutes(hour, start.getMinutes()));

   start.setHour(hour);
   // juster endHour og endMin vha. setIntervalLength
   setIntervalLength(intervalLength);;
}
```

Totalt sett har de tre objektene i strukturen akkurat de samme variablene som det ene **TimeInterval1**-objektet, hvis en ser bort fra variablene som håndterer selve strukturen. 

### Testing med main-metoden

**main**-metoden som brukes for testing, er også som før: 

```java
public static void main(String[] args) {
   TimeInterval ti = new TimeInterval(12, 15, 14, 0);
   System.out.println(ti);
   ti.setStartHour(14);
   System.out.println(ti);
   ti.setStartMinutes(0);
   System.out.println(ti);
   try {
      ti.setStartHour(23);
      System.out.println("Forventet feil ble ikke fanget opp");
   } catch (IllegalArgumentException e) {
      System.out.println("Forventet feil ble fanget opp");
   }
   System.out.println(ti);
}
```

Sekvensen er lik, men hvert trinn utgjøres av tre objekter:

![Sekvens av struktur av objekter](TimeInterval-object-states.png)

Vi ser av id-en til hver objekt-boks hvilke objekter som logisk sett er de samme i hvert trinn.
 