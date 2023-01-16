package com.thehiflyer.kata.yatzy;

import java.util.Arrays;
import java.util.List;

public class YatzyScorer {
    public int calculateScore(Category category, YatzyRoll roll) {
        int sum = 0;
        int[] diceArray = roll.getDice();
        if (category == Category.CHANCE) {
            for (int num : diceArray) {
                sum += num;
            }
        }
        if (category == Category.YATZY) {
            boolean sumShouldBeZero = false;
            //Gullig liten metod för att casta till en list.
            List<Integer> list = Arrays.stream(diceArray).boxed().toList();
            int first = list.get(0);
            for (int num : list) {
                if (num != first) {
                    sumShouldBeZero = true;
                    break;
                }
            }
            if (!sumShouldBeZero) {
                sum = 50;
            }
        }

        if (category == Category.PAIRS) {
            sum = calculatePairs(diceArray);
        }

        //TODO ÅTERKOM TILL CATEGORY?
        if (category == Category.ACES || category == Category.TWOS || category == Category.THREES
                || category == Category.FOURS || category == Category.FIVES || category == Category.SIXES) {
            sum = calculateUpperSection(diceArray, category);

        }
        return sum;
    }

    private int calculateUpperSection(int[] diceArray, Category category) {
        int expectedVal = category.getValue();
        int sum = 0;
        for (int num : diceArray) {
            if (num == expectedVal) {
                sum += num;
            }
        }
        return sum;
    }


    private int calculatePairs(int[] diceArray) {
        int temp = 0;
        for(int i = 0; i < diceArray.length; i++){
            for(int j = i+1; j < diceArray.length; j++){
                if (diceArray[i] == diceArray[j]) {
                    if (diceArray[i] > temp) {
                        temp = diceArray[i];
                    }
                }
            }
        }
        return temp * 2;
    }
}
