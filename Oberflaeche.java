public class Oberflaeche {
    public Karte[] oberflaeche = new Karte[5]; //Flop + Turn + River
    public Karte[] verbrKarten = new Karte[3];  //Zu verbrennende Karten

    //Konstruktor
    public Oberflaeche(){

    }

    public void setTischKarte(Karte karte, int karteNum){

        this.oberflaeche[karteNum] = karte;
    }

    public Karte getTischKarte(int karteNum){

        return this.oberflaeche[karteNum];
    }

    public void setVerbrKarte(Karte card, int karteNum){
        this.verbrKarten[karteNum] = card;
    }

    public Karte getVerbrKarte(int karteNum){

        return this.verbrKarten[karteNum];
    }

    public void ausgabeOberflaeche(){
        System.out.println("Auf der Oberflaeche befinden sich folgende Karten:");
        for(int i =0; i<oberflaeche.length;i++){
            System.out.println(i+1 + ": " + getTischKarte(i).karteAusgeben());
        }
    }

    //Methoden die mal nützlich sein könnten aber im Moment keiner braucht
    public void ausgabeVerbrKarten(){   // Damit wir auch das prüfen können
        System.out.println("The burn cards are:");
        for(int i =0; i<verbrKarten.length;i++){
            System.out.println(i+1 + ": " + getVerbrKarte(i).karteAusgeben());
        }
    }

    public int oberflaecheGroesse(){

        return oberflaeche.length;
    }

}