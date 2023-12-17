package com.murismo.solution.day_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Solution {

    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_13/inputs/input.txt").toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void solve() {
        // input.stream().map(this::partOne).forEach(System.out::println);
        // var p1 = input.stream().map(this::partOne).reduce(Integer::sum).orElseThrow();
        var p2 = input.stream().map(this::partTwo).reduce(Integer::sum).orElseThrow();
        // for part 2 we just need to flip one # or . and reattempt
        // find the reflection point innit
        // part two
        // System.out.println(p1);
        System.out.println(p2);
    }

    public int partTwo(String puzzle){
        // this is an individual puzzle
        // the reflection point must be different than before lol
        // but there could still be the same reflection point
        // we just need to keep track of the reflection points and don't duplicate them
        int[] initialRp = partOne(puzzle);
        System.out.println("||" + Arrays.toString(initialRp) + "||");
        int rowRP = initialRp[0];
        int colRP = initialRp[1];
        int best = 0;
        // we need it as char arrays really, but rejoin to a singular string before sending to part one

        for(int i = 0; i < puzzle.length(); i++){

            char c = puzzle.charAt(i);
            String puz = switch (c) {
                case '.' -> puzzle.substring(0, i) + '#' + puzzle.substring(i + 1);
                case '#' -> puzzle.substring(0, i) + '.' + puzzle.substring(i + 1);
                default -> puzzle;
            };

            if(puz.equals(puzzle)){
                continue;
            }

            var check = partOne(puz);


            System.out.println(Arrays.toString(check));


        }
        System.out.println("-------------");

        return best;

    }

    public int[] partOne(String puzzle){
        var lines = puzzle.split("\n");

        // return the higher of the two?
        var rowCounter = -1;
        var columnCounter = -1;


        // check columns
        for(int col = 1; col < lines[0].length(); col++){
            var sb = new StringBuilder();
            var sb2 = new StringBuilder();
            for(int i =0; i < lines.length; i++){
                sb.append(lines[i].charAt(col-1));
                sb2.append(lines[i].charAt(col));
            }

            if(sb.toString().equals(sb2.toString())){
                var c1 = col-1;
                var c2 = col;
                var left = sb;
                var right = sb2;

                while(left.toString().equals(right.toString())){
                    left = new StringBuilder();
                    right = new StringBuilder();
                    c1--;
                    c2++;

                    if(c1 < 0 || c2 >= lines[0].length() ){
                        if(col > columnCounter){
                            columnCounter = col;
                        }
                        break;
                    }
                    for(int i =0; i < lines.length; i++){
                        left.append(lines[i].charAt(c1));
                        right.append(lines[i].charAt(c2));
                    }
                }
            }
        }


        // check rows
        for (int rp = 1; rp < lines.length; rp++) {
            var one = lines[rp-1];
            var two = lines[rp];
            if(one.equals(two)){
                var up = rp-1;
                var down = rp;
                while(one.equals(two)){
                    if(up < 0 || down >= lines.length){
                        // if we make it out of bounds, I am calling that reflected
                        if(rp > rowCounter){
                            rowCounter = rp;
                        }
                        break;
                    }
                    one = lines[up--];
                    two = lines[down++];
                }
            }
        }

        // we could just return both?
        // part one
        // look lets be honest, they both can be set

        return new int[]{rowCounter*100, columnCounter};

    }

    private List<String> input(Path path) throws IOException {
        return List.of(Files.readString(path).split("\n\n"));
    }
}
