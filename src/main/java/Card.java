import java.lang.Comparable;

public class Card implements Comparable<Card>{
	
	private char suit;
	private int value;
	
	public Card(char suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	public char getSuit() {
		return this.suit;
	}
	
	public void setSuit(char suit) {
		this.suit = suit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}	
	
	public int compareTo(Card otherCard) {
		if(this.value > otherCard.value) {
			return 1;
		} else if (this.value < otherCard.value){
			return -1;
		} else {
			return 0;
		}
	}	
}
