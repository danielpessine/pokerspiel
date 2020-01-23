import java.util.Arrays;
import java.util.Comparator;

public class Auswertung {
    // Mit diesem Array arbeitet Auswertung hauptsächlich
    public Karte[] gegKarten = new Karte[7];    // Im Main wird ein solches Objekt auswertung erzeugt

    /* Da Karte als short initialisiert wurde und mit Rängen sowie Farben im Array gearbeitet werden muss,
    *  werden entsprechende Zahlen als short benötigt*/
    public final static short EINS = 1;
    public final static short ZWEI = 2;
    public final static short DREI = 3;
    public final static short VIER = 4;

    public Auswertung(){
    }

    static class rangVergleich implements Comparator<Karte>{
        public int compare(Karte karte1, Karte karte2) throws ClassCastException{   /**ClassCastException ist Fehlerausgabe, wenn zwei nicht vergleichbare datentypen verglichen werden*/
            /**
             * Vergleicht die beiden übergebenen Objekte für Reihenfolge, gibt negativen Integer,
             * Null, oder einen positiven Integer -falls das erste Objekt weniger, genauso groß,
             * oder größer als das zweite ist, zurück*/
            if (!((karte1 instanceof Karte) && (karte2 instanceof Karte))){
                throw new ClassCastException("Was gibst du hier ein Yaa? Klasse par 1:" + karte1.getClass()
                        + " Lern Coden isch schwöre! Klasse par 2: " + karte2.getClass());
            }

            short rang1 = ((Karte)karte1).getRang();
            short rang2 = ((Karte)karte2).getRang();

            return rang1 - rang2;
        }
    }

    static class farbeVergleich implements Comparator<Karte>{
        /**
         * Vergleicht die beiden übergebenen Objekte für Reihenfolge, gibt negativen Integer,
         * Null, oder einen positiven Integer -falls das erste Objekt weniger, genauso groß,
         * oder größer als das zweite ist, zurück*/
        public int compare(Karte karte1, Karte karte2) throws ClassCastException{
            if (!((karte1 instanceof Karte) && (karte2 instanceof Karte))){
                throw new ClassCastException("Was gibst du hier ein Yaa? Klasse par 1:" + karte1.getClass()
                        + " Lern Coden isch schwöre! Klasse par 2: " + karte2.getClass());
            }

            short rang1 = ((Karte)karte1).getFarbe();
            short rang2 = ((Karte)karte2).getFarbe();

            return rang1 - rang2;
        }
    }

    // Methoden

    public void karteHinzufuegen(Karte karte, int i){

        gegKarten[i] = karte;
    }

    // Karten werden nach Rang (1-13) geordnet -automatisch absteigend.
    public void nachRangSort(){

        Arrays.sort(gegKarten, new rangVergleich());
    }

    // Karten werden zuerst nach Rang, dann nach Farbe sortiert
    public void rangFarbeSort(){

        Arrays.sort(gegKarten, new rangVergleich());
        Arrays.sort(gegKarten, new farbeVergleich());
    }

    // Für jeden Spieler läuft diese Auswertung genau 1 mal ab
    public String handAuswerten(){
        String auswertungErgebnis;
        short[] rangZaehler = new short[13];
        short[] farbenZaehler = new short[4];

        // RangZähler und FarbenZähler werden für alle übergebenen Karten(gegKarten) initialisiert
        for (Karte karte : gegKarten) { // Enhanced for
            rangZaehler[karte.getRang()]++;
            farbenZaehler[karte.getFarbe()]++;
        }

        // Oben initialisierte Karten werden für Auswertung sortiert
        this.rangFarbeSort();

        // Royal Flush, Straight Flush, Vierling, Flush
        auswertungErgebnis = auswertungRoyalFlush(rangZaehler, farbenZaehler);


        // Full House
        if (auswertungErgebnis == null || auswertungErgebnis.length() == 0){
            auswertungErgebnis = auswertungFullHouse(rangZaehler);
        }

        // Straight
        if (auswertungErgebnis == null || auswertungErgebnis.length() == 0){
            auswertungErgebnis = auswertungStraight(rangZaehler);
        }

        // Drilling
        if (auswertungErgebnis == null || auswertungErgebnis.length() == 0){
            auswertungErgebnis = auswertungDrilling(rangZaehler);
        }

        // Zwei Paare
        if (auswertungErgebnis == null || auswertungErgebnis.length() == 0){
            auswertungErgebnis = ausWertungZweiPaare(rangZaehler);
        }

        // Paar
        if (auswertungErgebnis == null || auswertungErgebnis.length() == 0){
            auswertungErgebnis = auswertungPaar(rangZaehler);
        }

        // High Card
        if (auswertungErgebnis == null || auswertungErgebnis.length() == 0){
            auswertungErgebnis = auswertungHighCard(rangZaehler);
        }

        return auswertungErgebnis;
    }

    public String auswertungRoyalFlush(short[] rangZaehler, short[] farbenZaehler) {
        String ergebnis = "";
        /* Sind mindestens 5 Karten von einer Farbe enthalten*/
        if (farbenZaehler[0] >= 5 || farbenZaehler[1] >= 5 || farbenZaehler[2] >= 5 ||
                farbenZaehler[3] >= 5) {
            /* Sind alle benötigten Karten vorhanden*/
            if (rangZaehler[9] >= 1 && rangZaehler[10] >= 1 && rangZaehler[11] >= 1 && rangZaehler[12] >= 1 && rangZaehler[0] >= 1) {
                /* Ass muss an 0, 1 oder 2 sein*/
                for (int i = 0; i < 3; i++) {
                    if (gegKarten[i].getRang() == 0) {
                        /* Nach Karten K - 10 wird gesucht*/
                        for (int j = 1; j < 4 - i; j++) {
                            /* Mit Einbezug der Position des Ass wird das Deck per Schleife nach der nötigen Kombination durchsucht*/
                            if ((gegKarten[i + j].getRang() == 9 &&
                                    gegKarten[i + j + 1].getRang() == 10 &&    // Verschiebung um +1
                                    gegKarten[i + j + 2].getRang() == 11 &&
                                    gegKarten[i + j + 3].getRang() == 12) &&
                                    /* Prüfung ob Ass selbe Farbe hat wie andere nach Rang geprüfte Karten*/
                                    (gegKarten[i].getFarbe() == gegKarten[i + j].getFarbe() &&
                                            gegKarten[i].getFarbe() == gegKarten[i + j + 1].getFarbe() &&
                                            gegKarten[i].getFarbe() == gegKarten[i + j + 2].getFarbe() &&
                                            gegKarten[i].getFarbe() == gegKarten[i + j + 3].getFarbe())) {
                                ergebnis = "Royal Flush";
                                break;

                            }
                        }
                    }
                }
            }
            /* Nach oberer Abfrage sind mindestens 5 Karten der gleichen Farbe enthalten, die höchstmögliche Option
             *  ist neben "Straight Flush" "Vierling" und "Flush".
             * Prüfung auf Vierling:*/
            else {
                for (int i = 0; i < rangZaehler.length; i++) {
                    if (rangZaehler[i] == VIER) {
                        ergebnis = "Vierling der Karten:" + Karte.rangAlsString(i);
                        break;
                    } else {
                        ergebnis = "Flush";
                        break;
                    }
                }
                for (int i = gegKarten.length - 1; i > 3; i--) {
                    if ((gegKarten[i].getRang() - EINS == gegKarten[i - EINS].getRang() &&
                            gegKarten[i].getRang() - ZWEI == gegKarten[i - ZWEI].getRang() &&
                            gegKarten[i].getRang() - DREI == gegKarten[i - DREI].getRang() &&
                            gegKarten[i].getRang() - VIER == gegKarten[i - VIER].getRang())
                            &&
                            (gegKarten[i].getFarbe() == gegKarten[i - EINS].getFarbe() &&
                                    gegKarten[i].getFarbe() == gegKarten[i - ZWEI].getFarbe() &&
                                    gegKarten[i].getFarbe() == gegKarten[i - DREI].getFarbe() &&
                                    gegKarten[i].getFarbe() == gegKarten[i - VIER].getFarbe())) {
                        ergebnis = "Straight Flush";
                        break;
                    }

                    else {
                        ergebnis = "Flush";
                    }
                }
            }
        }
        return ergebnis;
    }

    public String auswertungFullHouse(short[] rangZaehler){
        String ergebnis = "";
        short drilling = -1;
        short paar = -1;

        for (int i=rangZaehler.length;i>0;i--){
            if ((drilling < (short)0) || (paar < (short)0)){
                if ((rangZaehler[i-EINS]) > 2){
                    drilling = (short) (i-EINS);
                }
                else if ((rangZaehler[i-EINS]) > 1){
                    paar = (short)(i-EINS);
                }
            }
            else
            {
                break;
            }
        }

        if ((drilling >= (short)0) && (paar >= (short)0)){
            ergebnis = "Full House";
        }
        return ergebnis;
    }
}