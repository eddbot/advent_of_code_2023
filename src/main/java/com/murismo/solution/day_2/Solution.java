package com.murismo.solution.day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solution {
    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_2/inputs/input.txt").toAbsolutePath());
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
        return this.input.stream()
                .map(Game::new)
                .filter(Game::isValid)
                .map(Game::getId)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private int partTwo() {
        return this.input.stream()
                .map(Game::new)
                .map(Game::cubeValueOfMaximum)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private List<String> input(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
