package com.github.captainrosco.BlackJack;

public class Card{
	private int value;
	
	/**
	 * Create a new card with the given value.
	 * 
	 * @param value
	 *            - Card value.
	 */

	public Card(int value){
		this.value = value;
	}

	/**
	 * Get the value of the card as an int.
	 * 
	 * @return Card value.
	 */

	public int intValue(){
		return value;
	}
	
	/**
	 * Set the value of the card to the given value.
	 * 
	 * @param value
	 *            - Cards new value.
	 */

	public void setValue(int value){
		this.value = value;
	}

	/**
	 * Returns the value of the card as a String
	 */

	@Override
	public String toString(){
		return value + "";
	}
	
	@Override
	public boolean equals(Object object){
		if (object == null) {
			return false;
		}
		if (!(object instanceof Card)) {
			return false;
		}
		if (((Card) object).value == value) {
			return true;
		}
		return false;
	}
}
