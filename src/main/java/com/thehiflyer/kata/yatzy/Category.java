package com.thehiflyer.kata.yatzy;

public enum Category {
	CHANCE
	// add more categories here as you implement them

	private final int value;

	Category(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}

}

