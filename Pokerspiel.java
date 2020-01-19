// import java.io.IOException würden wir importieren, wenn wir exceptions bräuchten
import java.io.BufferedReader;  //Ermöglicht zeilenweises Auslesen von Text
import java.io.InputStreamReader;   //Einlesen von Daten

public class Pokerspiel {

    public static int getAnzahlSpieler() {
        int anzSpieler = 0;
        String BenutzerInput = "";

        // Abfrage Der Anzahl der Spieler in Konsole
        System.out.println("Eyy, wie viele Spieler?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        if ((anzSpieler<2) || (anzSpieler>9)){
            //Hier könnte Ihre Exception stehen
        }
        return anzSpieler;
    }

    public static void main(String[] args) {
        Deck holdemDeck = new Deck();   // Neues Deck wird erstellt
        int spielerzahl = 0;
        int kartenIterator = 0;
        int verbrIterator = 0;
        int oberflaechenIterator = 0;
        Oberflaeche tisch = new Oberflaeche();  // Platz für Flop bis River wird geschaffen

        // Initialisierung
        spielerzahl = getAnzahlSpieler();
        Spieler[] spieler = new Spieler[spielerzahl];

        /* Deck wird 100 Mal gemischt */
        for(int i=0;i<100;i++){
            holdemDeck.mischen();
        }

        // Objekte der Klasse Spieler werden nach Abfrage der Spielerzahl erzeugt -siehe Initialisierung
        for (int i=0;i<spielerzahl;i++){
            spieler[i] = new Spieler();
        }

        // Karten werden an Spieler verteilt
        for (int i=0;i<2;i++){
            for (int j=0;j<spielerzahl;j++){
                spieler[j].setKarte(holdemDeck.getKarte(kartenIterator++), i);  // Viel Spass, wenn ihr das konsekutiv machen wollt
            }
        }

        // Oberflaeche wird bearbeitet

        // Verbrennung vor Flop
        tisch.setVerbrKarte(holdemDeck.getKarte(kartenIterator++), verbrIterator++);

        // Flop
        for (int i=0; i<3;i++){
            tisch.setTischKarte(holdemDeck.getKarte(kartenIterator++), oberflaechenIterator++);
        }

        // Verbrennung vor Turn
        tisch.setVerbrKarte(holdemDeck.getKarte(kartenIterator++), verbrIterator++);

        // Turn
        tisch.setTischKarte(holdemDeck.getKarte(kartenIterator++), oberflaechenIterator++);

        // Verbrennung vor River
        tisch.setVerbrKarte(holdemDeck.getKarte(kartenIterator++), verbrIterator++);

        // River
        tisch.setTischKarte(holdemDeck.getKarte(kartenIterator++), oberflaechenIterator++);


        System.out.println("The sun comes by the River");

        //Tisch wird ausgegeben
        tisch.ausgabeOberflaeche();

        // Spielerkarten werden ausgegeben
        System.out.println("Zeig her was du hast");
        for (int i=0;i<spielerzahl;i++){
            spieler[i].ausgabeSpielerKarten(i);
        }
    }
}
