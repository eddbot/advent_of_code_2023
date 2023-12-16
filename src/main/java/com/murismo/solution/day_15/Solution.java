package com.murismo.solution.day_15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solution {
    private String input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_15/inputs/test_input.txt").toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void solve(){

        var library = new LensLibrary(input);

        var answer = library.process(1);

        System.out.println("part one answer = " + answer);

    }


    private String input(Path path) throws IOException {
        return Files.readString(path);
    }
}

