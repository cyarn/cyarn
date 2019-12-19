package FinalPokerHandProject;

import java.util.Arrays;

public class Hand {

	private static final int HIGH_CARD = 4; // 4th card always highest
	private Card cards[] = new Card[5];
	private int[] values = new int[5];
	private String[] suits = new String[5];
	private String win1 = "";
	private String win2 = "";
	private int cardScore = 0;
	
	private int winnerscore1 = -1;
	private int winnerscore2 = -1;
	private int winnerscore3 = -1;
	private int winnerscore4 = -1;
	private int winnerscore5 = -1;

	public int getWinnerScore1() {
		return winnerscore1;
	}
	
	public int getWinnerScore2() {
		return winnerscore2;
	}
	
	public int getWinnerScore3() {
		return winnerscore3;
	}
	
	public int getWinnerScore4() {
		return winnerscore4;
	}
	
	public int getWinnerScore5() {
		return winnerscore5;
	}
	
	public String getWin1() {
		return win1;
	}

	public String getWin2() {
		return win2;
	}

	public int getCardScore() {
		return cardScore;
	}

	public void setWin(int card, int position) {

		String win = "";

		if (card == 10)
			win = "Ten";
		else if (card == 11)
			win = "Jack";
		else if (card == 12)
			win = "Queen";
		else if (card == 13)
			win = "King";
		else if (card == 14)
			win = "Ace";
		else
			win = Integer.toString(card);

		if (position == 1) {
			this.win1 = win;
		} else {
			this.win2 = win;
		}
	}
	
	public void setFlushWin(String suit)
	{
		if (suit.equals("D"))
			win1 = "Diamonds";
		else if (suit.equals("S"))
			win1 = "Spades";
		else if (suit.equals("H"))
			win1 = "Hearts";
		else if (suit.equals("C"))
			win1 = "Clubs";
	}

	public Hand(Card[] cards) {

		this.cards = cards;
	}

	public int getValues() {

		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getValue();
			suits[i] = cards[i].getSuit();
		}

		Arrays.sort(values);
		boolean straight = false;
		boolean flush = false;
		boolean fourOfAKind = false;
		boolean fullHouse = false;
		boolean threeOfAKind = false;
		boolean twoPairs = false;
		boolean pair = false;

		flush = checkFlush(suits, values);
		straight = checkStraight(values);

		if (straight & flush)
			return 9;

		fourOfAKind = checkFourOfAKind(values);

		if (fourOfAKind)
			return 8;

		fullHouse = checkFullHouse(values);

		if (fullHouse)
			return 7;

		else if (flush)
			return 6;

		else if (straight)
			return 5;

		threeOfAKind = checkThreeOfAKind(values);

		if (threeOfAKind)
			return 4;

		twoPairs = checkTwoPairs(values);

		if (twoPairs)
			return 3;

		pair = checkPair(values);

		if (pair) {
			return 2;

		} else {
			setWin(cards[HIGH_CARD].getValue(), 1);
			cardScore = cards[HIGH_CARD].getValue();
//			Arrays.sort(cards);
			winnerscore1 = cards[4].getValue();
			winnerscore2 = cards[3].getValue();
			winnerscore3 = cards[2].getValue();
			winnerscore4 = cards[1].getValue();
			winnerscore5 = cards[0].getValue();
			return 1;
		}

	}

	public void output(int score) {
		if (score == 1)
			System.out.println("- with high card: " + win1);
		if (score == 2)
			System.out.println("- with a pair: " + win1 + "s");
		if (score == 3)
			System.out.println("- with two pairs: " + win1 + "s and " + win2 + "s");
		if (score == 4)
			System.out.println("- with three of a kind: " + win1 + "s");
		if (score == 5)
			System.out.println("- with straight: " + win1 + " through " + win2);
		if (score == 6)
			System.out.println("- with flush: " + win1 + " high card was " + win2);
		
		if (score == 7)
			System.out.println("- with full house: " + win1 + "s over " + win2 + "s");
		if (score == 8)
			System.out.println("- with four of a kind, " + win1);
		if (score == 9)
			System.out.println("- with straight flush, " + win1 + " through " + win2);

	}

	public boolean checkStraight(int[] values) {

		Arrays.sort(values);
		if (((values[0] + 1) == values[1]) && ((values[1] + 1) == values[2]) && ((values[2] + 1) == values[3])
				&& (values[3] + 1) == values[4]) {
			setWin(values[0], 1);
			setWin(values[4], 2);
			return true;
		} else
			return false;
	}

	boolean checkFlush(String suits[], int values[]) {
		Arrays.sort(suits);
		Arrays.sort(values);
		if (suits[0].equals(suits[4]))
		{
			setFlushWin(suits[0]);
			setWin(values[4], 2);
			return true;
		}
		else
			return false;
	}

	boolean checkFourOfAKind(int values[]) {
		Arrays.sort(values);
		if (values[0] == values[3] || values[1] == values[4])
			return true;
		else
			return false;
	}

	boolean checkFullHouse(int values[]) {
		if (((values[0] == values[1] && values[1] == values[2]) && (values[3] == values[4]))
				|| ((values[0] == values[1]) && ((values[2] == values[3]) && values[3] == values[4])))
		{
			if (values[0] == values[2])
			{
				setWin(values[0], 1);
				setWin(values[4], 2);
			}
			else
			{
				setWin(values[4], 1);
				setWin(values[0], 2);
			}
			
			
			return true;
		}
		else
			return false;
	}

	boolean checkThreeOfAKind(int values[]) {
		Arrays.sort(values);
		if (values[0] == values[2] || values[1] == values[3] || values[2] == values[4])
		{
			if (values[0] == values[2])
				setWin(values[0], 1);
			else if (values[1] == values[3])
				setWin(values[1], 1);
			else
				setWin(values[4], 1);
			
			return true;
		}
		else
			return false;
	}

	boolean checkTwoPairs(int values[]) {
		Arrays.sort(values);
		if ((values[0] == values[1] && values[2] == values[3]) || (values[0] == values[1] && values[3] == values[4])
				|| (values[1] == values[2]) && values[3] == values[4])
		{
			if (values[0] == values[1])
			{
				setWin(values[0], 1);
				if (values[2] == values[3])
					setWin(values[2], 2);
				else
					setWin(values[3], 2);
			}
			else if (values[1] == values[2])
			{
				setWin(values[1], 1);
				setWin(values[3], 2);
			}

			
			return true;

		}
			
			
		else
			return false;
	}

	boolean checkPair(int values[]) {
		Arrays.sort(values);
		
		if ((values[0] == values[1]) || values[1] == values[2] || values[2] == values[3] || values[3] == values[4])
		{	
			if (values[0] == values[1])
				setWin(values[0], 1);
			else if (values[1] == values[2])
				setWin(values[1], 1);
			else if (values[2] == values[3])
				setWin(values[2], 1);
			else
				setWin(values[4], 1);
			
			return true;
		}
		else
			return false;
	}

	public void setHand(char ranks[], char suits[]) {

		for (int i = 0; i < 5; i++) {

			// cards[i] = new Card(, ranks[i], suits[i]);
		}
	}
}