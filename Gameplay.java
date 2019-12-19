// https://www.mkyong.com/java/how-to-get-user-input-in-java/
// https://www.mkyong.com/java/java-how-to-split-a-string/
package FinalPokerHandProject;

import java.util.Scanner;

public class Gameplay {

	private static Card[] blackCards = null;
	private static Card[] whiteCards = null;

	public static void getInput() {

		String[] blackHand = null;
		String[] whiteHand = null;

		String black = "";
		String white = "";

		// Scanner accepts input from the keyboard System.in
		Scanner keyboard = new Scanner(System.in);

		try {
			// System.out.print("Black: ");

			// black = keyboard.nextLine(); // Read user input
			black = "2H 3D 5S 9C KD";

			// System.out.print("White: ");

			// white = keyboard.nextLine(); // Read user input
			white = "2D 3H 5C 9S KH";

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		blackHand = black.split("\\ ");
		whiteHand = white.split("\\ ");
		blackCards = new Card[blackHand.length];
		whiteCards = new Card[whiteHand.length];

		for (int i = 0; i < blackHand.length; i++) {
			blackCards[i] = new Card(blackHand[i]);
			whiteCards[i] = new Card(whiteHand[i]);
		}

		keyboard.close();
	}

	public static void computeHand() {

		int winScore = -1;
		Hand blackHand = new Hand(blackCards);
		Hand whiteHand = new Hand(whiteCards);

		int blackScore = blackHand.getValues();
		int whiteScore = whiteHand.getValues();

		// System.out.println("blackScore: " + blackScore);
		// System.out.println("whiteScore: " + whiteScore);

		if (blackScore > whiteScore) {
			System.out.print("Black wins. ");
			winScore = blackScore;
			blackHand.output(winScore);
		} else if (blackScore < whiteScore) {
			System.out.print("White wins. ");
			winScore = whiteScore;
			whiteHand.output(winScore);
		} else {
			
			if (blackHand.getCardScore() > whiteHand.getCardScore()) {
				System.out.print("Black wins. ");
				blackHand.output(blackScore);
			} else if (blackHand.getCardScore() < whiteHand.getCardScore()){
				System.out.print("White wins. ");
				whiteHand.output(whiteScore);
			}
			else
				if (blackHand.getWinnerScore2() > whiteHand.getWinnerScore2()) {
					
					System.out.print("Black wins. ");
					blackHand.setWin(blackHand.getWinnerScore2(), 1);
					blackHand.output(blackScore);
				}
					
				else if (blackHand.getWinnerScore2() < whiteHand.getWinnerScore2()) {
					whiteHand.setWin(whiteHand.getWinnerScore2(), 1);
					System.out.print("White wins. ");
					whiteHand.output(whiteScore);
				}
				else
					System.out.print("Tie.");
		}
	}

	// main - only instructs the Java Virtual Machine where to start
	public static void main(String[] args) {
		Gameplay.getInput();
		Gameplay.computeHand();
	}
}