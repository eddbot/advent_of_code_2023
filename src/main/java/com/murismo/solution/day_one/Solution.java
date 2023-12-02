package com.murismo.solution.day_one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {

    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_one/input.txt").toAbsolutePath());
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
        var numbers = new ArrayList<String>();

        for (int i = 0; i < line.length(); i++) {
            for (int j = i + 1; j <= line.length(); j++) {
                var num = this.mapper.get(line.substring(i, j));
                if (num != null) {
                    numbers.add(num);
                    break;
                }
            }
        }
        return Integer.parseInt(numbers.getFirst() + numbers.getLast());
    }

    private final Map<String, String> mapper = Map.ofEntries(
            Map.entry("one", "1"),
            Map.entry("two", "2"),
            Map.entry("three", "3"),
            Map.entry("four", "4"),
            Map.entry("five", "5"),
            Map.entry("six", "6"),
            Map.entry("seven", "7"),
            Map.entry("eight", "8"),
            Map.entry("nine", "9"),
            Map.entry("1", "1"),
            Map.entry("2", "2"),
            Map.entry("3", "3"),
            Map.entry("4", "4"),
            Map.entry("5", "5"),
            Map.entry("6", "6"),
            Map.entry("7", "7"),
            Map.entry("8", "8"),
            Map.entry("9", "9"));

    private List<String> input(Path path) throws IOException {

        return Files.readAllLines(path);
    }
}
