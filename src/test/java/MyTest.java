import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;



class MyTest {

	Deck myDeck;
	Dealer theDealer;

	@BeforeEach
	void init() {
		myDeck = new Deck();
	}

	/*
	 * Deck Class Tests
	 */
	@Test
	void deckConstructorTest() {
		assertEquals(52, myDeck.size(), "wrong deck size");
	}	

	@Test
	void newDeckTest() {
		//remove first two cards
		myDeck.remove(0);
		myDeck.remove(0);
		assertEquals(50, myDeck.size(), "wrong deck size");
		myDeck.newDeck(); 
		assertEquals(52, myDeck.size(), "wrong deck size");
	}	

	/*
	 * Dealer Class Tests
	 */
	@Test
	void dealerConstructorTest() {
		theDealer = new Dealer();
		Deck theDeck = theDealer.getDeck();
		assertEquals(52, theDeck.size(), "wrong deck size");
	}	

	// Test dealHand
	@Test
	void dealHandTest() {
		theDealer = new Dealer();
		ArrayList<Card> hand = theDealer.dealHand();

		// At the beginning, the deck has 52 cards.
		// Dealing a had must decrease its size by 3.
		assertEquals(49, theDealer.getDeck().size(), "Deck size is wrong after dealing hand");
	}	

	@Test
	void dealHandTest2() {
		theDealer = new Dealer();
		ArrayList<Card> hand1 = theDealer.dealHand();
		ArrayList<Card> hand2 = theDealer.dealHand();
		// At the beginning, the deck has 52 cards.
		// Dealing a had must decrease its size by 3.
		assertEquals(46, theDealer.getDeck().size(), "Deck size is wrong after dealing hand");
	}	

	/*
	 * 	Card Class Tests
	 */
	@Test
	void cardComparisonTest() {
		Card card1 = new Card('H', 1);
		Card card2 = new Card('C', 2);

		assertEquals(-1, card1.compareTo(card2));
	}

	@Test
	void cardComparisonTest2() {
		Card card1 = new Card('H', 1);
		Card card2 = new Card('C', 2);

		assertEquals(1, card2.compareTo(card1));
	}

	@Test
	void cardComparisonTest3() {
		Card card1 = new Card('H', 1);
		Card card2 = new Card('C', 1);

		assertEquals(0, card1.compareTo(card2));
	}

	@Test
	void sortCards() {

		System.out.println("Sorting...");
		Collections.sort(myDeck);

		for(int i = 0; i < myDeck.size(); i++) {
			int value = myDeck.get(i).getValue();
			char suit = myDeck.get(i).getSuit();
			System.out.println(value + " " + suit);
		}
	}



	/*
	 * ThreeCardPokerGame Tests
	 */


	/*
	 * Private methods tests
	 */
	/*
	@Test
	void isConsecutiveTest1() {
		Card card1 = new Card('H',4);
		Card card2 = new Card('D',5);
		Card card3 = new Card('S',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertTrue(ThreeCardLogic.isConsecutive(hand));		

	}

	@Test
	void isConsecutiveTest2() {
		Card card1 = new Card('H',4);
		Card card2 = new Card('D',5);
		Card card3 = new Card('S',7);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertFalse(ThreeCardLogic.isConsecutive(hand));		

	}

	@Test
	void isConsecutiveTest3() {
		Card card1 = new Card('H',4);	
		Card card2 = new Card('D',3);
		Card card3 = new Card('S',2);

		ArrayList<Card> hand = new ArrayList<Card>();
		// This is a consecutive card but the isConsecutiveMethod assumes hand is sorted in increasing order
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertFalse(ThreeCardLogic.isConsecutive(hand));				
	}

	@Test
	void isConsecutiveTest4() {
		Card card1 = new Card('H',4);
		Card card2 = new Card('D',4);
		Card card3 = new Card('S',4);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertFalse(ThreeCardLogic.isConsecutive(hand));				
	}

	@Test
	void hasSameSuits() {
		Card card1 = new Card('H',4);
		Card card2 = new Card('H',5);
		Card card3 = new Card('H',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertTrue(ThreeCardLogic.hasSameSuits(hand));				
	}

	@Test
	void hasSameSuits2() {
		Card card1 = new Card('H',4);
		Card card2 = new Card('H',5);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertFalse(ThreeCardLogic.hasSameSuits(hand));				
	}

	@Test
	void hasSameSuits3() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',3);
		Card card3 = new Card('D',3);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertTrue(ThreeCardLogic.hasSameSuits(hand));				
	}

	 */

	@Test
	void isStraightFlush() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',5);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertEquals(1, ThreeCardLogic.evalHand(hand),"Not a straight flush");				
	}

	@Test
	void isStraightFlush2() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('H',5);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(1, ThreeCardLogic.evalHand(hand),"Should be a Straight");				
	}

	@Test
	void isStraightFlush3() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',7);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(1, ThreeCardLogic.evalHand(hand),"Should be a flush");				
	}




	@Test
	void isThreeOfAKind() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('S',4);
		Card card3 = new Card('H',4);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertEquals(2, ThreeCardLogic.evalHand(hand), "Not a three of a kind");				
	}

	@Test
	void isStraight() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('H',5);
		Card card3 = new Card('S',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertEquals(3, ThreeCardLogic.evalHand(hand),"Not a straight flush");				
	}

	@Test
	void isStraight2() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',5);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(3, ThreeCardLogic.evalHand(hand),"Should be a straight flush");				
	}

	@Test
	void isFlush() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',10);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertEquals(4, ThreeCardLogic.evalHand(hand),"Not a flush");				
	}

	@Test
	void isFlush2() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',5);
		Card card3 = new Card('D',6);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(4, ThreeCardLogic.evalHand(hand),"Should be a straightFlush");				
	}

	@Test
	void isAPair() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',10);
		Card card3 = new Card('H',4);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertEquals(5, ThreeCardLogic.evalHand(hand),"Not a pair");				
	}


	@Test
	void isAPair2() {
		Card card1 = new Card('D',4);
		Card card2 = new Card('D',4);
		Card card3 = new Card('D',4);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(5, ThreeCardLogic.evalHand(hand),"Not a pair");				
	}

	@Test
	void isAPair3() {
		Card card1 = new Card('D',5);
		Card card2 = new Card('D',2);
		Card card3 = new Card('H',3);
		Card card4 = new Card('D',9);
		Card card5 = new Card('D',10);
		Card card6 = new Card('H',3);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);

		assertEquals(5, ThreeCardLogic.evalHand(hand),"Not a pair");				
	}

	@Test
	void isAPair4() {
		Card card1 = new Card('D',5);
		Card card2 = new Card('D',2);
		Card card3 = new Card('H',3);
		Card card4 = new Card('D',5);
		Card card5 = new Card('D',10);
		Card card6 = new Card('H',3);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);

		assertNotEquals(5, ThreeCardLogic.evalHand(hand),"More than one pairs");				
	}

	@Test
	void isAHigh() {
		Card card1 = new Card('D',5);
		Card card2 = new Card('D',2);
		Card card3 = new Card('H',3);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertEquals(0, ThreeCardLogic.evalHand(hand));				
	}

	@Test
	void isAHigh2() {
		Card card1 = new Card('D',5);
		Card card2 = new Card('D',2);
		Card card3 = new Card('D',3);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(0, ThreeCardLogic.evalHand(hand));				
	}


	@Test
	void isAHigh3() {
		Card card1 = new Card('D',5);
		Card card2 = new Card('H',6);
		Card card3 = new Card('S',7);

		ArrayList<Card> hand = new ArrayList<Card>();

		hand.add(card1);
		hand.add(card2);
		hand.add(card3);

		assertNotEquals(0, ThreeCardLogic.evalHand(hand));				
	}



	// Straight Flush vs Straight flush. Dealer wins.
	@Test
	void compareHandsTest() {

		// Dealers hand
		Card card1 = new Card('H',5);		// Straight flush
		Card card2 = new Card('H',7);
		Card card3 = new Card('H',6);

		// playersHand
		Card card4 = new Card('D',4);		// Straight flush
		Card card5 = new Card('D',2);
		Card card6 = new Card('D',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);


		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight Flush vs Straight flush. Tie.
	@Test
	void compareHandsTest2() {

		// Dealers hand
		Card card1 = new Card('H',2);		// Straight flush
		Card card2 = new Card('H',3);
		Card card3 = new Card('H',4);

		// playersHand
		Card card4 = new Card('D',4);		// Straight flush
		Card card5 = new Card('D',2);
		Card card6 = new Card('D',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Should have been a tie");				
	}

	// Straight Flush vs Straight flush. Player wins.
	@Test
	void compareHandsTest3() {

		// Dealers hand
		Card card1 = new Card('H',5);		// Straight flush
		Card card2 = new Card('H',7);
		Card card3 = new Card('H',6);

		// playersHand
		Card card4 = new Card('D',11);		// Straight flush
		Card card5 = new Card('D',9);
		Card card6 = new Card('D',10);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Straight Flush vs Three of a Kind. Dealer wins
	@Test
	void compareHandsTest4() {

		// Dealers hand
		Card card1 = new Card('H',5);		// Straight flush
		Card card2 = new Card('H',7);
		Card card3 = new Card('H',6);

		// playersHand
		Card card4 = new Card('D',11);		// Three of a Kind
		Card card5 = new Card('H',11);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight Flush vs Three of a Kind. Player Wins.
	@Test
	void compareHandsTest5() {

		// Dealers hand
		Card card1 = new Card('C',5);		// Three of a Kind
		Card card2 = new Card('D',5);
		Card card3 = new Card('S',5);

		// playersHand
		Card card4 = new Card('D',4);		// Straight flush
		Card card5 = new Card('D',6);
		Card card6 = new Card('D',5);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}



	// Straight Flush vs Straight. Dealer Wins.
	@Test
	void compareHandsTest6() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Straight flush
		Card card2 = new Card('C',3);
		Card card3 = new Card('C',4);

		// playersHand
		Card card4 = new Card('C',4);		// Straight
		Card card5 = new Card('D',6);
		Card card6 = new Card('C',5);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// Straight Flush vs Straight. Player Wins.
	@Test
	void compareHandsTest7() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Straight
		Card card2 = new Card('D',3);
		Card card3 = new Card('H',4);

		// playersHand
		Card card4 = new Card('C',1);		// Straight flush
		Card card5 = new Card('C',2);
		Card card6 = new Card('C',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}



	// Straight Flush vs Flush. Dealer Wins
	@Test
	void compareHandsTest8() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Straight flush
		Card card2 = new Card('C',3);
		Card card3 = new Card('C',4);

		// playersHand
		Card card4 = new Card('C',1);		// Flush
		Card card5 = new Card('C',10);
		Card card6 = new Card('C',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight Flush vs Flush. Player Wins.
	@Test
	void compareHandsTest9() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Flush
		Card card2 = new Card('C',7);
		Card card3 = new Card('C',7);

		// playersHand
		Card card4 = new Card('C',10);		// Straight flush
		Card card5 = new Card('C',11);
		Card card6 = new Card('C',12);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Straight Flush vs Pair. Dealer Wins.
	@Test
	void compareHandsTest10() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Straight flush
		Card card2 = new Card('C',3);
		Card card3 = new Card('C',4);

		// playersHand
		Card card4 = new Card('S',10);		// Pair
		Card card5 = new Card('C',11);
		Card card6 = new Card('H',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// Straight Flush vs Pair. Player Wins.
	@Test
	void compareHandsTest11() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Pair
		Card card2 = new Card('S',2);
		Card card3 = new Card('C',4);

		// playersHand
		Card card4 = new Card('S',10);		// Straight flush
		Card card5 = new Card('S',11);
		Card card6 = new Card('S',12);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}



	// Straight Flush vs High Card. Dealer Wins.
	@Test
	void compareHandsTest12() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Straight flush
		Card card2 = new Card('C',3);
		Card card3 = new Card('C',4);

		// playersHand
		Card card4 = new Card('S',10);		// High Card
		Card card5 = new Card('C',8);
		Card card6 = new Card('S',12);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// Straight Flush vs High Card. Player Wins.
	@Test
	void compareHandsTest13() {

		// Dealers hand
		Card card1 = new Card('C',2);		// High Card
		Card card2 = new Card('C',3);
		Card card3 = new Card('C',12);

		// playersHand
		Card card4 = new Card('S',10);		// Straight flush
		Card card5 = new Card('S',11);
		Card card6 = new Card('S',12);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Straight Flush vs High Card. Dealer cannot play because he does not have at least a 12.
	@Test
	void compareHandsTestDealerCantPlay() {

		// Dealers hand
		Card card1 = new Card('C',2);		// High Card
		Card card2 = new Card('H',3);
		Card card3 = new Card('S',11);

		// playersHand
		Card card4 = new Card('S',10);		// Straight flush
		Card card5 = new Card('S',11);
		Card card6 = new Card('S',12);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer cannot play.");				
	}


	// Three of a Kind vs Three of a Kind. Dealer Wins.
	@Test
	void compareHandsTest14() {

		// Dealers hand
		Card card1 = new Card('C',3);		// Three of a Kind
		Card card2 = new Card('S',3);
		Card card3 = new Card('H',3);

		// playersHand
		Card card4 = new Card('S',2);		// Three of a Kind
		Card card5 = new Card('D',2);
		Card card6 = new Card('C',2);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Three of a Kind vs Three of a Kind. Tie. 
	// NOTE: THERE CANNONT BE A TIE BECAUSE THERE ARE NO REPEATED CARDS IN A DECK.
	@Test
	void compareHandsTest15() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Three of a Kind
		Card card2 = new Card('S',2);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',2);		// Three of a Kind
		Card card5 = new Card('D',2);
		Card card6 = new Card('C',2);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Should be a tie");				
	}

	// Three of a Kind vs Three of a Kind. Player wins.
	@Test
	void compareHandsTest16() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Three of a Kind
		Card card2 = new Card('S',2);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',3);		// Three of a Kind
		Card card5 = new Card('D',3);
		Card card6 = new Card('C',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Three of a Kind vs Straight. Dealer wins.

	@Test
	void compareHandsTest17() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Three of a Kind
		Card card2 = new Card('S',2);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',4);		// Straight
		Card card5 = new Card('D',5);
		Card card6 = new Card('C',6);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Three of a Kind vs Straight. Player wins.

	@Test
	void compareHandsTest18() {

		// Dealers hand
		Card card1 = new Card('C',2);		// Straight
		Card card2 = new Card('S',3);
		Card card3 = new Card('H',4);

		// playersHand
		Card card4 = new Card('S',4);		// Three of a Kind
		Card card5 = new Card('D',4);
		Card card6 = new Card('C',4);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Three of a Kind vs Flush. Dealer Wins.

	@Test
	void compareHandsTest19() {

		// Dealers hand
		Card card1 = new Card('C',12);		// Three of a Kind
		Card card2 = new Card('S',12);
		Card card3 = new Card('H',12);

		// playersHand
		Card card4 = new Card('S',9);		// Flush
		Card card5 = new Card('S',12);
		Card card6 = new Card('S',4);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Three of a Kind vs Flush. Player Wins.

	@Test
	void compareHandsTest20() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Flush
		Card card2 = new Card('H',7);
		Card card3 = new Card('H',12);

		// playersHand
		Card card4 = new Card('S',9);		// Three of a Kind
		Card card5 = new Card('H',9);
		Card card6 = new Card('D',9);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Three of a Kind vs Pair. Dealer wins
	@Test
	void compareHandsTest21() {

		// Dealers hand
		Card card1 = new Card('H',7);		// Three of a Kind
		Card card2 = new Card('S',7);
		Card card3 = new Card('C',7);

		// playersHand
		Card card4 = new Card('S',9);		// Pair
		Card card5 = new Card('H',10);
		Card card6 = new Card('D',10);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Three of a Kind vs Pair. Player wins
	@Test
	void compareHandsTest22() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Pair
		Card card2 = new Card('S',7);
		Card card3 = new Card('C',7);

		// playersHand
		Card card4 = new Card('S',9);		// Three of a Kind
		Card card5 = new Card('H',9);
		Card card6 = new Card('D',9);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Three of a Kind vs High Card. Dealer wins
	@Test
	void compareHandsTest23() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Three of a Kind
		Card card2 = new Card('S',4);
		Card card3 = new Card('C',4);

		// playersHand
		Card card4 = new Card('S',3);		// High Card
		Card card5 = new Card('H',6);
		Card card6 = new Card('D',9);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Three of a Kind vs High Card. Player wins
	@Test
	void compareHandsTest24() {

		// Dealers hand
		Card card1 = new Card('H',12);		// High Card
		Card card2 = new Card('S',4);
		Card card3 = new Card('C',2);

		// playersHand
		Card card4 = new Card('S',3);		// Three of a Kind
		Card card5 = new Card('H',3);
		Card card6 = new Card('D',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Three of a Kind vs High Card.  Dealer cannot play because he does not have at least a 12.
	@Test
	void compareHandsTestDealerCantPlay2() {

		// Dealers hand
		Card card1 = new Card('H',11);		// High Card
		Card card2 = new Card('S',4);
		Card card3 = new Card('C',2);

		// playersHand
		Card card4 = new Card('S',3);		// Three of a Kind
		Card card5 = new Card('H',3);
		Card card6 = new Card('D',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer cannot play.");				
	}


	// Straight vs Straight. Dealer wins
	@Test
	void compareHandsTest25() {

		// Dealers hand
		Card card1 = new Card('H',10);		// Straight
		Card card2 = new Card('S',11);
		Card card3 = new Card('C',12);

		// playersHand
		Card card4 = new Card('S',9);		// Straight
		Card card5 = new Card('H',10);
		Card card6 = new Card('D',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight vs Straight. Tie.
	@Test
	void compareHandsTest26() {

		// Dealers hand
		Card card1 = new Card('H',10);		// Straight
		Card card2 = new Card('S',11);
		Card card3 = new Card('C',12);

		// playersHand
		Card card4 = new Card('S',10);		// Straight
		Card card5 = new Card('H',11);
		Card card6 = new Card('D',12);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Should have been a tie");				
	}

	// Straight vs Straight. Player wins
	@Test
	void compareHandsTest27() {

		// Dealers hand
		Card card1 = new Card('H',8);		// Straight
		Card card2 = new Card('S',9);
		Card card3 = new Card('C',10);

		// playersHand
		Card card4 = new Card('S',9);		// Straight
		Card card5 = new Card('H',10);
		Card card6 = new Card('D',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Straight vs Flush. Dealer wins.
	@Test
	void compareHandsTest28() {

		// Dealers hand
		Card card1 = new Card('H',8);		// Straight
		Card card2 = new Card('S',9);
		Card card3 = new Card('C',10);

		// playersHand
		Card card4 = new Card('S',9);		// Flush
		Card card5 = new Card('S',4);
		Card card6 = new Card('S',3);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight vs Flush. Player wins.
	@Test
	void compareHandsTest29() {

		// Dealers hand
		Card card1 = new Card('H',8);		// Flush
		Card card2 = new Card('H',3);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',9);		// Straight
		Card card5 = new Card('C',10);
		Card card6 = new Card('H',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Straight vs Pair. Dealer Wins.
	@Test
	void compareHandsTest30() {

		// Dealers hand
		Card card1 = new Card('H',8);		// Straight
		Card card2 = new Card('C',9);
		Card card3 = new Card('D',10);

		// playersHand
		Card card4 = new Card('S',9);		// Pair
		Card card5 = new Card('S',12);
		Card card6 = new Card('H',9);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1 , ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight vs Pair. Player Wins.
	@Test
	void compareHandsTest31() {

		// Dealers hand
		Card card1 = new Card('H',8);		// Pair
		Card card2 = new Card('C',8);
		Card card3 = new Card('D',10);

		// playersHand
		Card card4 = new Card('S',2);		// Straight 
		Card card5 = new Card('D',3);
		Card card6 = new Card('H',4);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Straight vs High Card. Dealer wins
	@Test
	void compareHandsTest32() {

		// Dealers hand
		Card card1 = new Card('H',8);		// Straight 
		Card card2 = new Card('C',9);
		Card card3 = new Card('D',10);

		// playersHand
		Card card4 = new Card('S',2);		// High Card
		Card card5 = new Card('D',3);
		Card card6 = new Card('H',9);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Straight vs High Card. Player wins
	@Test
	void compareHandsTest33() {

		// Dealers hand
		Card card1 = new Card('H',8);		// High Card
		Card card2 = new Card('C',4);
		Card card3 = new Card('D',12);

		// playersHand
		Card card4 = new Card('S',2);		// Straight 
		Card card5 = new Card('D',3);
		Card card6 = new Card('H',4);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Straight vs High Card. Dealer cannot play because he does not have at least a 12.
	@Test
	void compareHandsTestDealerCantPlay3() {

		// Dealers hand
		Card card1 = new Card('H',8);		// High Card
		Card card2 = new Card('C',4);
		Card card3 = new Card('D',11);

		// playersHand
		Card card4 = new Card('S',2);		// Straight 
		Card card5 = new Card('D',3);
		Card card6 = new Card('H',4);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer cannot play.");				
	}

	// Flush vs Flush. Dealer wins.
	@Test
	void compareHandsTest34() {

		// Dealers hand
		Card card1 = new Card('H',10);		// Flush
		Card card2 = new Card('H',4);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',2);		// Flush
		Card card5 = new Card('S',4);
		Card card6 = new Card('S',9);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// Flush vs Flush. Tie
	@Test
	void compareHandsTest35() {

		// Dealers hand
		Card card1 = new Card('H',10);		// Flush
		Card card2 = new Card('H',4);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',2);		// Flush
		Card card5 = new Card('S',4);
		Card card6 = new Card('S',10);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Should have been a tie");				
	}

	// Flush vs Flush. Player wins.
	@Test
	void compareHandsTest36() {

		// Dealers hand
		Card card1 = new Card('H',10);		// Flush
		Card card2 = new Card('H',4);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',2);		// Flush
		Card card5 = new Card('S',4);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Flush vs Pair. Dealer wins.
	@Test
	void compareHandsTest37() {

		// Dealers hand
		Card card1 = new Card('H',2);		// Flush
		Card card2 = new Card('H',4);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',2);		// Pair
		Card card5 = new Card('H',2);
		Card card6 = new Card('C',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// Flush vs Pair. Player wins.
	@Test
	void compareHandsTest38() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Pair
		Card card2 = new Card('D',4);
		Card card3 = new Card('S',5);

		// playersHand
		Card card4 = new Card('S',3);		//  Flush
		Card card5 = new Card('S',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Flush vs High Card. Dealer wins.
	@Test
	void compareHandsTest39() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Flush
		Card card2 = new Card('H',10);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',3);		// High card
		Card card5 = new Card('D',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// Flush vs High Card. Dealer cannot play because he does not have at least a 12.
	@Test
	void compareHandsTestDealerCantPlay4() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High card
		Card card2 = new Card('C',11);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',3);		// Flush
		Card card5 = new Card('S',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer cannot play");				
	}

	// Flush vs High Card. Player wins.
	@Test
	void compareHandsTest40() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High card
		Card card2 = new Card('C',12);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',3);		// Flush
		Card card5 = new Card('S',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// Pair vs Pair. Dealer wins
	@Test
	void compareHandsTest41() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Pair
		Card card2 = new Card('C',5);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',3);		// Pair
		Card card5 = new Card('C',3);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Pair vs Pair. Tie.
	@Test
	void compareHandsTest42() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Pair
		Card card2 = new Card('C',4);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',4);		// Pair
		Card card5 = new Card('H',4);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Tie.");				
	}

	// Pair vs Pair. Player wins
	@Test
	void compareHandsTest43() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Pair
		Card card2 = new Card('C',5);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',6);		// Pair
		Card card5 = new Card('C',6);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}


	// Pair vs High Card. Dealer wins.
	@Test
	void compareHandsTest44() {

		// Dealers hand
		Card card1 = new Card('H',4);		// Pair
		Card card2 = new Card('C',5);
		Card card3 = new Card('H',5);

		// playersHand
		Card card4 = new Card('S',6);		// High Card
		Card card5 = new Card('C',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}

	// Pair vs High Card. Dealer cannot play.
	@Test
	void compareHandsTestDealerCantPlay5() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High card
		Card card2 = new Card('C',11);
		Card card3 = new Card('H',2);

		// playersHand
		Card card4 = new Card('S',3);		// Pair
		Card card5 = new Card('H',3);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer cannot play");				
	}

	// Pair vs High Card. Player wins.
	@Test
	void compareHandsTest45() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High Card
		Card card2 = new Card('C',3);	
		Card card3 = new Card('H',12);

		// playersHand
		Card card4 = new Card('S',2);		// Pair
		Card card5 = new Card('C',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Player should have won");				
	}

	// High card vs Every Card. Dealer wins.
	@Test
	void compareHandsTest46() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High Card
		Card card2 = new Card('C',3);	
		Card card3 = new Card('H',12);

		// playersHand
		Card card4 = new Card('S',5);		// High Card
		Card card5 = new Card('C',2);
		Card card6 = new Card('S',11);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(1, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}


	// High card vs Every Card. Dealer cannot play.
	@Test
	void compareHandsTestDealerCantPlay6() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High Card
		Card card2 = new Card('C',3);	
		Card card3 = new Card('H',11);

		// playersHand
		Card card4 = new Card('S',1);		// High Card
		Card card5 = new Card('C',2);
		Card card6 = new Card('S',14);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(0, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer cannot play.");				
	}

	// High card vs Every Card. Player wins.
	@Test
	void compareHandsTest47() {

		// Dealers hand
		Card card1 = new Card('H',4);		// High Card
		Card card2 = new Card('C',3);	
		Card card3 = new Card('H',12);

		// playersHand
		Card card4 = new Card('S',1);		// High Card
		Card card5 = new Card('C',2);
		Card card6 = new Card('S',14);

		ArrayList<Card> dealersHand = new ArrayList<Card>();
		ArrayList<Card> playersHand = new ArrayList<Card>();

		dealersHand.add(card1);
		dealersHand.add(card2);
		dealersHand.add(card3);

		playersHand.add(card4);
		playersHand.add(card5);
		playersHand.add(card6);

		assertEquals(2, ThreeCardLogic.compareHands(dealersHand, playersHand), "Dealer should have won");				
	}
	
	
	
	
	// Evaluate winnings for Straight Flush
	
	@Test
	void evalPPWinningsTest1() {
		// Dealers hand
		Card card1 = new Card('H',4);		
		Card card2 = new Card('H',5);	
		Card card3 = new Card('H',6);
		
		int bet = 25;
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertEquals(1000, ThreeCardLogic.evalPPWinnings(hand, bet), "wrong pay-off");		
	}
	
	// Evaluate winnings for Three of a Kind
	
	@Test
	void evalPPWinningsTest2() {
		// Dealers hand
		Card card1 = new Card('H',4);		
		Card card2 = new Card('C',4);	
		Card card3 = new Card('S',4);
		
		int bet = 25;
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertEquals(750, ThreeCardLogic.evalPPWinnings(hand, bet), "wrong pay-off");		
	}
	
	
	// Evaluate winnings for Straight
	
	@Test
	void evalPPWinningsTest3() {
		// Dealers hand
		Card card1 = new Card('H',4);		
		Card card2 = new Card('C',5);	
		Card card3 = new Card('S',6);
		
		int bet = 25;
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertEquals(150, ThreeCardLogic.evalPPWinnings(hand, bet), "wrong pay-off");		
	}
	
	// Evaluate winnings for Flush
	
	@Test
	void evalPPWinningsTest4() {
		// Dealers hand
		Card card1 = new Card('H',4);		
		Card card2 = new Card('H',7);	
		Card card3 = new Card('H',10);
		
		int bet = 25;
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertEquals(75, ThreeCardLogic.evalPPWinnings(hand, bet), "wrong pay-off");		
	}
	
	// Evaluate winnings for Pair
	
	@Test
	void evalPPWinningsTest5() {
		// Dealers hand
		Card card1 = new Card('H',4);		
		Card card2 = new Card('S',4);	
		Card card3 = new Card('D',10);
		
		int bet = 25;
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertEquals(25, ThreeCardLogic.evalPPWinnings(hand, bet), "wrong pay-off");		
	}
}
