import java.util.Random;

public class Deck{
    public Karte[] deck = new Karte[52];
    static Random random = new Random();
    public Deck(){
        int i = 0;  // i wird für Zählung Initialisiert
        for (short j=0; j<4; j++){  // j ist short weil Speicheraufwand am geringsten und weil ich es kann. j zählt Farbe durch
            for (short k=0; k<13;k++){  // k zählt rang durch siehe Karte.raenge
                deck[i++] = new Karte(k, j);  // Keine 3 Schleifen da 2 von alleine aufhören. Iterationen sind abhängig von obigen Bedingungen, "i++" dient lediglich zum durchzählen
            }
        }
    }

    //Gibt Karte nach Nummer aus Deck aus
    public Karte getKarte(int karteNum){

        return deck[karteNum];
    }

    public void mischen(){
        int laenge = deck.length;
        for (int i=0;i<laenge;i++){
            int change = i + random.nextInt(laenge-i);
            kartenTauschen(i, change);
        }
    }

    // Karten werden zum Mischen getauscht
    public void kartenTauschen(int i, int change){
        Karte temp = deck[i];
        deck[i] = deck[change];
        deck[change] = temp;
    }

    // Test für uns, Deck wird ausgegeben
    public void deckAusgeben(){
        for(int i=0; i<deck.length;i++){
            System.out.print(i+1 + ": " + deck[i].karteAusgeben());
        }
    }

    // Ein weiteres Testtool für uns, falls jemand im gemischten Deck auf der Suche nach einer Karte sein sollte
    public int findeKarte(Karte karte){
        for (int i=0;i<52;i++){
            if (Karte.gleicheKarte(deck[i], karte)){
                return i;
            }
        }
        return -1;
    }
}
