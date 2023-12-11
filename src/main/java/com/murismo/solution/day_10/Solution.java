package com.murismo.solution.day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private int counter;
    private boolean[][] visited;
    private String[][] gfx;
    private List<List<String>> grid;
    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_10/inputs/input.txt").toAbsolutePath());
            this.grid = input.stream().map(line -> Arrays.stream(line.split("")).toList()).toList();
            this.visited  = new boolean[grid.size()][grid.getFirst().size()];
            this.gfx  = new String[grid.size()][grid.getFirst().size()];
            for(int i = 0; i < visited.length; i++){
                for(int j = 0; j < visited[0].length; j++){
                    gfx[i][j] = ".";

                }
            }
            this.counter = 0;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void solve(){
        System.out.println("Part 1=" + partOne());
        // partTwo();
    }

    private int partOne() {
        // hardcoded as i'm scum :)
        int i = 42;
        int j = 8;

        while (true) {
            counter++;
            visited[i][j] = true;
            gfx[i][j] = "x";
            String pipe = grid.get(i).get(j);

            if ((pipe.equals("F") || pipe.equals("7") || pipe.equals("|")) && !visited[i + 1][j]) {
                i += 1;
                continue;
            }

            if ((pipe.equals("|") || pipe.equals("J") || pipe.equals("L")) && !visited[i - 1][j]) {
                i -= 1;
                continue;
            }

            if ((pipe.equals("-") || pipe.equals("L") || pipe.equals("F")) && !visited[i][j + 1]) {
                j += 1;
                continue;
            }

            if ((pipe.equals("J") || pipe.equals("-") || pipe.equals("7")) && !visited[i][j - 1]) {
                j -= 1;
                continue;
            }

            // if we get here the pipe is complete :)
            return counter/2;
        }
    }

    private void partTwo(){

        for(var line : gfx){

            // how do we figure this out? :)
            System.out.println(Arrays.toString(line));
        }
    }

      private List<String> input(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
