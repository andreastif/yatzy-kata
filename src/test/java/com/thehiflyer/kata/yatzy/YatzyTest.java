package com.thehiflyer.kata.yatzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class YatzyTest {

	private YatzyScorer yatzyScorer;

	@BeforeEach
	public void setUp() {
		yatzyScorer = new YatzyScorer();
	}

	@Test
	public void chanceScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 2, 1, 4, 6));
		assertEquals(18, score);
	}

	@Test
	public void yatzyScoresCorrectly(){
		int score = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1,1,1,1,1));
		assertEquals(50, score);
	}

	@Test
	public void yatzyScoresIncorrectly(){
		int score = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1,1,0,1,1));
		assertEquals(0,score);
	}

	@Test
	public void acesScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1,2,1,1,6));
		assertEquals(3, score);
	}

	@Test
	public void acesScoresIncorrectly() {
		int score = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1,1,0,7,6));
		assertNotEquals(3, score);
	}

	@Test
	public void twosScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1,2,1,2,6));
		assertEquals(4, score);
	}

	@Test
	public void twosScoresIncorrectly() {
		int score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1,2,1,2,6));
		assertNotEquals(5, score);
	}

	@Test
	public void threesScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3,3,3,1,6));
		assertEquals(9, score);
	}

	@Test
	public void threesScoresIncorrectly() {
		int score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3,3,3,1,6));
		assertNotEquals(100, score);
	}

	@Test
	public void foursScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4,4,4,4,6));
		assertEquals(16, score);
	}

	@Test
	public void foursScoresIncorrectly() {
		int score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4,4,4,4,6));
		assertNotEquals(999, score);
	}

	@Test
	public void fivesScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(5,4,4,4,5));
		assertEquals(10, score);
	}

	@Test
	public void fivesScoresIncorrectly() {
		int score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(5,4,4,4,5));
		assertNotEquals(764, score);
	}

	@Test
	public void sixesScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6,4,6,4,5));
		assertEquals(12, score);
	}


	@Test
	public void sixesScoresIncorrectly() {
		int score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6,4,6,4,5));
		assertNotEquals(112, score);
	}

	@Test
	public void pairsScoresCorrectly(){
		int score = yatzyScorer.calculateScore(Category.PAIRS, new YatzyRoll(2,2,0,1,5));
		System.out.println(score);
		assertEquals(4,score);
	}

	@Test
	public void pairsScoresIncorrectly(){
		int score = yatzyScorer.calculateScore(Category.PAIRS, new YatzyRoll(2,2,0,1,5));
		assertNotEquals(2,score);
	}
}
