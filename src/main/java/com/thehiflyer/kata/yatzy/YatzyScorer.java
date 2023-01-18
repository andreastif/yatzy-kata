package com.thehiflyer.kata.yatzy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YatzyScorer {
    public int calculateScore(Category category, YatzyRoll roll) {
        int sum = 0;
        int[] diceArray = roll.getDice();

        switch (category) {
            case CHANCE -> sum = calculateChance(diceArray);
            case YATZY -> sum = caclulateYatzy(diceArray);
            case ACES, SIXES, TWOS, THREES, FOURS, FIVES -> sum = calculateUpperSection(diceArray, category);
            case PAIRS -> sum = calculatePairs(diceArray);
            case TWOPAIRS -> sum = calculateTwoPair(diceArray);
            case THREEOFAKIND -> sum = calculateThreeOfAkind(diceArray);
        }
        return sum;
    }


    private int calculateThreeOfAkind(int[] diceArray) {
        int val = 0;
        Map<Integer, Long> diceCount = Arrays.stream(diceArray).boxed().collect(Collectors.groupingBy(values -> values, Collectors.counting()));
        for (Map.Entry<Integer, Long> valuePair : diceCount.entrySet()) {
            if (valuePair.getValue() >=3) {
                val += valuePair.getKey() * 3;
            }
        }
        return val;
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

    private int calculateTwoPair(int[] rollResultArray) {
        int score = 0;

        // Vi Grupperar alla nummer i rollResultArray
        Map<Integer, Long> numbers = Arrays.stream(rollResultArray)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Sparar alla par till en ny map där alla values är minst 2 eller mer
        Map<Integer, Long> filteredPairs = numbers.entrySet().stream()
                .filter(key -> key.getValue() >= 2)
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));


        // Kolla om de finns minst 2 Entry, om de gör, summera alla values i entry
        if (filteredPairs.size() >= 2) {
            score += filteredPairs.keySet().stream()
                    .mapToInt(val -> val.intValue() * 2).sum();
        }

        return score;
    }
}
