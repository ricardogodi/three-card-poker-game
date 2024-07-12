import java.util.ArrayList;

public class Dealer {
	
	private Deck theDeck;
	private ArrayList<Card> dealersHand;
	
	public Dealer() {
		theDeck = new Deck();
		dealersHand = new ArrayList<Card>();
	}
	
	public ArrayList<Card> dealHand() {
		
		if (theDeck.size() <= 34) {
			theDeck.newDeck();
		}
		
		ArrayList<Card> playersHand = new ArrayList<Card>();
		for (int i = 0; i < 3; i++) {
			Card nextCard = theDeck.remove(0); 
			playersHand.add(nextCard);
		}
		return playersHand;
	}
	
	public Deck getDeck() {
		return theDeck;
	}
	
	public void setDeck(Deck theDeck) {
		this.theDeck = theDeck;
	}
	
	public ArrayList<Card> getDealersHand() {
		return dealersHand;
	}
	
	public void setDealersHand(ArrayList<Card> dealersHand) {
		this.dealersHand = dealersHand;
	}
}
