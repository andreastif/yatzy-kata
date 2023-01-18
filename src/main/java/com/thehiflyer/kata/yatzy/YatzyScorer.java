package com.thehiflyer.kata.yatzy;

import java.util.Arrays;
import java.util.List;

public class YatzyScorer {
    public int calculateScore(Category category, YatzyRoll roll) {
        int sum = 0;
        int[] diceArray = roll.getDice();

        switch (category) {
            case CHANCE -> sum = calculateChance(diceArray);
            case YATZY -> sum = caclulateYatzy(diceArray);
            case ACES, SIXES, TWOS, THREES, FOURS, FIVES -> sum = calculateUpperSection(diceArray, category);
            case PAIRS -> sum = calculatePairs(diceArray);
        }
        return sum;
    }

    private int calculateChance(int[] diceArray) {
        int val = 0;
        for (int num : diceArray) {
            val += num;
        }
        return val;
    }

    private int caclulateYatzy(int[] diceArray) {
        boolean sumShouldBeZero = false;
        //Gullig liten metod för att casta till en list.
        List<Integer> list = Arrays.stream(diceArray).boxed().toList();
        int first = list.get(0);
        int val = 0;
        for (int num : list) {
            if (num != first) {
                sumShouldBeZero = true;
                break;
            }
        }
        if (!sumShouldBeZero) {
            val = 50;
        }
        return val;
    }

    private int calculateUpperSection(int[] diceArray, Category category) {
        int expectedVal = category.getValue();
        int val = 0;
        for (int num : diceArray) {
            if (num == expectedVal) {
                val += num;
            }
        }
        return val;
    }


    private int calculatePairs(int[] diceArray) {
        int val = 0;
        for(int i = 0; i < diceArray.length; i++){
            for(int j = i+1; j < diceArray.length; j++){
                if (diceArray[i] == diceArray[j]) {
                    if (diceArray[i] > val) {
                        val = diceArray[i];
                    }
                }
            }
        }
        return val * 2;
    }
}
