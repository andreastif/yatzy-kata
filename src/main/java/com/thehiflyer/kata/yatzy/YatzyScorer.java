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
            case THREEOFAKIND -> sum = calculateThreeOfAKind(diceArray);
            case FOUROFAKIND -> sum = calculateFourOfAKind(diceArray);
            case SMALLSTRAIGHT, LARGESTRAIGHT -> sum = calculateSmallLargeStraight(diceArray, category);
            case FULLHOUSE -> sum = calculateFullHouse(diceArray);
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

    private int calculateTwoPair(int[] diceArray) {
        int score = 0;

        // Vi Grupperar alla nummer i rollResultArray
        Map<Integer, Long> numbers = Arrays.stream(diceArray)
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


    private int calculateThreeOfAKind(int[] diceArray) {

        int val = 0;

        //tar in key-values och räknar förekomsten av key-value

        Map<Integer, Long> diceCount = Arrays.stream(diceArray).boxed().collect(Collectors.groupingBy(keyValues -> keyValues, Collectors.counting()));
        //skapar ett Map.Entry objekt med namnet valuePair. valuePair innehåller både key OCH value. Itererar över alla valuePairs i diceCount.
        //diceCount måste definieras med .entrySet() annars är det EJ en itererbar lista.
        for (Map.Entry<Integer, Long> valuePair : diceCount.entrySet()) {
            if (valuePair.getValue() >=3) {
                val += valuePair.getKey() * 3;
            }
        }
        return val;
    }

    private int calculateFourOfAKind(int[] diceArray) {
        int val = 0;

        //tar in key-values och räknar förekomsten av key-value
        Map<Integer, Long> diceCount = Arrays.stream(diceArray).boxed().collect(Collectors.groupingBy(keyValues -> keyValues, Collectors.counting()));

        //skapar ett Map.Entry objekt med namnet valuePair. valuePair innehåller både key OCH value. Itererar över alla valuePairs i diceCount.
        //diceCount måste definieras med .entrySet() annars är det EJ en itererbar lista.
        for (Map.Entry<Integer, Long> valuePair : diceCount.entrySet()) {
            if (valuePair.getValue() >=4) {
                val += valuePair.getKey() * 4;
            }
        }
        return val;
    }

    private int calculateSmallLargeStraight(int[] diceArray, Category category) {
        int score = 0;

        // Vi Grupperar alla nummer i rollResultArray
        Map<Integer, Long> numbers = Arrays.stream(diceArray)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // Om ej numbers map har 5 nycklar (5 tärningar), så fallerar allt, returnerar 0p
        if(numbers.size() == 5) {
            int straightSum = numbers.keySet().stream()
                    .reduce(0, (entryValue, nextValue) -> entryValue + nextValue);
            // score = Ternary Operator
            if (category.equals(Category.SMALLSTRAIGHT)) {
                score = (straightSum == category.getValue()) ? category.getValue() : 0; // expected 15
            } else if (category.equals(Category.LARGESTRAIGHT)) {
                score = (straightSum == category.getValue()) ? category.getValue() : 0; // expected 20
            }
        }
        return score;
    }

    private int calculateFullHouse(int[] diceArray) {
        int score = 0;
        // Vi Grupperar alla nummer i rollResultArray
        Map<Integer, Long> numbers = Arrays.stream(diceArray)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // Kolla om de finns minst 2 Entry (kåk, 2 par, 3 par tsm), om de gör, summera alla values i entry
        // tar ej TVÅPAR pga att det är en REST kvar i numbers, därav size = 3.
        if (numbers.size() == 2) {
            score += numbers.entrySet().stream()
                    .mapToInt(val -> (val.getKey() * val.getValue().intValue())).sum();
        }
        return score;
    }


}
