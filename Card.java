package FinalPokerHandProject;

public class Card {
	private int value;
	private String suit;

	public Card(String string) {

		String tester = string.substring(0, 1);

		char mytester = tester.charAt(0);

		if (mytester == 'A') {
			this.value = 14;
		}

		else if (mytester == 'K') {
			this.value = 13;
		}

		else if (mytester == 'Q') {
			this.value = 12;
		}

		else if (mytester == 'J') {
			this.value = 11;
		}

		else if (mytester == 'T') {
			this.value = 10;
		}

		else {
			this.value = Integer.parseInt(string.substring(0, 1));
		}
		this.suit = string.substring(1, 2);
	}

	public Card() {

	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public void setValue(String value) {
		this.value = Integer.parseInt(value);
	}

	public int getValue() {
		return this.value;
	}

	public String getSuit() {
		return this.suit;
	}

	public void setCard(String suit, int value) {
		this.value = value;
		this.suit = suit;
	}

	public void printCard() {
		System.out.println(this.suit + " " + this.value);

	}
}