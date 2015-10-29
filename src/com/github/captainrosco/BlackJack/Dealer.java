package com.github.captainrosco.BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Dealer{
	Random					random	= new Random();
	private ArrayList<Card>	playersHand;
	private ArrayList<Card>	dealersHand;
	private int				dealerTotal;
	private int				playerTotal;

	/**
	 * Draw two cards for both player & dealer.
	 */

	public Dealer(){
		playersHand = new ArrayList<Card>();
		dealersHand = new ArrayList<Card>();
		playersHand.add(draw_card());
		playersHand.add(draw_card());
		for (Card card : playersHand){
			playerTotal += card.intValue();
		}
		dealersHand.add(draw_card());
		dealersHand.add(draw_card());
		for (Card card : dealersHand){
			dealerTotal += card.intValue();
		}
	}
	
	/**
	 * Draw a new card with a value between 1 and 10
	 *
	 * @return Card with a value of 1-10
	 */
	
	public Card draw_card(){
		Card card = new Card(random.nextInt(10) + 1);
		return card;
	}

	/**
	 * Output the contents of the dealers hand.
	 */

	public void get_DealersHand(){
		System.out.print("Dealers Hand: ");
		for (Card card : dealersHand){
			System.out.print(card.intValue() + " ");
		}

	}

	/**
	 * Output the contents of the players hand.
	 */

	public void get_PlayersHand(){
		System.out.print("Your Hand: ");
		for (Card card : playersHand){
			System.out.print(card.intValue() + " ");
		}
	}

	/**
	 * Add a random card to the players hand.
	 */

	public void playerHit(){
		Card card = draw_card();
		playersHand.add(card);
		playerTotal += card.intValue();
	}

	/**
	 * Add a random card to the dealers hand.
	 */

	public void dealerHit(){
		Card card = draw_card();
		dealersHand.add(card);
		dealerTotal += card.intValue();
	}

	/**
	 * Get the value of all the cards within the players hand added together.
	 *
	 * @return Value of all cards within players hand added together.
	 */

	public int playerTotal(){
		return playerTotal;
	}
	
	/**
	 * Get the value of all the cards within the dealers hand added together.
	 *
	 * @return Value of all cards within dealers hand added together.
	 */

	public int dealerTotal(){
		return dealerTotal;
	}

	/**
	 * Checks if the players hand contains a card with a value of 1.
	 *
	 * @return true if players hand contains a card with a value of 1.
	 *         <p>
	 *         false if players hand does not contain a card with a value of 1.
	 */

	public boolean aceCheck(){
		if (playersHand.contains(new Card(1))) {
			return true;
		}
		return false;
	}

	/**
	 * Change the value of the first card within the players hand that is a 1 to
	 * a 11.
	 */

	public void aceConvert(){
		if (aceCheck()) {
			playersHand.get(playersHand.indexOf(new Card(1))).setValue(11);
			playerTotal += 10;
			System.out.println("Ace Converted");
		}
	}
}