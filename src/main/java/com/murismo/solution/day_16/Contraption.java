package com.murismo.solution.day_16;

import java.util.Arrays;
import java.util.List;

public class Contraption {

    private final char[][] grid;
    private String[][] visited;
    private final int HEIGHT;
    private final int WIDTH;


    private int energised = 0;

    public Contraption(List<String> input) {

        HEIGHT = input.size();
        WIDTH = input.get(0).length();
        var grid = new char[HEIGHT][WIDTH];
        var visited = new String[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            var line = input.get(i).toCharArray();
            for( int j = 0; j < WIDTH; j++){
               grid[i][j] = line[j];
               visited[i][j] = "";
            }
        }
        this.grid = grid;
        this.visited = visited;
    }

    private void resetVisited(){
        for (int i = 0; i < HEIGHT; i++) {
            for( int j = 0; j < WIDTH; j++){
                this.visited[i][j] = "";
            }
        }
    }

    private int energised(){
        var counter = 0;
        for (String[] lines : visited) {
            for (String line : lines) {
                if(!line.isEmpty()){
                    counter++;
                }
            }
        }
        resetVisited();

        return counter;
    }


    public void partTwo(){

        int best = 0;
        int counter;

        // down
        for (int i = 0; i <= WIDTH; i++) {
            beam(0, i, 'D');
            counter = energised();
            if(counter > best) {
                best = counter;
            }
        }

//         up
        for (int i = 0; i <= WIDTH; i++) {
            beam(HEIGHT, i, 'U');
            counter = energised();
            if(counter > best) {
                best = counter;
            }
        }

//         right
        for (int i = 0; i <= HEIGHT; i++) {
            beam(i, 0 , 'R');
            counter = energised();
            if(counter > best) {
                best = counter;
            }
        }

        // left

        for (int i = 0; i <= HEIGHT; i++) {
            beam(i, WIDTH , 'L');
            counter = energised();
            if(counter > best) {
                best = counter;
            }
        }
//
        System.out.println(best);
    }

    public int on() {

        // we start at 0,0 and go right
        beam(0,0, 'R');

        var counter = 0;

        for (String[] lines : visited) {
            for (String line : lines) {
                if(!line.isEmpty()){
                    counter++;
                }
            }
        }

        System.out.println(counter);

        return -1;
    }

    private void beam(int i, int j, char direction){



        if(i < 0 || i >= grid.length || j < 0 || j >= grid.length){
            return;
        }

        if(visited[i][j].contains(Character.toString(direction))) {
            return;
        }

        var caret = switch (direction){
            case 'R' -> '>';
            case 'L' -> '<';
            case 'D' -> 'v';
            case 'U' -> '^';
            default -> throw new RuntimeException();
        };

        var square = grid[i][j];
        visited[i][j] += direction;

//        for (int k = 0; k < grid.length; k++) {
//            for (int l = 0; l < grid[k].length; l++) {
//
//                if(k == i && j == l){
//                    System.out.print(caret);
//                } else {
//                    System.out.print(grid[k][l]);
//                }
//            }
//
//            System.out.println();
//
//        }


        switch (square){
            case '.' -> {
                switch (direction) {
                    case 'R' -> beam(i, j + 1, direction);
                    case 'L' -> beam(i, j - 1, direction);
                    case 'U' -> beam(i - 1, j, direction);
                    case 'D' -> beam(i + 1, j, direction);
                }
            }
            case '|' -> {
                switch (direction){
                    case 'R', 'L' -> {
                        beam(i-1, j, 'U');
                        beam(i+1, j, 'D');
                    }
                    case 'U' -> beam(i-1, j, direction);
                    case 'D' -> beam(i+1, j, direction);
                }
            }
            case '-' -> {
                switch (direction){
                    case 'U', 'D' -> {
                        beam(i, j+1, 'R');
                        beam(i, j-1, 'L');
                    }
                    case 'R' -> beam(i, j+1, direction);
                    case 'L' -> beam(i, j-1, direction);
                }
            }
            case '/' -> {
                switch (direction){
                    case 'U' -> beam(i, j+1, 'R');
                    case 'D' -> beam(i, j-1, 'L');
                    case 'R' -> beam(i-1, j, 'U');
                    case 'L' -> beam(i+1, j, 'D');
                }
            }
            case '\\' -> {
                switch (direction){
                    case 'U' -> beam(i, j-1, 'L');
                    case 'D' -> beam(i, j+1, 'R');
                    case 'R' -> beam(i+1, j, 'D');
                    case 'L' -> beam(i-1, j, 'U');
                }
            }
            default -> throw new RuntimeException(Character.toString(square));
        }
    }

}
