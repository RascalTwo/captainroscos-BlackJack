package com.github.captainrosco.BlackJack;

import java.util.Arrays;
import java.util.Scanner;

public class BlackJack{
	private static Scanner input;

	public static void main(String[] args){
		boolean quit = false;
		boolean stay = false;
		int total = 0;
		int bet = 5;

		input = new Scanner(System.in);

		do{ // While quit is false.
			Dealer dealer = new Dealer();
			dealer.init();
			dealer.get_PlayersHand();
			dealer.get_DealersHand();
			if (dealer.aceCheck()) {
				if (askPlayer("Make Ace 11? (Y/N): ", new String[] { "yes", "y" }, new String[] { "no", "n" })) {
					dealer.aceConvert();
					dealer.get_PlayersHand();
				}
			}
			do{ // While stay is false or player has not gone bust or gotten
				// blackjack.
				if (askPlayer("Would You Like To Hit Or Stay? (Hit/Stay): ", "hit", "stay")) {
					dealer.playerHit();
					dealer.get_PlayersHand();
					if (dealer.playerTotal() > 21) {
						System.out.println("\nSorry you've busted! You Lost $" + bet);
						total -= bet;
						break;
					}
					else if (dealer.playerTotal() == 21) {
						System.out.println("\nBLACKJACK! YOU WIN $" + bet);
						total += bet;
						break;
					}
					stay = false;
				}
				else{
					stay = true;
				}
			}
			while (!stay);
			System.out.println("Your Total: " + dealer.playerTotal());
			
			// Check who won.
			Check:
			if (dealer.playerTotal() < 21) {
				do{ // While dealer total less than 17
					dealer.dealerHit();
					System.out.println("\nDealer Draws..");
					dealer.get_DealersHand();
					if (dealer.dealerTotal() > 21) {
						System.out.println("You win. Dealer Bust");
						total += bet;
						System.out.println("You won: $" + bet);
						break Check;
					}
				}
				while (dealer.dealerTotal() < 17);
				if (dealer.playerTotal() > dealer.dealerTotal()) {
					System.out.printf("\nYou win! Your Total %s. Dealer Total: %s", dealer.playerTotal(), dealer.dealerTotal());
					total += bet;
					System.out.println("\nYou won: $" + bet);
				}
				else if (dealer.playerTotal() == dealer.dealerTotal()) {
					System.out.printf("\nDraw Game");
				}
				else{
					System.out.printf("\nYou Lose! Your Total %s. Dealer Total: %s", dealer.playerTotal(), dealer.dealerTotal());
					total -= bet;
					System.out.println("\nYou lost: $" + bet);
				}
			}
			if (askPlayer("Play Again? (Y/N): ", new String[] { "yes", "y" }, new String[] { "no", "n" })) {
				quit = false;
			}
			else{
				quit = true;
			}
		}
		while (!quit);
		System.out.print("Your Winnings: $" + total);
	}
	
	/**
	 * Asks The User A Question And Returns True Or False Depending On The
	 * Response. Will Continue Asking Until Users Response Equals One Of The
	 * Possible Options.
	 *
	 * @param message
	 *            - Question To Ask User.
	 * @param trueOptions
	 *            - Options That Will Return True.
	 * @param falseOptions
	 *            - Options That Will Return False.
	 * @return true if trueOptions contains response.
	 *         <p>
	 *         false if falseOptions contains response.
	 */
	
	public static boolean askPlayer(String message, String[] trueOptions, String[] falseOptions){
		System.out.print(message);
		String response = input.nextLine().trim();
		for (String trueOption : trueOptions){
			if (trueOption.equalsIgnoreCase(response)) {
				return true;
			}
		}
		for (String falseOption : falseOptions){
			if (falseOption.equalsIgnoreCase(response)) {
				return true;
			}
		}
		System.out.println("Please Enter '" + Arrays.toString(trueOptions) + "' Or '" + Arrays.toString(falseOptions) + "'");
		return askPlayer(message, trueOptions, falseOptions);
	}
	
	/**
	 * Asks The User A Question And Returns True Or False Depending On The
	 * Response. Will Continue Asking Until Users Response Equals One Of The
	 * Possible Options.
	 *
	 * @param message
	 *            - Question To Ask User.
	 * @param trueOption
	 *            - Options That Will Return True.
	 * @param falseOption
	 *            - Option That Will Return False.
	 * @return true if trueOption equals response.
	 *         <p>
	 *         false if falseOption equals response.
	 */
	
	public static boolean askPlayer(String message, String trueOption, String falseOption){
		return askPlayer(message, new String[] { trueOption }, new String[] { falseOption });
	}
}