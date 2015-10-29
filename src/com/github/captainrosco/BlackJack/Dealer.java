package com.github.captainrosco.BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Dealer{
	Random						random	= new Random();
	private ArrayList<Integer>	playersHand;
	private ArrayList<Integer>	dealersHand;
	private int					dealerTotal;
	private int					playerTotal;

	public int draw_card(){
		int card = random.nextInt(10 - 1 + 1) + 1;
		return card;
	}

	public void init(){
		playersHand = new ArrayList<Integer>();
		dealersHand = new ArrayList<Integer>();
		playersHand.add(draw_card());
		playersHand.add(draw_card());
		for (int cards : playersHand){
			playerTotal += cards;
		}
		dealersHand.add(draw_card());
		dealersHand.add(draw_card());
		for (int cards : dealersHand){
			dealerTotal += cards;
		}
	}

	public void get_DealersHand(){
		System.out.print("Dealers Hand: ");
		for (int cards : dealersHand){
			System.out.print(cards + " ");
		}

	}

	public void get_PlayersHand(){
		System.out.print("Your Hand: ");
		for (int cards : playersHand){
			System.out.print(cards + " ");
		}
	}

	public void playerHit(){
		int card = draw_card();
		playersHand.add(card);
		playerTotal += card;
	}

	public void dealerHit(){
		int card = draw_card();
		dealersHand.add(card);
		dealerTotal += card;
	}

	public int playerTotal(){
		return playerTotal;
	}

	public int dealerTotal(){
		return dealerTotal;
	}

	public boolean aceCheck(){
		if (playersHand.contains(1)) {
			return true;
		}
		return false;
	}

	public void aceConvert(){
		if (playersHand.contains(1)) {
			int index = playersHand.indexOf(1);
			playersHand.set(index, 11);
			System.out.println("Ace Converted");
		}
	}
}