package com.murismo.solution.day_16;

import java.util.List;

public class Contraption {

    private final char[][] grid;
    private String[][] visited;
    private final int HEIGHT;
    private final int WIDTH;

    public Contraption(List<String> input) {

        HEIGHT = input.size();
        WIDTH = input.get(0).length();
        var grid = new char[HEIGHT][WIDTH];
        var visited = new String[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            var line = input.get(i).toCharArray();
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = line[j];
                visited[i][j] = "";
            }
        }
        this.grid = grid;
        this.visited = visited;
    }


    private int energised() {
        var counter = 0;
        for (String[] lines : visited) {
            for (String line : lines) {
                if (!line.isEmpty()) {
                    counter++;
                }
            }
        }
        resetVisited();

        return counter;
    }

    private void resetVisited() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                this.visited[i][j] = "";
            }
        }
    }

    public void partOne() {
        // we start at 0,0 and go right
        beam(0, 0, 'R');

        System.out.println(energised());
    }

    public void partTwo() {

        int best = 0;

        for (int i = 0; i < HEIGHT; i++) {
            beam(i, 0, 'R');
            var counter = energised();
            if (counter > best) {
                best = counter;
            }

            beam(i, WIDTH - 1, 'L');
            counter = energised();
            if (counter > best) {
                best = counter;
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            beam(0, i, 'D');
            var counter = energised();
            if (counter > best) {
                best = counter;
            }

            beam(HEIGHT - 1, i, 'U');
            counter = energised();
            if (counter > best) {
                best = counter;
            }
        }


        System.out.println(best);
    }


    private void beam(int i, int j, char direction) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length) {
            return;
        }

        if (visited[i][j].contains(Character.toString(direction))) {
            return;
        }

        var square = grid[i][j];
        visited[i][j] += direction;

        switch (square) {
            case '.' -> {
                switch (direction) {
                    case 'R' -> beam(i, j + 1, direction);
                    case 'L' -> beam(i, j - 1, direction);
                    case 'U' -> beam(i - 1, j, direction);
                    case 'D' -> beam(i + 1, j, direction);
                }
            }
            case '|' -> {
                switch (direction) {
                    case 'R', 'L' -> {
                        beam(i - 1, j, 'U');
                        beam(i + 1, j, 'D');
                    }
                    case 'U' -> beam(i - 1, j, direction);
                    case 'D' -> beam(i + 1, j, direction);
                }
            }
            case '-' -> {
                switch (direction) {
                    case 'U', 'D' -> {
                        beam(i, j + 1, 'R');
                        beam(i, j - 1, 'L');
                    }
                    case 'R' -> beam(i, j + 1, direction);
                    case 'L' -> beam(i, j - 1, direction);
                }
            }
            case '/' -> {
                switch (direction) {
                    case 'U' -> beam(i, j + 1, 'R');
                    case 'D' -> beam(i, j - 1, 'L');
                    case 'R' -> beam(i - 1, j, 'U');
                    case 'L' -> beam(i + 1, j, 'D');
                }
            }
            case '\\' -> {
                switch (direction) {
                    case 'U' -> beam(i, j - 1, 'L');
                    case 'D' -> beam(i, j + 1, 'R');
                    case 'R' -> beam(i + 1, j, 'D');
                    case 'L' -> beam(i - 1, j, 'U');
                }
            }
            default -> throw new RuntimeException(Character.toString(square));
        }
    }

}
