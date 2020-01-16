
import java.util.Random;

public class Deck 
{
  
	public final int DECK_GROESSE = 52;	//Klassenvariable
	public final int MISCHEN_STUFEN = 1000;
	public final int HAND_GROESSE = 2;
	public final int FLOP_GROESSE = 3;
	
	Karte[] deck = new Karte[DECK_GROESSE];
	Random random = new Random();
	
	// Deck füllen
	public void fuellen()
	{
		int c = 0;
		for (int farbe = 1; farbe <= 4; farbe++)
		{
			for (int rang = 1; rang <= 13; rang++)
			{
				deck[c] = new Karte();
				deck[c].farbe = farbe;
				deck[c].rang = rang;
				c++;
			}
		}
	}
	
	// Deck mischen
	public void mischen()
	{
		for (int x = 0; x <= MISCHEN_STUFEN; x++)
		{
			int versch1 = random.nextInt(DECK_GROESSE);
			int versch2 = random.nextInt(DECK_GROESSE);
			Karte temp = deck[versch1];
			deck[versch1] = deck[versch2];
			deck[versch2] = temp;
		}
	}
	
	// Karten dealen 
	public Karte[] deal()
	{
		Karte[] hand = new Karte[HAND_GROESSE];
		for (int deckPosition = 0; deckPosition < 2; deckPosition++)
		{
			hand[deckPosition] = deck[deckPosition];
		}
		return hand;
	}
	
	// Flop auslegen
	public Karte[] flop()
	{
		Karte[] flop = new Karte[FLOP_GROESSE];
		for (int deckPosition = 0; deckPosition < 3; deckPosition++)
		{
			flop[deckPosition] = deck[deckPosition];
		}
		return flop;
	}

}
