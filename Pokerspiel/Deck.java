
import java.util.Random;

public class Deck 
{
  
	public final int DECK_GROESSE = 52;	//Klassenvariable
	public final int MISCHEN_STUFEN = 1000;
	public final int HAND_GROESSE = 2;
	public final int FLOP_GROESSE = 3;
	
	Card[] deck = new Card[DECK_GROESSE];
	Random random = new Random();
	
	// Deck füllen
	public void fuellen()
	{
		int c = 0;
		for (int suit = 1; suit <= 4; suit++)
		{
			for (int rank = 1; rank <= 13; rank++)
			{
				deck[c] = new Card();
				deck[c].suit = suit;
				deck[c].rank = rank;
				c++;
			}
		}
	}
	
	// Deck mischen
	public void mischen()
	{
		for (int x = 0; x <= MISCHEN_STUFEN; x++)
		{
			int number1 = random.nextInt(DECK_GROESSE);
			int number2 = random.nextInt(DECK_GROESSE);
			Card temp = deck[number1];
			deck[number1] = deck[number2];
			deck[number2] = temp;
		}
	}
	
	// Karten dealen 
	public Card[] deal()
	{
		Card[] hand = new Card[HAND_GROESSE];
		for (int deckPosition = 0; deckPosition < 2; deckPosition++)
		{
			hand[deckPosition] = deck[deckPosition];
		}
		return hand;
	}
	
	// Flop auslegen
	public Card[] flop()
	{
		Card[] flop = new Card[FLOP_GROESSE];
		for (int deckPosition = 0; deckPosition < 3; deckPosition++)
		{
			flop[deckPosition] = deck[deckPosition];
		}
		return flop;
	}

}