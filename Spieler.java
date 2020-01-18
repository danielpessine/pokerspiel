public class Spieler {
    public Karte[] handKarten = new Karte[2];   //Array für Karten wird erstellt

    public Spieler(){
    }
    // Karten werden benannt
    public void setKarte(Karte karte, int karteNum){
        handKarten[karteNum] = karte;
    }

    // Ausgabe der Handkarten, könnt ihr zu interner Ausgabe umbauen
    public void ausgabeSpielerKarten(int SpielerNummer){
        System.out.println("Spieler " + (SpielerNummer+1) + " Handkarten:");
        for (int i=0;i<2;i++){
            System.out.println(handKarten[i].karteAusgeben());
        }
    }

    //Methoden, die mal nützlich sein könnten, aber gerade keiner braucht
    public Karte getKarte(int karteNum){
        return handKarten[karteNum];
    }

    public int handKartenLaenge(){
        return handKarten.length;
    }

    public Spieler(Karte karte1, Karte karte2){
        handKarten[0] = karte1;
        handKarten[1] = karte2;
    }
}