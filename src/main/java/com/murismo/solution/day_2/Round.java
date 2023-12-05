package com.murismo.solution.day_2;

import java.util.Arrays;

public class Round {

    private final int MAX_RED = 12;
    private final int MAX_GREEN = 13;
    private final int MAX_BLUE = 14;

    private int red = 0;
    private int green = 0;
    private int blue = 0;

    public Round(String input) {
        Arrays.stream(input.split(", ")).forEach(line -> {
            var round = line.trim().split(" ");
            switch (round[1]) {
                case "red" -> this.red = Integer.parseInt(round[0]);
                case "green" -> this.green = Integer.parseInt(round[0]);
                case "blue" -> this.blue = Integer.parseInt(round[0]);
                default -> throw new IllegalArgumentException("don't recognise that colour");
            }
        });
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }


    public int getBlue() {
        return blue;
    }

    public boolean isOverLimit() {
        return isOverRedLimit() || isOverGreenLimit() || isOverBlueLimit();
    }

    private boolean isOverRedLimit() {
        return this.red > MAX_RED;
    }

    private boolean isOverGreenLimit() {
        return this.green > MAX_GREEN;

    }

    private boolean isOverBlueLimit() {
        return this.blue > MAX_BLUE;
    }

    @Override
    public String toString() {
        return "Round{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
