package com.murismo.solution.day_one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Solution {

    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_one/inputs/input.txt").toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void solve() {

        System.out.printf("PART 1 = %d\n", partOne());
        System.out.printf("PART 2 = %d\n", partTwo());

    }

    private int partOne() {
        return this.input
                .stream()
                .map(this::numParser)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private int partTwo() {
        return this.input
                .stream()
                .map(this::numAndWordParser)
                .reduce(Integer::sum)
                .orElseThrow();

    }

    private int numParser(String line) {
        var nums = List.of(line.replaceAll("\\D", "").split(""));
        return Integer.parseInt(nums.getFirst() + nums.getLast());
    }

    private int numAndWordParser(String line) {
        var pattern = Pattern.compile("(?=(one|1|two|2|three|3|four|4|five|5|six|6|seven|7|eight|8|nine|9))",
                Pattern.MULTILINE);
        var matcher = pattern.matcher(line);
        var results = matcher.results().map(result -> wordToNum(result.group(1))).toList();
        return results.getFirst() * 10 + results.getLast();
    }

    private int wordToNum(String word) {
        return switch (word) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(word);
        };
    }

    private List<String> input(Path path) throws IOException {

        return Files.readAllLines(path);
    }
}
