# Versjon 3 av sjakk med JavaFX og FXML

I denne versjonen er hovedmålet å flytte mest mulig av sjakklogikken ut av kontrolleren, slik at det eneste som er igjen der er logikken for *koblingen* mellom GUI-et og sjakklogikken.
Sjakklogikken havner i en såkalt domeneklasse som vi kaller **Chess** (domenet her er sjakk), som skal være fri for GUI-spesifikk logikk. Et slikt tydelig skille mellom såkalt domenelogikk  og GUI gjør app-koden mye ryddigere og gir større mulighet for gjenbruk av domenelogikken i andre systemer.
