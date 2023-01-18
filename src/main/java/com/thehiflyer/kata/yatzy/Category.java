package com.thehiflyer.kata.yatzy;


public enum Category {
	CHANCE(0),
	YATZY(0),
	ACES(1),
	TWOS(2),
	THREES(3),
	FOURS(4),
	FIVES(5),
	SIXES(6),
	PAIRS(0),
	TWOPAIRS(0),
	THREEOFAKIND(0),
	FOUROFAKIND(0);
	// add more categories here as you implement them

	private final int value;

	Category(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}

}

