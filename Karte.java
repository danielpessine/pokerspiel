import java.util.*;

public class Karte{
    public short rang, farbe;   // short weil wir sowieso keine 5 Stellen brauchen

    public static String[] raenge = {"Ass", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König"};
    public static String[] farben = {"Karo", "Kreuz", "Herz", "Piek"}; //Kann man später indexieren oder neu als Integer deklarieren

    //Konstruktor
    public Karte(short rang, short farbe){
        this.rang = rang;
        this.farbe = farbe;
    }

    // Getter und Setter
    public short getFarbe(){    // Wird nicht benutzt warum eigentlich?

        return farbe;
    }

    public short getRang(){

        return rang;
    }

    // Ein if als verlagerter Flex
    class RangVergleich implements Comparator<Karte>{
        public int compare(Karte karte1, Karte karte2) throws ClassCastException{   //ClassCastException ist Fehlerausgabe, wenn zwei nicht vergleichbare datentypen verglichen werden -weil ich es kann
            // Zum zweiten Mal Kontrolle für uns dpc´s
            if (!((karte1 instanceof Karte) && (karte2 instanceof Karte))){
                throw new ClassCastException("Was gibst du hier ein Yaa? Klasse par 1:" + karte1.getClass()
                        + " Lern Coden isch schwöre! Klasse par 2: " + karte2.getClass());
            }

            short rang1 = ((Karte)karte1).getRang();
            short rang2 = ((Karte)karte2).getRang();

            return rang1 - rang2;
        }
    }

    // Methoden
    public @Override String toString(){ //In any object-oriented programming language, Overriding is a feature that allows a subclass or child class to provide a specific implementation of a method that
                                        // is already provided by one of its super-classes or parent classes. When a method in a subclass has the same name, same parameters or signature and same return
                                        // type(or sub-type) as a method in its super-class, then the method in the subclass is said to override the method in the super-class.
        return rang + " " + farbe;
    }

    public static boolean gleicheKarte(Karte karte1, Karte karte2){         // Prüfung ob Karten gleich sind
        return (karte1.rang == karte2.rang && karte1.farbe == karte2.farbe); // Schon klar wie blöd das ist aber wir sichern ja nicht nur gegen dpu ab sondern auch gegen dpc(oder) -ich bin mit gemeint
    }

    // Methoden die mal nützlich sein könnten aber im Moment keiner braucht

    public static String rangAlsString(int rang){    // "rang" ist int für das String ausgegeben wird
        // auch "rang" bezeichnet weil statisch und keine Klassenvariabole
        // "AsString" gibt Objekt als String aus. Anders "toString"- es konvertiert ein Objekt zu einem Neuen
        return raenge[rang];
    }

    public static String farbeAlsString(int farbe){
        return farben[farbe];
    }   // denkts euch lol

    public String karteAusgeben() {
        return farben[farbe] + " " + raenge[rang];
    }
}
