package com.murismo.solution.day_18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solution {
    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_18/inputs/input.txt").toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void solve(){
        var lavaductLagoon = new LavaductLagoon(input);
        lavaductLagoon.partOne();
        lavaductLagoon.partTwo();
    }

    private List<String> input(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
