package com.murismo.solution.day_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    private final List<Round> rounds;
    private final int id;

    private final String input;

    public Game(String input) {
        this.input = input;
        this.id = parseId();
        this.rounds = parseRounds();
    }


    public boolean isValid() {
        return rounds.stream().noneMatch(Round::isOverLimit);
    }

    public int cubeValueOfMaximum() {

        int red = rounds.stream().max(Comparator.comparingInt(Round::getRed)).orElseThrow().getRed();
        int green = rounds.stream().max(Comparator.comparingInt(Round::getGreen)).orElseThrow().getGreen();
        int blue = rounds.stream().max(Comparator.comparingInt(Round::getBlue)).orElseThrow().getBlue();

        return red * green * blue;
    }

    public int getId() {
        return this.id;
    }

    private final Pattern idPattern = Pattern.compile("(\\d+):");

    private int parseId() {

        int id;
        Matcher matcher = idPattern.matcher(input);

        if (matcher.find()) {
            id = Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException();
        }

        return id;
    }

    private List<Round> parseRounds() {
        return Arrays.stream(
                input.replaceFirst("Game \\d+:", "").split(";")
        ).map(Round::new).toList();
    }
}
