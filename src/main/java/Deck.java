import java.util.ArrayList;
import java.util.Random;

public class Deck extends ArrayList<Card>{

	private final static char SUITS [] = {'C', 'D', 'S', 'H'};
	private final static int VALUES [] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	
	public Deck() {
		// Populate
		for(int suit = 0; suit < 4; suit++) {
			for(int value = 0; value < 13; value++) {
				add(new Card(SUITS[suit], VALUES[value]));
			}	
		}
		//Shuffle
		shuffle();
	}	
	
	public void newDeck() {
		
		clear();
		// Populate
		for(int suit = 0; suit < 4; suit++) {
			for(int value = 0; value < 13; value++) {
				add(new Card(SUITS[suit], VALUES[value]));
			}	
		}
		//Shuffle
		shuffle();
	}
	
	// Helper method Shuffle
	
	private void shuffle() {
		Random random = new Random();
		for (int i = size() - 1; i > 0; i--) {
			int j = random.nextInt(i);
			swapCards(i, j);
		}
	}
	
	private void swapCards(int i, int j) {
		Card temp = get(i);
		set(i, get(j));
		set(j, temp);
	}
}
