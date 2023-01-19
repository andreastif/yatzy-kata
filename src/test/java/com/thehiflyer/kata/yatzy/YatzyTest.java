package com.thehiflyer.kata.yatzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

		//should be 18
		int score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 2, 1, 4, 6));
		assertEquals(18, score);

		//should be 21
		int score1 = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 5, 5, 5, 1));
		assertEquals(21, score1);

	}

	@Test
	public void chanceScoresIncorrectly() {

		//should not be -1
		int score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 2, 1, 4, 6));
		assertNotEquals(-1, score);

		//should not be 0
		int score1 = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 5, 5, 5, 1));
		assertNotEquals(0, score1);

		//should not be 4
		int score2 = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 5, 5, 5, 1));
		assertNotEquals(4, score2);

	}

	@Test
	public void yatzyScoresCorrectly() {
		//should be 50
		int score = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1, 1, 1, 1, 1));
		assertEquals(50, score);

		//should be 50
		int score2 = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(2, 2, 2, 2, 2));
		assertEquals(50, score2);
	}

	@Test
	public void yatzyScoresIncorrectly() {

		//should not be 50, should be 0
		int score = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1, 1, 2, 1, 1));
		assertEquals(0, score);

		//should not be 51, should be 50
		int score2 = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1, 1, 1, 1, 1));
		assertNotEquals(51, score2);
	}

	@Test
	public void acesScoresCorrectly() {
		//should be 0
		int score = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(0, 2, 0, 0, 6));
		assertEquals(0, score);
		//should be 1
		int score1 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1, 2, 0, 0, 6));
		assertEquals(1, score1);
		//should be 2
		int score2 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1, 2, 0, 1, 6));
		assertEquals(2, score2);
		//should be 3
		int score3 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1, 2, 1, 1, 6));
		assertEquals(3, score3);
		//should be 4
		int score4 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1, 2, 1, 1, 1));
		assertEquals(4, score4);
		//should be 5
		int score5 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1, 1, 1, 1, 1));
		assertEquals(5, score5);
	}

	@Test
	public void acesScoresIncorrectly() {
		//should not sum up to 8
		int score = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(4, 1, 4, 2, 6));
		assertNotEquals(8, score);

		//should not sum up to 18
		int score1 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(6, 1, 6, 2, 6));
		assertNotEquals(18, score1);
	}

	@Test
	public void twosScoresCorrectly() {

		//should be 0
		int score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1, 0, 1, 1, 0));
		assertEquals(0, score);

		//should be 2
		int score1 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1, 0, 1, 2, 6));
		assertEquals(2, score1);

		//should be 4
		int score2 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1, 2, 1, 2, 6));
		assertEquals(4, score2);

		//should be 6
		int score3 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1, 2, 2, 2, 6));
		assertEquals(6, score3);

		//should be 8
		int score4 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 2, 2, 2, 6));
		assertEquals(8, score4);

		//should be 10
		int score5 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 2, 2, 2, 2));
		assertEquals(10, score5);
	}

	@Test
	public void twosScoresIncorrectly() {

		//should not be 2
		int score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1, 2, 1, 2, 6));
		assertNotEquals(2, score);

		//should not be 8
		int score1 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(4, 2, 4, 2, 6));
		assertNotEquals(8, score1);
	}

	@Test
	public void threesScoresCorrectly() {
		//should be 9
		int score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 3, 3, 1, 6));
		assertEquals(9, score);

		//should be 12
		int score1 = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 3, 3, 3, 6));
		assertEquals(12, score1);
	}

	@Test
	public void threesScoresIncorrectly() {
		//should not be 4
		int score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(2, 2, 3, 1, 6));
		assertNotEquals(4, score);

		//should not be 6
		int score1 = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(2, 2, 3, 1, 2));
		assertNotEquals(6, score1);

		//should not be -1
		int score2 = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(2, 2, 0, 1, 2));
		assertNotEquals(-1, score2);
	}

	@Test
	public void foursScoresCorrectly() {

		//should be 16
		int score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4, 4, 4, 4, 6));
		assertEquals(16, score);

		//should be 20
		int score1 = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4, 4, 4, 4, 4));
		assertEquals(20, score1);
	}

	@Test
	public void foursScoresIncorrectly() {

		//should not be 2
		int score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(1, 1, 1, 1, 3));
		assertNotEquals(2, score);

		//should not be 4
		int score1 = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(1, 1, 1, 1, 3));
		assertNotEquals(4, score1);

	}

	@Test
	public void fivesScoresCorrectly() {

		//should be 10
		int score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(5, 4, 4, 4, 5));
		assertEquals(10, score);

		//should be 15
		int score1 = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(5, 4, 5, 4, 5));
		assertEquals(15, score1);
	}

	@Test
	public void fivesScoresIncorrectly() {

		//should not be 16
		int score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(0, 4, 4, 4, 0));
		assertNotEquals(16, score);

		//should not be 20
		int score1 = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(0, 4, 4, 4, 4));
		assertNotEquals(20, score1);
	}

	@Test
	public void sixesScoresCorrectly() {

		//should be 12
		int score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 4, 6, 4, 5));
		assertEquals(12, score);

		//should be 18
		int score1 = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 4, 6, 4, 6));
		assertEquals(18, score1);
	}


	@Test
	public void sixesScoresIncorrectly() {

		//should not be 8
		int score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 4, 6, 4, 5));
		assertNotEquals(8, score);

		//should not be 5
		int score1 = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 4, 6, 4, 5));
		assertNotEquals(5, score1);

	}

	@Test
	public void pairsScoresCorrectly() {

		//testar att vi får rätt resultat (2+2 = 4)
		int score = yatzyScorer.calculateScore(Category.PAIRS, new YatzyRoll(2, 2, 0, 1, 5));
		assertEquals(4, score);

		//testar att vi får rätt resultat (4+4 = 8, ej 1+1 = 2)
		int score1 = yatzyScorer.calculateScore(Category.PAIRS, new YatzyRoll(4, 4, 1, 1, 5));
		assertEquals(8, score1);
	}

	@Test
	public void pairsScoresIncorrectly() {
		//testar att vi inte får lägsta paret (1+1 = 2)
		int score = yatzyScorer.calculateScore(Category.PAIRS, new YatzyRoll(1, 1, 0, 2, 2));
		assertNotEquals(2, score);

		//testar att vi inte får lägsta paret (2+2 = 4)
		int score1 = yatzyScorer.calculateScore(Category.PAIRS, new YatzyRoll(4, 4, 0, 2, 2));
		assertNotEquals(4, score1);
	}

	@Test
	public void twoPairsScoresCorrectly() {
		// 1,1,2,3,3 scores 8 (1+1+3+3)
		int score = yatzyScorer.calculateScore(Category.TWOPAIRS, new YatzyRoll(1, 1, 2, 3, 3));
		assertEquals(8, score);

		// 1,1,2,2,2 scores 6 (1+1+2+2)
		int score2 = yatzyScorer.calculateScore(Category.TWOPAIRS, new YatzyRoll(1, 1, 2, 2, 2));
		assertEquals(6, score2);

		// 1,1,2,3,4 scores 0
		int score3 = yatzyScorer.calculateScore(Category.TWOPAIRS, new YatzyRoll(1, 1, 2, 3, 4));
		assertEquals(0, score3);
	}

	@Test
	public void twoPairsScoresIncorrectly() {
		// should NOT be 2 ( 1 + 1 )
		int score = yatzyScorer.calculateScore(Category.TWOPAIRS, new YatzyRoll(1, 1, 2, 1, 3));
		assertNotEquals(2, score);

		// should NOT be 9 ( 1 + 1 + 1, 3 + 3)
		int score2 = yatzyScorer.calculateScore(Category.TWOPAIRS, new YatzyRoll(1, 1, 1, 3, 3));
		assertNotEquals(9, score2);
	}

	@Test
	public void threeOfAKindScoresCorrectly() {
		//should be 3
		int score = yatzyScorer.calculateScore(Category.THREEOFAKIND, new YatzyRoll(1,1,1,2,2));
		assertEquals(3, score);

		//should be 9
		int score1 = yatzyScorer.calculateScore(Category.THREEOFAKIND, new YatzyRoll(3,3,3,2,2));
		assertEquals(9, score1);

		//should be 6
		int score2 = yatzyScorer.calculateScore(Category.THREEOFAKIND, new YatzyRoll(3,2,3,2,2));
		assertEquals(6, score2);
	}

	@Test
	public void threeOfAKindScoresIncorrectly() {
		//should NOT be 0
		int score = yatzyScorer.calculateScore(Category.THREEOFAKIND, new YatzyRoll(1,1,1,2,2));
		assertNotEquals(0, score);

		//should NOT be -1
		int score1 = yatzyScorer.calculateScore(Category.THREEOFAKIND, new YatzyRoll(3,3,3,2,2));
		assertNotEquals(-1, score1);

		//should NOT be 6
		int score2 = yatzyScorer.calculateScore(Category.THREEOFAKIND, new YatzyRoll(3,3,3,2,2));
		assertNotEquals(6, score2);
	}

	@Test
	public void fourOfAKindScoresCorrectly() {
		//    2,2,2,2,5 scores 8 (2+2+2+2)
		int score = yatzyScorer.calculateScore(Category.FOUROFAKIND, new YatzyRoll(2,2,2,2,5));
		assertEquals(8, score);

		//    2,2,2,5,5 scores 0
		int score1 = yatzyScorer.calculateScore(Category.FOUROFAKIND, new YatzyRoll(2,2,2,5,5));
		assertEquals(0, score1);

		//    2,2,2,2,2 scores 8 (2+2+2+2)
		int score2 = yatzyScorer.calculateScore(Category.FOUROFAKIND, new YatzyRoll(2,2,2,2,2));
		assertEquals(8, score2);
	}


	@Test
	public void fourOfAKindScoresIncorrectly() {
		// should NOT be 5
		int score = yatzyScorer.calculateScore(Category.FOUROFAKIND, new YatzyRoll(2,2,2,2,5));
		assertNotEquals(5, score);

		// should NOT be 6
		int score1 = yatzyScorer.calculateScore(Category.FOUROFAKIND, new YatzyRoll(2,2,2,5,5));
		assertNotEquals(6, score1);

		// should NOT be 10
		int score2 = yatzyScorer.calculateScore(Category.FOUROFAKIND, new YatzyRoll(2,2,2,2,2));
		assertNotEquals(10, score2);
	}

	@Test
	public void smallStraightScoresCorrectly() {
		// 1,2,3,4,5 scores SMALL STRAIGHT, 15
		int score = yatzyScorer.calculateScore(Category.SMALLSTRAIGHT, new YatzyRoll(1,2,3,4,5));
		assertEquals(15, score);

		// 2,1,3,5,4 scores SMALL STRAIGHT, 15
		int score2 = yatzyScorer.calculateScore(Category.SMALLSTRAIGHT, new YatzyRoll(2,1,3,5,4));
		assertEquals(15, score2);

	}

	@Test
	public void smallStraightScoresInCorrectly() {
		// 2,3,4,5,6 scores 0 On small Straight 1, 2, 3, 4, 5
		int score = yatzyScorer.calculateScore(Category.SMALLSTRAIGHT, new YatzyRoll(2,3,4,5,6));
		assertEquals(0, score);
		assertNotEquals(21, score);

		// (2,2,2,4,5) scores !!NOT!! SMALL STRAIGHT, 15 p
		int score2 = yatzyScorer.calculateScore(Category.SMALLSTRAIGHT, new YatzyRoll(2,2,2,4,5));
		assertEquals(0, score2);
	}

	@Test
	public void largeStraightScoresCorrectly() {
		// 2,3,4,5,6,  scores LARGE STRAIGHT, 20
		int score = yatzyScorer.calculateScore(Category.LARGESTRAIGHT, new YatzyRoll(2,3,4,5,6));
		assertEquals(20, score);

		// 6,4,3,5,2  scores LARGE STRAIGHT, 20
		int score2 = yatzyScorer.calculateScore(Category.LARGESTRAIGHT, new YatzyRoll(6,4,3,5,2));
		assertEquals(20, score2);

	}

	@Test
	public void largeStraightScoresInCorrectly() {
		// 1,2,3,4,5 scores 0 ( Large Straight )
		int score = yatzyScorer.calculateScore(Category.LARGESTRAIGHT, new YatzyRoll(1,2,3,4,5));
		assertEquals(0, score);
		assertNotEquals(15, score);

		// (5,5,2,3,5) scores 0 ( Large Straight )
		int score2 = yatzyScorer.calculateScore(Category.LARGESTRAIGHT, new YatzyRoll(5,5,2,3,5));
		assertNotEquals(20, score2);
		assertEquals(0, score2);
	}

	@Test
	public void fullHouseScoresCorrectly() {
		//    1,1,2,2,2 scores 8 (1+1+2+2+2)
		int score = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(1,1,2,2,2));
		assertEquals(8, score);

		//    2,2,3,3,4 scores 0
		int score1 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(2,2,3,3,4));
		assertEquals(0, score1);

		//    4,4,4,4,4 scores 0
		int score2 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(4,4,4,4,4));
		assertEquals(0, score2);

		//    3,4,3,4,4 scores 18
		int score3 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(3,4,3,4,4));
		assertEquals(18, score3);
	}


	@Test
	public void fullHouseScoresIncorrectly() {

		// should NOT be 6
		int score = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(1,1,2,2,2));
		assertNotEquals(6, score);

		// should NOT be 10
		int score1 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(2,2,3,3,4));
		assertNotEquals(6, score1);

		// should NOT be 6
		int score2 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(2,2,3,3,4));
		assertNotEquals(10, score2);

		// should NOT be 4
		int score3 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(2,2,3,3,4));
		assertNotEquals(4, score3);

		// should NOT be 2
		int score4 = yatzyScorer.calculateScore(Category.FULLHOUSE, new YatzyRoll(1,1,2,2,2));
		assertNotEquals(2, score4);
	}



}
