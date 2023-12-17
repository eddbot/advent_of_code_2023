package com.murismo.solution.day_14;

import java.util.Arrays;
import java.util.List;

public class ReflectorDish {

    private char[][] grid;

    public ReflectorDish(List<String> input){
        var grid = new char[input.size()][input.getFirst().length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.getFirst().length();j++){
                grid[i][j]=input.get(i).charAt(j);
            }
        }

        this.grid = grid;
    }

    public int tilt() {

        var moving = 10000; // there is a problem with my moving calcs!
        // this needs to be fixed before part 2

        do {
            for (int i = grid.length-1; i >= 0; i--) {
                for (int j = 0; j < grid[i].length; j++) {
                    var currentRock = grid[i][j];

                    if (currentRock == 'O') {

                        if(i-1 == -1){
                            moving--;
                            continue;
                        }
                        // roll
                        if (grid[i - 1][j] == '.') {
                            grid[i][j] = '.';
                            grid[i - 1][j] = 'O';
                            moving++;
                        }
                        // no roll
                        if(grid[i-1][j] == '#'){
                            moving--;
                        }

                    }

                }
            }

        } while(moving >= 0);

        var answer = calculate();

        for (char[] chars : grid) {
            System.out.println(chars);

        }

        return answer;
    }

    private int calculate(){

        var total = 0;

        for (int i = grid.length-1; i >= 0 ; i--) {
            var rocksOnRow = 0;
            for (int j = 0; j < grid[i].length; j++){
                var currentRock = grid[i][j];
                if(currentRock == 'O'){
                    if(i-1 >= 0){

                        if(grid[i-1][j] == '.'){
                            grid[i-1][j] = 'X';
                            System.out.println("we found a phony on row " + i);
                        }

                    }
                    rocksOnRow++;
                }
            }
            var weighting = grid.length - i;

//            System.out.println("row = " + weighting + "rocks = " + rocksOnRow);


            total += (rocksOnRow * weighting);
        }

        return total;

    }
}
