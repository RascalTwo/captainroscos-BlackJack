package com.github.captainrosco.BlackJack;

import java.util.Scanner;

public class BlackJack{
	private static Scanner input;
	
	public static void main(String[] args){
		boolean quit = false;
		boolean stay = false;
		String answer;
		int total = 0;
		int bet = 5;
		
		input = new Scanner(System.in);
		
		do{
			// setups and resets game
			Dealer dealer = new Dealer();
			dealer.init();
			dealer.get_PlayersHand();
			dealer.get_DealersHand();
			do{
				
				// checks if player wants to convert ACE's
				if (dealer.aceCheck()) {
					do{
						System.out.println("\nMake Ace 11? (Y/N)");
						answer = input.nextLine().trim();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
							dealer.aceConvert();
							dealer.get_PlayersHand();
							break;
						}
						else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
							break;
						}
						else{
							System.out.println("Enter Y or N");
						}
					}
					while (true);
				}
				
				// checks if player wants to hit or stay
				System.out.print("\nWould you like to hit or stay? ");
				answer = input.nextLine().trim();
				if (answer.equalsIgnoreCase("hit")) {
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
				else if (answer.equalsIgnoreCase("stay")) {
					stay = true;
				}
				else{
					System.out.println("\nPlease enter hit or stay.");
				}
			}
			while (!stay);
			System.out.println("Your Total: " + dealer.playerTotal());
			
			// checks and plays dealer
			Check:
			if (dealer.playerTotal() < 21) {
				do{
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
				
				// checks to see who wins
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
			// checks if user wants to play again.
			do{
				System.out.print("\nPlay Again? (Y/N)");
				answer = input.nextLine().trim();
				if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
					quit = true;
					break;
				}
				else if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
					quit = false;
					break;
				}
				else{
					System.out.println("Please Enter Y or N");
				}
			}
			while (true);
			
		}
		while (!quit);
		// Total bets and ends game.
		System.out.print("Your Winnings: $" + total);
	}
}