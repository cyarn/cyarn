//Program below will accept 5 cards from two players, one named Black, and the other named White.
//Program accepts them in the following format: 2H 3D 5S 9C KD
//The previous comment translates to the 2 of hearts, the 3 of diamonds, the 5 of spades, the 9 of clubs and the King of diamonds
//after the program receives the two different poker hands, it will evaluate each one, determine which hand would win, and print that out to the screen
//The program will also print out the meaning of the hand, namely, what makes it value, examples include high card, one pair, three of a kind, straight, flush, etc.


package poker;									//I haven't had much experience with eclipse workplace for Java developers, but when I set this game up, it requires this package now. Not sure why
import java.io.*;								//I believe I'll need this to send text back and forth between the program and the console
import java.util.Arrays;						//I believe I'll need this to set up arrays that I'll use later. Arrays will make a lot of things easier, but mostly sorting the cards

public class Gameplay {							

	public static void main(String[] args) {
//		int value = 0;							//There's going to be a lot of old code throughout the program (as of right now) that was either used to test, or was part of 
//		int suit = 0;							//a failed prototype of the program and is no longer used.
		int WhiteScore = -1;					//I sometimes initialize variables to negative one, this helps me debug later
		int BlackScore = -1;					//You'll see later that I score the hands with a number after I determine how good the hand is. It makes it easy to compare them.
		int WhiteSecondScore = -1;				//The WhiteSecondScore and BlackSecondScore variables help me if there is ever a tie between two hands
		int BlackSecondScore = -1;
		int WhiteThirdScore = -1;				//In the rare case of a tie with the WhiteSecondScore and BlackSecondScore variables, I have this one to determine the better between those.
		int BlackThirdScore = -1;				//I could have continued adding more tiebreaker variables but it seemed tedious for the purposes of this assignment
		String WinningText = "";				//This is for later when I print text to the screen after determining which hand is best
//		String suitname = "null";
//		String[][] CardDeck = new String [4] [14];	I honestly don't remember what this was for. I think I was trying to create an entire deck to play with. That would be useful for other programs, but probably not practical in this one
//		char W1; 								As you can see here, I originally tried to do this program without arrays.
//		char W2;								In hindsight, I probably could have done that, but it would have been
//		char W3;								much more complicated. I prefer the way it ended up.
//		char W4;
//		char W5;								 
		
		String[] BSuit = new String[5];			//These are both used later when I wanted to be able to print the full name of 
		String[] WSuit = new String[5];			//the suits in the case of a flush or straight flush
		
		//char BlackSuit1;
		//char BlackSuit2;						more evidence of my attempt to do the assignment without the use of arrays
		//char BlackSuit3;
		//char BlackSuit4;
		//char BlackSuit5;
		
		char[] BlackSuit = new char[5];			//I need an array to hold the letters that identify the suits of the cards later
		
		//char BlackValue1;
		//char BlackValue2;
		//char BlackValue3;
		//char BlackValue4;
		//char BlackValue5;
		
		char[] BlackValue = new char[5];		//I'll also need an array to hold the letters (and numbers) that identify the value of the cards
		
		//char WhiteSuit1;
		//char WhiteSuit2;
		//char WhiteSuit3;
		//char WhiteSuit4;
		//char WhiteSuit5;
		
		char[] WhiteSuit = new char[5];			//I've already described what this is for the Black player, this is the identical version for the White player
		
		//char WhiteValue1;
		//char WhiteValue2;
		//char WhiteValue3;
		//char WhiteValue4;
		//char WhiteValue5;
		
		char[] WhiteValue = new char[5];		//Self-explanatory
		
		//int numWhite1 = -1;
		//int numWhite2 = -1;
		//int numWhite3 = -1;
		//int numWhite4 = -1;
		//int numWhite5 = -1;
		
		int[] numWhite = new int[5];			//I needed an array to hold an integer when I determined the value of the cards, as opposed to a String or char
		
		//int numBlack1 = -1;
		//int numBlack2 = -1;
		//int numBlack3 = -1;
		//int numBlack4 = -1;
		//int numBlack5 = -1;
		
		int[] numBlack = new int[5];			//Same thing for the other player
		
		InputStreamReader isr = new InputStreamReader(System.in);	//I needed to use this to manipulate and collect data from the console
		BufferedReader buffer = new BufferedReader(isr);			//This was used as well, for similar purposes
		String WhiteInput = "";										//Created a variable to store the input
		String BlackInput = "";										//I don't think I needed to initialize these, but I did it anyway...I'm more used to using C++
		
		System.out.print("Black: ");								//Here I ask for the Black player's hand
		try															//it's always a good idea to use a try catch block any time you are collecting information from the user
		{
			BlackInput = buffer.readLine();							//here I am collecting information from the user
			//buffer.close();										//at first I had this in here but then I realized I needed the buffer again when I get information about the White player
			
			
		}
		catch (IOException e)										//if the user puts in anything that isn't formatted properly, they get an error. 
		{
			System.out.println("An input error has occurred");
		}
		
		
		
		BlackValue[0] = BlackInput.charAt(0);						//There might be a better way to do this, but I'm using this to collect the data I need
		BlackValue[1] = BlackInput.charAt(3);						
		BlackValue[2] = BlackInput.charAt(6);
		BlackValue[3] = BlackInput.charAt(9);
		BlackValue[4] = BlackInput.charAt(12);
		
		BlackSuit[0] = BlackInput.charAt(1);						//I've already collected the values of each card, but I'll need the suits as well
		BlackSuit[1] = BlackInput.charAt(4);
		BlackSuit[2] = BlackInput.charAt(7);
		BlackSuit[3] = BlackInput.charAt(10);
		BlackSuit[4] = BlackInput.charAt(13);
		
		numBlack[0] = fixVal(BlackValue[0]);						//I used a function to convert all the chars into integers. In hindsight, I didn't need a function
		numBlack[1] = fixVal(BlackValue[1]);						//to do that...I could have done it all in the main
		numBlack[2] = fixVal(BlackValue[2]);
		numBlack[3] = fixVal(BlackValue[3]);
		numBlack[4] = fixVal(BlackValue[4]);
		
		
		
		
		System.out.print("White: ");								//I'm doing the same thing as before but now I'm getting information about White player's hand
		try
		{
			
			WhiteInput = buffer.readLine();							//collecting the information...
			buffer.close();											//It's good practice to close the buffer when you're done with it
			
			
		}
		catch (IOException e)										
		{
			System.out.println("An input error has occurred");
		}
		
		WhiteValue[0] = WhiteInput.charAt(0);
		WhiteValue[1] = WhiteInput.charAt(3);
		WhiteValue[2] = WhiteInput.charAt(6);
		WhiteValue[3] = WhiteInput.charAt(9);
		WhiteValue[4] = WhiteInput.charAt(12);
		
		WhiteSuit[0] = WhiteInput.charAt(1);
		WhiteSuit[1] = WhiteInput.charAt(4);
		WhiteSuit[2] = WhiteInput.charAt(7);
		WhiteSuit[3] = WhiteInput.charAt(10);
		WhiteSuit[4] = WhiteInput.charAt(13);
		
		numWhite[0] = fixVal(WhiteValue[0]);
		
		//if (numWhite1 == -1)
		//	numWhite1 = Character.getNumericValue(WhiteValue1);	
		
		
		numWhite[2] = fixVal(WhiteValue[2]);						//You can see this is an example of how I learned during the process of
																	//writing the code. The stuff that is commented out is what I was using before,
		//if (numWhite2 == -1)										//then I realized a more efficient way to do it and changed it up.
		//	numWhite2 = Character.getNumericValue(WhiteValue2);
	
		
		numWhite[3] = fixVal(WhiteValue[3]);
		
		//if (numWhite3 == -1)
		//	numWhite3 = Character.getNumericValue(WhiteValue3);
		
		numWhite[4] = fixVal(WhiteValue[4]);
		
		//if (numWhite4 == -1)
		//	numWhite4 = Character.getNumericValue(WhiteValue4);
		
		numWhite[1] = fixVal(WhiteValue[1]);
		
		//if (numWhite5 == -1)
		//	numWhite5 = Character.getNumericValue(WhiteValue5);
		
		Arrays.sort(numBlack);										//sorting the arrays from least to greatest is going to make my job
		Arrays.sort(numWhite);										//a lot easier later. I thought I was going to have to do it using a loop at first
		
		//for (int i = 0; i < 5; i++)
		//{
			
		//}
						//All of the code below this comment was used for debugging and testing. Any time the program spit out something I didn't like, I probably used some of the lines below to try and figure out what was going on.
//		System.out.println("numBlack1: " + numBlack[0]);
//		System.out.println("numBlack2: " + numBlack[1]);
//		System.out.println("numBlack3: " + numBlack[2]);			
//		System.out.println("numBlack4: " + numBlack[3]);
//		System.out.println("numBlack5: " + numBlack[4]);
		
//		System.out.println("numWhite1: " + numWhite[0]);
//		System.out.println("numWhite2: " + numWhite[1]);
//		System.out.println("numWhite3: " + numWhite[2]);
//		System.out.println("numWhite4: " + numWhite[3]);
//		System.out.println("numWhite5: " + numWhite[4]);
		
		
		
		
//		System.out.println("BlackValue[0]: " + BlackValue[0]);
//		System.out.println("BlackValue[1]: " + BlackValue[1]);
//		System.out.println("BlackValue[2]: " + BlackValue[2]);
//		System.out.println("BlackValue[3]: " + BlackValue[3]);
//		System.out.println("BlackValue[4]: " + BlackValue[4]);

//		System.out.println("WhiteValue[0]: " + WhiteValue[0]);
//		System.out.println("WhiteValue[1]: " + WhiteValue[1]);
//		System.out.println("WhiteValue[2]: " + WhiteValue[2]);
//		System.out.println("WhiteValue[3]: " + WhiteValue[3]);
//		System.out.println("WhiteValue[4]: " + WhiteValue[4]);
//		int win = Character.getNumericValue(WhiteValue1);
//		System.out.println("win is equal to: " + win);
//		System.out.println("Black's poker hand is: " + BlackInput);
//		System.out.println("White's poker hand is: " + WhiteInput);
//		System.out.println("Your B1 char is equal to: " + BlackValue1);
//		System.out.println("Your B2 char is equal to: " + BlackValue2);
//		System.out.println("Your B3 char is equal to: " + BlackValue3);
//		System.out.println("Your B4 char is equal to: " + BlackValue4);
//		System.out.println("Your B5 char is equal to: " + BlackValue5);
		
//		System.out.println("Your BlackSuit1 value is: " + BlackSuit1);
//		System.out.println("Your BlackSuit2 value is: " + BlackSuit2);
//		System.out.println("Your BlackSuit3 value is: " + BlackSuit3);
//		System.out.println("Your BlackSuit4 value is: " + BlackSuit4);
//		System.out.println("Your BlackSuit5 value is: " + BlackSuit5);
		
//		System.out.println("Your W1 char is equal to: " + WhiteValue1);
//		System.out.println("Your W2 char is equal to: " + WhiteValue2);
//		System.out.println("Your W3 char is equal to: " + WhiteValue3);
//		System.out.println("Your W4 char is equal to: " + WhiteValue4);
//		System.out.println("Your W5 char is equal to: " + WhiteValue5);
		
//		System.out.println("Your WhiteSuit1 value is: " + WhiteSuit1);
//		System.out.println("Your WhiteSuit2 value is: " + WhiteSuit2);
//		System.out.println("Your WhiteSuit3 value is: " + WhiteSuit3);
//		System.out.println("Your WhiteSuit4 value is: " + WhiteSuit4);
//		System.out.println("Your WhiteSuit5 value is: " + WhiteSuit5);
																						//All the stuff above was used for testing
		//for (int i = 0; i < 52; i++)											//I think this was where I was trying to create an entire deck to use for the program.
		//{
		//	if (value == 13)
		//		{
		//			value = 2;
		//			suit ++;
		//		}
//		suitname = fixname(suit);
			
		//CardDeck [suit][value] = (value + " of "  + suitname);
		
		//System.out.println(cdf [suit][value]);
		//value ++;
		
		WhiteScore = handScore(numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4], WhiteSuit[0], WhiteSuit[1], WhiteSuit[2], WhiteSuit[3], WhiteSuit[4]); //Here I'm calling some functions to help me determine the value of the hands
		BlackScore = handScore(numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4], BlackSuit[0], BlackSuit[1], BlackSuit[2], BlackSuit[3], BlackSuit[4]);
		
//		System.out.println("BlackScore: " + BlackScore);
//		System.out.println("WhiteScore: " + WhiteScore);	//more testing code
		
		WhiteSecondScore = SecondScore(WhiteScore, numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4], WhiteSuit[0], WhiteSuit[1], WhiteSuit[2], WhiteSuit[3], WhiteSuit[4]);
		BlackSecondScore = SecondScore(BlackScore, numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4], BlackSuit[0], BlackSuit[1], BlackSuit[2], BlackSuit[3], BlackSuit[4]);

		//The two lines above this line call a function that will be used in the case of a tie in WhiteScore and BlackScore variables
		
//		WinningText == DetermineWinningText();
		
		BSuit[0] = fixname(BlackSuit[0]);	//These were both used to get String versions because it looks prettier in the output
		WSuit[0] = fixname(WhiteSuit[0]);	//to say "Clubs" or "Spades" than just "C" or "S"

		
		
		if (BlackScore > WhiteScore)	//If Black has a higher score at this point, Black has won the hand
		{
			System.out.println("Black Wins");	//We let the user know that Black wins
			WinningText = DetermineWinningText(BlackScore, numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4], BSuit[0]); //We call this function to create the output that tells us how Black won, and with which cards
			System.out.println(WinningText); //Then, we print that to the screen. We'll do this again immediately following this block in the next if statement, so I won't comment on that one
		//	WinOutput(BlackScore);
		}
		else if (WhiteScore > BlackScore)
		{
			System.out.println("White Wins");
			WinningText = DetermineWinningText(WhiteScore, numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4], WSuit[0]);
			System.out.println(WinningText);
		//	WinOutput(WhiteScore);
		}
			else if (WhiteScore == BlackScore) //in the case of a tie we go to this block
		{
			WhiteSecondScore = SecondScore(WhiteScore, numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4], WhiteSuit[0], WhiteSuit[1], WhiteSuit[2], WhiteSuit[3], WhiteSuit[4]);
			BlackSecondScore = SecondScore(BlackScore, numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4], BlackSuit[0], BlackSuit[1], BlackSuit[2], BlackSuit[3], BlackSuit[4]);
			
			//We've called in the second functions here that help us determine which hand won in the event of a tie. Usually we just look for the higher value card in the important cards in the hands
			
			//System.out.println("Draw...checking tiebreakers");
			if (BlackSecondScore > WhiteSecondScore)
			{
				System.out.println("Black Wins"); //Same stuff as before but this time we're comparing the tiebreaker scores instead of the regular scores
				WinningText = DetermineWinningText(BlackScore, numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4], BSuit[0]); 
				System.out.println(WinningText);
				//WinOutput(WhiteScore);
			}
			else if (WhiteSecondScore > BlackSecondScore)
			{
				System.out.println("White Wins");
				WinningText = DetermineWinningText(WhiteScore, numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4], WSuit[0]);
				System.out.println(WinningText);
				//WinOutput(WhiteScore);
			}
			else if (WhiteSecondScore == BlackSecondScore) //if it's tied a second time we have to look at even more stuff to determine who the winner is
			{
				WhiteThirdScore = ThirdScore(WhiteScore, numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4]); //We call the functions that help us figure out the second tiebreakers
				BlackThirdScore = ThirdScore(BlackScore, numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4]);
				if (WhiteThirdScore > BlackThirdScore)	//If White has a better score in the second tiebreaker White wins
				{
					System.out.println("White Wins");
					WinningText = DetermineWinningText(WhiteScore, numWhite[0], numWhite[1], numWhite[2], numWhite[3], numWhite[4], WSuit[0]);
					System.out.println(WinningText);
				}
				
				if (BlackThirdScore > WhiteThirdScore) //Likewise with Black
				{
					System.out.println("Black Wins");
					WinningText = DetermineWinningText(BlackScore, numBlack[0], numBlack[1], numBlack[2], numBlack[3], numBlack[4], BSuit[0]);
					System.out.println(WinningText);
					
				}
				else if (BlackThirdScore == WhiteThirdScore)
					System.out.println("Tie");					//This isn't necessarily true, but the likelihood of having to look at a third card is pretty slim
			}
		}	
		
		
	}

//}
	
	static String fixname(char suit)		//This function is just here to spit out a pretty version of the suit so we can put it in our output
	{
		if (suit == 'C')					
			return "Clubs";
		if (suit == 'D')
			return "Diamonds";
		if (suit == 'H')
			return "Hearts";
		else
			return "Spades";
	}
	
	static int fixVal(char cardVal)			//In the case of a ten, jack, queen, king, or ace, the user is going to put in
	{										//characters other than numbers. This makes it easier for the program because it's only reading
											//one character every time, and also makes my life as a programmer easier.
		if (cardVal == 'T')
			return 10;
		
		else if (cardVal == 'J')			//I made the assumption that the user will always use all capital letters
		{
			return 11;
		}
		else if (cardVal == 'Q')
		{
			return 12;
		}
		else if (cardVal == 'K')
		{
			return 13;
		}
		else if (cardVal == 'A')
		{
			return 14;					//I assumed aces were high. In the case that Aces were low, this value would be "1" instead of "14"
		}
		else
			return Character.getNumericValue(cardVal);	//There are actually a lot of useful functions like this in Java. This one turns a char into an int
		
	}
	
	static int handScore(int card1, int card2, int card3, int card4, int card5, char suit1, char suit2, char suit3, char suit4, char suit5)	//Here's our first function that gives us the score before tiebreakers are needed
	{
//		int high = -1;		//more unused stuff
//		int low = 999;
		boolean straight = false;														//I set straight to false at first
		straight = checkStraight(card1, card2, card3, card4, card5);					//This calls a function to test if the values are in numerical order one by one
		
		if (suit1 == suit2 && suit2 == suit3 && suit3 == suit4 && suit4 == suit5)		//This determines for us if the hand is a flush or not.
		{																				//Luckily this is all we really have to worry about regarding the suits

			if (straight == true)														//If the first condition is met, it's a flush. We also have to determine if it's a straight
				return 9;																//Luckily, if it's a flush, we don't have to worry about if there are multiples of cards...unless we're playing with multiple decks
			else
				return 5;																//if the previous condition wasn't met, we know it's just a regular straight
		}
		else if (card1 == card2 || card2 == card3 || card3 == card4 || card4 == card5)		//if anything at all is a pair we have to determine how many pairs there are, as well as if there are any other multiples
			if ((card1 == card2 && card2 == card3 && card3 == card4) || (card2 == card3 && card3 == card4 && card4 == card5)) //this determines if there is a four of a kind
				return 8;						
			else if ((card1 == card2 && card2 == card3) || (card2 == card3 && card3 == card4) || (card3 == card4 && card4 == card5) )	//if it's not a four of a kind, we've gotta figure out if there's a three of a kind
				if ((card1 == card2 && card2 == card3) && (card4 == card5) || (card1 == card2) && (card3 == card4 && card4 == card5))	// if there is a three of a kind, we have to determine if there's a full house
					return 7;
				else				//if there wasn't a full house, at this point we should have checked everything else, so it is a three of a kind
					return 4;
			else if (((card1 == card2) && (card3 == card4)) || ((card1 == card2) && (card4 == card5)) || (card2 == card3) && (card4 == card5)) //this is to determine if we have two pair
				return 3;		//Each hand has a different integer value based on how good it is. I probably should have mentioned that earlier
			else
				return 2;			//if none of the previous conditions have been met, it's just a regular pair
			
		return 1;				//if none of the previous conditions have been met, we take the high card
		
//		return -1; 				//this should never execute
	
}
	
	static boolean checkStraight(int val1, int val2, int val3, int val4, int val5)					//This function checks if there is a straight
	{
		if ((val1 +1) == val2 && (val2 + 1) == val3 && (val3 + 1) == val4 && (val4 + 1) == val5)	//If each value is equal to the previous value plus one, it's a straight
			return true;	
		else
			return false;	//otherwise, it's not
	}
	static int SecondScore(int score, int card1, int card2, int card3, int card4, int card5, char suit1, char suit2, char suit3, char suit4, char suit5)
	{											//Here we have the secondscore function, to use for tiebreakers
		if (score == 1)
			return card5;						//In the event of two high cards, we have to determine which high card is more valuable
		if (score == 2)
			if (card1 == card2)					//In the event of a pair, we have to track down which cards match, and use the value from one of them
				return card1;
			else if (card2 == card3)
				return card2;
			else if (card3 == card4)
				return card3;
			else
				return card5;					//We know that if none of the previous conditions are met, the card4 and card5 must be the pair, so we send card5
		if (score == 3)							//If there's two pair, we have to track down one of the higher pairs to use for the tie breaker
			if (card5 == card4)					//Since we know the higher cards are toward the back, we check those first
				return card5;
			else if (card4 == card3)		
				return card4;
			else if (card3 == card2)			//In hindsight, I think this could just be an else statement, since none of the other conditions are met
				return card3;
		if (score == 4)
			if (card5 == card4)					//In the case of three of a kind, westart from the top card and work our way down...any time there's 
				return card5;					//a match, we know that's going to be what we need to send
			else if (card4 == card3)
				return card4;
			else
				return card3;					//no matter what, assuming the cards are in order (which they should be) card 3 has to be a part of the three
		if (score == 5)							//in the event of a straight, we just send card 5. If card 5 from both hands match it will be a draw regardless
			return card5;
		if (score == 6)							//in the event of a flush, we just send card 5. However, we'll need to keep working our way down since flushes 
			return card5;						//can vary a lot more than straights
		if (score == 7)
			if (card5 == card4 && card4 == card3)	//full houses can be kind of tricky. We have to make sure we get a card from the three, not from the pair
				return card5;
			else if (card4 == card3 && card3 == card2)
				return card4;
			else
				return card3;						//if none of the previous conditions have been met, we know that card3 must have one of the three, just like earlier
		if (score == 8)
			if (card5 == card4)						//in the case of four of a kind, we're either getting the top four or the bottom four
				return card5;
			else
				return card1;						//I could have used any other card but I chose card1 just to be safe
		if (score == 9)
			return card5;							//in the event of the extremely rare straight flush, we just send the highest value card
		
		return -1;									//this should never execute, I used it for testing
	}
	
//	static void WinOutput(int score)
//	{
//		if (score == 1)
//			System.out.println("- with high card:");
//		else if (score == 2)
//			System.out.println("- with pair:");
//		else if (score == 3)
//			System.out.println("- with two pairs...high pair:");
//		else if (score == 4)
//			System.out.println("- with three of a kind: ");
//		else if (score == 5)
//			System.out.println("- with straight: ");
//		else if (score == 6)
//			System.out.println("- with flush: ");										//old function I used before reworking it into DetermineWinningText
//		else if (score == 7)
//			System.out.println("- with full house:");
//		else if (score == 8)
//			System.out.println("- with four of a kind: ");
//		else if (score == 9)
//			System.out.println("- with straight flush:");
//		else
//			System.out.println("fatal error");
//	}
	
	static String DetermineWinningText(int score, int card1, int card2, int card3, int card4, int card5, String suit)	
	{
		String winner;				//I return the winner every time, I could probably have just returned the string without a variable but I prefer to use a variable. It helps me stay organized
		String wincard;				//I use wincard to help me determine which cards I want to output because they helped that player win the hand
		String minorcard;			//minorcard is used sometimes in the cases that I need to mention two cards, such as two pair or full house
		if (score == 1)
		{
			if (card5 == 11)
				wincard = "Jack";
			else if (card5 == 12)
				wincard = "Queen";				//I reused this same code several times in this function, which makes it an extremely long function
			else if (card5 == 13)				//in hindsight, I'm sure I could have come up with a more efficient way to do this, but I'm out of practice
				wincard = "King";
			else if (card5 == 14)
				wincard = "Ace";
			else
				wincard = Integer.toString(card5);		//if the card didn't meet any of the requirements above, we just convert it to a string to make the output look nice later
			winner = ("- with high card: " + wincard);
			return winner;
		}
		if (score == 2)
			if (card1 == card2)
			{
				if (card1 == 11)
					wincard = "Jack";
				else if (card1 == 12)
					wincard = "Queen";
				else if (card1 == 13)
					wincard = "King";
				else if (card1 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card1);
			
				winner = ("- with a pair of " + wincard + "s");
				return winner;
			}
			else if (card2 == card3)
			{
				if (card2 == 11)
					wincard = "Jack";
				else if (card2 == 12)
					wincard = "Queen";
				else if (card2 == 13)
					wincard = "King";
				else if (card2 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card2);
				winner = ("- with a pair of " + wincard + "s");
				return winner;
			}
			else if (card3 == card4)
			{
				if (card3 == 11)
					wincard = "Jack";
				else if (card3 == 12)
					wincard = "Queen";
				else if (card3 == 13)
					wincard = "King";
				else if (card3 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card3);
				winner = ("- with a pair of " + wincard + "s");
				return winner;
			}
			else
			{
				if (card5 == 11)
					wincard = "Jack";
				else if (card5 == 12)
					wincard = "Queen";
				else if (card5 == 13)
					wincard = "King";
				else if (card5 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card5);
				winner = ("- with a pair of " + wincard + "s");
				return winner;
			}
		
		if (score == 3)
			if (card5 == card4)
				if (card3 == card2)
				{	if (card5 == 11)
						wincard = "Jack";
					else if (card5 == 12)
						wincard = "Queen";
					else if (card5 == 13)
						wincard = "King";
					else if (card5 == 14)
						wincard = "Ace";
					else
						wincard = Integer.toString(card5);
				
					if (card3 == 11)
						minorcard = "Jack";
					else if (card3 == 12)
						minorcard = "Queen";
					else if (card3 == 13)
						minorcard = "King";
					else if (card3 == 14)
						minorcard = "Ace";													//here's where I started using minorcard. Just so I can show two values for two pair
					else
						minorcard = Integer.toString(card3);
					winner = ("- with a pair of " + wincard + "s and a pair of " + minorcard + "s");
					return winner;
				}
				else
				{
					if (card3 == 11)
						wincard = "Jack";
					else if (card3 == 12)
						wincard = "Queen";
					else if (card3 == 13)
						wincard = "King";
					else if (card3 == 14)
						wincard = "Ace";
					else
						wincard = Integer.toString(card3);
				
					if (card1 == 11)
						minorcard = "Jack";
					else if (card1 == 12)
						minorcard = "Queen";
					else if (card1 == 13)
						minorcard = "King";
					else if (card1 == 14)
						minorcard = "Ace";
					else
						minorcard = Integer.toString(card1);
					winner = ("- with a pair of " + wincard + "s and a pair of " + minorcard + "s");
					return winner;
				}
					
			else if (card4 == card3)
			{	if (card4 == 11)
					wincard = "Jack";
				else if (card4 == 12)
					wincard = "Queen";
				else if (card4 == 13)
					wincard = "King";
				else if (card4 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card4);
		
				if (card2 == 11)
					minorcard = "Jack";
				else if (card2 == 12)
					minorcard = "Queen";
				else if (card2 == 13)
					minorcard = "King";
				else if (card2 == 14)
					minorcard = "Ace";
				else
					minorcard = Integer.toString(card2);
				winner = ("- with a pair of " + wincard + "s and a pair of " + minorcard + "s");
				return winner;
			}
			else
			{
				winner = "error";
				return winner;
			}	

		if (score == 4)
			if (card5 == card4)
			{
				if (card4 == 11)
					wincard = "Jack";
				else if (card4 == 12)
					wincard = "Queen";
				else if (card4 == 13)
					wincard = "King";
				else if (card4 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card4);
				winner = ("- with three " + wincard + "s");
				return winner;
			}
				
			else if (card4 == card3)
			{	if (card4 == 11)
					wincard = "Jack";
				else if (card4 == 12)
					wincard = "Queen";
				else if (card4 == 13)
					wincard = "King";
				else if (card4 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card4);
				winner = ("- with three " + wincard + "s");
				return winner;
			}
			else
			{
				if (card3 == 11)
					wincard = "Jack";
				else if (card3 == 12)
					wincard = "Queen";
				else if (card3 == 13)
					wincard = "King";
				else if (card3 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card3);
				winner = ("- with three " + wincard + "s");
				return winner;
			}

		if (score == 5)
		{
			if (card5 == 11)
				wincard = "Jack";
			else if (card5 == 12)
				wincard = "Queen";
			else if (card5 == 13)
				wincard = "King";
			else if (card5 == 14)
				wincard = "Ace";
			else
				wincard = Integer.toString(card5);
			winner = ("- with a straight. The high card was " + wincard);		//I put in the high card so that in the case of a tie breaker it will help the user understand why the player won
			return winner;
		}
		if (score == 6)
		{
			if (card5 == 11)
				wincard = "Jack";
			else if (card5 == 12)
				wincard = "Queen";
			else if (card5 == 13)
				wincard = "King";
			else if (card5 == 14)
				wincard = "Ace";
			else
				wincard = Integer.toString(card5);
			winner = ("- with a flush of " + suit + "s. the high card was " + wincard);
			return winner;
		}
		if (score == 7)
			if (card5 == card4 && card4 == card3)
			{	if (card4 == 11)
					wincard = "Jack";
				else if (card4 == 12)
					wincard = "Queen";
				else if (card4 == 13)
					wincard = "King";
				else if (card4 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card4);
				if (card1 == 11)
					minorcard = "Jack";
				else if (card1 == 12)
					minorcard = "Queen";
				else if (card1 == 13)
					minorcard = "King";
				else if (card1 == 14)
					minorcard = "Ace";										//Here I'm using minorcard to indicate which one was the three of a kind and which one was the pair
				else
					minorcard = Integer.toString(card1);
				winner = ("- with a full house. " + wincard + "s over " + minorcard + "s");
				return winner;
			}
			
			else
			{
				if (card1 == 11)
					wincard = "Jack";
				else if (card1 == 12)
					wincard = "Queen";
				else if (card1 == 13)
					wincard = "King";
				else if (card1 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card1);
				if (card5 == 11)
					minorcard = "Jack";
				else if (card5 == 12)
					minorcard = "Queen";
				else if (card5 == 13)
					minorcard = "King";
				else if (card5 == 14)
					minorcard = "Ace";
				else
					minorcard = Integer.toString(card5);
				winner = ("- with a full house. " + wincard + "s over " + minorcard + "s");
				return winner;
			}
		if (score == 8)
			if (card5 == card4)
			{
				if (card5 == 11)
					wincard = "Jack";
				else if (card5 == 12)
					wincard = "Queen";
				else if (card5 == 13)
					wincard = "King";
				else if (card5 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card5);
				winner = ("- with four " + wincard + "s");
				return winner;
			}
			else
			{
				if (card1 == 11)
					wincard = "Jack";
				else if (card1 == 12)
					wincard = "Queen";
				else if (card1 == 13)
					wincard = "King";
				else if (card1 == 14)
					wincard = "Ace";
				else
					wincard = Integer.toString(card1);
				winner = ("- with four " + wincard + "s");
				return winner;
			}
		else if (score == 9)
		{
			if (card5 == 11)
				wincard = "Jack";
			else if (card5 == 12)
				wincard = "Queen";
			else if (card5 == 13)
				wincard = "King";
			else if (card5 == 14)
				wincard = "Ace";
			else
				wincard = Integer.toString(card5);
			winner = ("- with a straight flush of " + suit + "s. High card is: " + wincard);
			return winner;
			
		}
		
		return "null";
	}

	static int ThirdScore(int score, int card1, int card2, int card3, int card4, int card5)
	{
//		static int SecondScore(int score, int card1, int card2, int card3, int card4, int card5, char suit1, char suit2, char suit3, char suit4, char suit5) does this function look familiar?
		{
			if (score == 1)									//I basically copy-pasted SecondScore into ThirdScore and reworked some of the stuff in it
				return card4;								//Here you can see we're using card4 instead of card5, since we know that card5 has already been compared
			if (score == 2)
				if (card4 == card5)
					return card3;							//Here we have to find the highest card that doesn't belong to a pair. If the pair is card4 and card5, we send card3
				else
					return card5;							//Otherwise, it must be card5
			if (score == 3)
				return card2;								//Here we take the value of the lower of the pairs. card2 has to be a part of that pair no matter what
			if (score == 4)
				if (card5 == card4 && card4 == card3)		//in the case that the three of a kind was card5 card4 and card3, we send card2, the next highest after them
					return card2;
				else
					return card5;							//otherwise, we use card5
			if (score == 5)
				return card5;								//no matter what if the program has reached this point the hand will result in a draw
			if (score == 6)
				return card4;								//same as the above comment
			if (score == 7)
				if (card5 == card4 && card4 == card3)	
					return card2;						//We have to determine where the three are, so that we can make sure we send the value of the two...
				else 
					return card5;						//...in hindsight though, there should never be a draw with two three of a kinds...that would mean there are six same-value cards in a deck
			if (score == 8)
				if (card5 == card4)						//in the case of a four of a kind, we have to find the odd card out. It will always be either card1...
					return card1;
				else
					return card5;						//or card5
			if (score == 9)
				return card5;							//this will result in a draw regardless, but I send it anyway
			
			return -1;								//used for testing. The program should never reach this point
	}
	}
}
//numWhite1 = Character.getNumericValue(WhiteValue1); //used for testing