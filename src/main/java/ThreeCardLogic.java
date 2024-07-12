import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {
	
	// Returns:
	// Straight Flush -> 1
	// Three of a Kind -> 2
	// Straight -> 3
	// Flush -> 4
	// Pair -> 5
	// High Card -> 0
	public static int evalHand(ArrayList<Card> hand) {
		
		Collections.sort(hand);

		// Check if hand is Straight Flush
		if(isStraightFlush(hand)) {
			return 1;
		}
		
		if(isThreeOfAKind(hand)) {
			return 2;
		}
		
		if(isStraight(hand)) {  
			return 3;
		}
		
		if(isFlush(hand)) {
			return 4;
		}
		
		if(isPair(hand)) {
			return 5;
		}
		
		return 0;
	}
	
	public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
		
		int evalHand = evalHand(hand);
		
		if (evalHand == 1) {  // Straight Flush
			return 40 * bet;
		} else if(evalHand == 2) {	// Three of a Kind
			return 30 * bet;
		} else if(evalHand == 3) {  // Straight
			return 6 * bet;
		} else if(evalHand == 4) {  // Flush
			return 3 * bet;
		} else if(evalHand == 5) {  // Pair
			return bet;
		} else {
			return 0;
		}
	}
	
	// 
	// Returns 0 if both hands are tied.
	// Returns 1 if DEALER wins.
	// Returns 2 if PLAYER wins.
	//
	// Hierarchy of values: 1 > 2 > 3 > 4 > 5 > 0
	// Straight Flush > Three of a Kind > Straight > Flush > Pair > High Card
	//
	public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
		
		int dealersValue = evalHand(dealer);
		int playersValue = evalHand(player);
		
		int dealersHigh = dealer.get(dealer.size() - 1).getValue(); // Get highest card
		int playersHigh = player.get(player.size() - 1).getValue(); 
		
		if (dealersValue == 1) { // Dealer has Straight Flush
			if (playersValue == 1) {	// Player has also a Straight Flush
				return tieBreaker(dealersHigh, playersHigh);
			} else {
				return 1; // No hand beats a Straight Flush other than another Straight Flush with a higher card
			}
		}
		
		if (dealersValue == 2) {	// Dealer has Three of a Kind
		//	System.out.println("Dealer has THREE OF A KIND");
			if (playersValue == 1) {	// But player has Straight Flush
				//System.out.println("But Player has Straight Flush\n");
				return 2;
			} else if (playersValue == 2) { // Player also has Three of a Kind
				//System.out.println("Player also has Three of a Kind\n");
				return tieBreaker(dealersHigh, playersHigh);
			} else {
				//System.out.println("Player has either Straight, Flush, Pair or High Card. RETURN 1\n");
				return 1; // No hand beats a Three of a Kind other than Straight Flush or a Three of a Kind with a higher card
			}
		}
		
		if (dealersValue == 3) {	// Dealer has Straight
			if (playersValue == 1 || playersValue == 2) {	// But player has a Straight Flush or a Three of a Kind
				return 2;
			} else if (playersValue == 3) { // Player also has Straight
				return tieBreaker(dealersHigh, playersHigh);
			} else {
				return 1; // No other hand beats a Straight other than Straight Flush, a Three of a Kind or another
						  // Straight with a higher card
			}
		}
		
		if (dealersValue == 4) {		// Dealer has a Flush
			// But player has a Straight Flush, a Three of a Kind, or a Straight
			if (playersValue == 1 || playersValue == 2 || playersValue == 3) {
				return 2;
			} else if (playersValue == 4) {  // Player also has Flush
				return tieBreaker(dealersHigh, playersHigh);
			} else { 
				return 1;  // No other hand beats a Flush other than Straight Flush, a Three of a Kind, a Straight or another
				  // Flush with a higher card
			}
		}
		
		if (dealersValue == 5) {		// Dealer has a Pair
			// But player has a Straight Flush, a Three of a Kind, a Straight, or a Flush
			if (playersValue == 1 || playersValue == 2 || playersValue == 3 || playersValue == 4) {
				return 2;
			} else if (dealersValue == 5) {	// Player also has a Pair
				//System.out.println("Both have pair");
				return tieBreakerForPairs(dealer, player);  // MUST FIND THE PAIRS FIRST IN ORDER TO COMPARE HIGHS!!!
			} else {
				return 1; // No other hand beats a Flush other than Straight Flush, a Three of a Kind, a Straight, a Flush
				  // or another Pair with a higher card
			}	
		}
		
		//System.out.println("DEALER HAS A HIGH\n");
		
		
		// If we reach here, dealersValue must be 0, so it has a High Card
		if (dealersHigh < 12) {  // Check first if dealers high is at least a Queen
			return 0;		// Dealer cannot play the hand
		} else {	// Dealer has Q+ (12,13 or 14), so he can play.
			
			// Check if player has a better hand
			if(playersValue == 1 ||playersValue == 2 || playersValue == 3 ||playersValue == 4 || playersValue == 5) {
				return 2;
			}
			// If we reach here, player does not have a better hand, so he must have a high
			if (dealersHigh > playersHigh) { // Compare high cards
				return 1;
			} else if (dealersHigh < playersHigh) {
				return 2;
			} else {
				return 0;  // Tied at, 12, 13 or 14.
			}
		}
	}	
	
	/*
	 * Helper methods are beyond this point downwards.
	 */
	
	//
	//	Returns 0 if no one wins, 1 if Dealer wins, 2 if Player wins
	//  This method assumes cards are sorted AND BOTH HAVE AT LEAST ONE PAIR!
	//
	private static int tieBreakerForPairs(ArrayList<Card> dealersHand, ArrayList<Card> playersHand) {
		
		int dealersHigh = 0;
		
		int playersHigh = 0;
		
		int curValue;
		int nextValue;
		
		int handSize = dealersHand.size();
		
		
		// First find the pairs
		// We know the cards are sorted. So we should traverse from high to low to 
	
		for(int i = handSize - 1; i > 0 ; i--) {
			curValue = dealersHand.get(i).getValue();
			nextValue = dealersHand.get(i-1).getValue();
			
			if(curValue == nextValue) { // FOUND DEALER'S HIGHEST PAIR
				dealersHigh = curValue;
				break;
			}
		}
		
		
		for(int i = handSize - 1; i > 0 ; i--) {
			curValue = playersHand.get(i).getValue();
			nextValue = playersHand.get(i-1).getValue();
			
			if(curValue == nextValue) { // FOUND PLAYER'S HIGHEST PAIR
				playersHigh = curValue;
				break;
			}
		}
		
		return tieBreaker(dealersHigh, playersHigh);
	}
	
	
	//
	//	Returns 0 if no one wins, 1 if Dealer wins, 2 if Player wins
	// 
	private static int tieBreaker(int dealersHigh, int playersHigh) {
		if (dealersHigh > playersHigh) {	
			return 1;							// Dealer wins
		} else if (dealersHigh < playersHigh){
			return 2;							// Player wins
		} else {	// dealersHigh == playersHigh
			return 0;
		}
	}
	
	
	private static boolean isStraightFlush(ArrayList<Card> hand) {
		if(hasSameSuits(hand) && isConsecutive(hand)) {
			return true;
		}
		return false;
	}
	
	private static boolean isThreeOfAKind(ArrayList<Card> hand) {
		int curValue;
		int nextValue;

		int handSize = hand.size();

		for(int i = 0; i < handSize - 1; i++) {
			curValue = hand.get(i).getValue();
			nextValue = hand.get(i+1).getValue();
			
			if (curValue != nextValue) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isStraight(ArrayList<Card> hand) {
		if(isConsecutive(hand) && !hasSameSuits(hand)) {
			return true;
		}
		return false;
	}
	
	private static boolean isFlush(ArrayList<Card> hand) {
		if(!isConsecutive(hand) && hasSameSuits(hand)) {
			return true;
		}
		return false;
	}
	
	private static boolean isPair(ArrayList<Card> hand) {
		
		int curValue;
		int nextValue;
		
		int handSize = hand.size();
		int numPairs = 0;
		
		for(int i = 0; i < handSize; i++) {
			curValue = hand.get(i).getValue();
			for(int j = i + 1; j < handSize ; j++) {
				nextValue = hand.get(j).getValue();
				
				if(curValue == nextValue){
					numPairs++;
				}
			}
		}
		if(numPairs == 1) { // we found exactly one pair
			return true;
		} 
	
		return false;
	}
	
	private static boolean hasSameSuits(ArrayList<Card> hand) {
		// Check if all cards are of the same suit
		char curSuit;
		char nextSuit;
		
		int handSize = hand.size();

		for(int i = 0; i < handSize - 1; i++) {	
			curSuit = hand.get(i).getSuit();
			nextSuit = hand.get(i+1).getSuit();
			if(curSuit != nextSuit) {  // at least one pair of suits are not equal. Return false immediately.
				return false; 
			}
		}
		return true;
	}
	
	/*
	 * Assumption: hand must be sorted in increasing order based on value attribute
	 */
	private static boolean isConsecutive(ArrayList<Card> hand) {
		int curValue;
		int nextValue;
		
		int handSize = hand.size();

		for(int i = 0; i < handSize - 1; i++) {
			curValue = hand.get(i).getValue();
			nextValue = hand.get(i+1).getValue();
			
			if(nextValue != (curValue + 1)) {
				return false; 
			}
		}
		return true;
	}
}