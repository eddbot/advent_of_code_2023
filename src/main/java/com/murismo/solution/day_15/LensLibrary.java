package com.murismo.solution.day_15;

import java.util.Arrays;
import java.util.List;

public class LensLibrary {
    private final String[] steps;

    public LensLibrary(String input) {
        this.steps = input.split(",");
    }

    public long process(int part) {

        return switch (part){
            case 1 -> processPartOne();
            case 2 -> processPartTwo();
            default -> throw new RuntimeException("part 1 or 2 please");
        };


    }


    private long processPartOne(){
        long total = 0;
        for (String step : steps) {
            total += hash(step);
        }
        return total;
    }

    private long processPartTwo() {
        return 0;
    }

    private long hash(String step){
        var currentValue = 0;
        var s = step.toCharArray();

        for (char c : s) {
            currentValue += c;
            currentValue *= 17;
            currentValue %= 256;
        }

        return currentValue;
    }
}
