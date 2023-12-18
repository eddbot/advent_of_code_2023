package com.murismo.solution.day_18;

import java.util.ArrayList;
import java.util.List;

public class LavaductLagoon {


    private final List<String> input;


    public LavaductLagoon(List<String> input) {
        this.input = input;

    }

    public void partOne() {
        calculate(1);
    }

    public void partTwo() {
        calculate(2);
    }

    private record Point(long x, long y){};

    private void calculate(int part) {
        long i = 0;
        long j = 0;
        var points = new ArrayList<Point>();
        points.add(new Point(0,0));
        var totalDistance = 0;

        for (String line : input) {
            var instructions = line.split(" ");
            var direction = instructions[0];
            var distance = Integer.parseInt(instructions[1]);

            if(part == 2){
                var hex = instructions[2]
                        .replace("(", "")
                        .replace(")", "")
                        .replace("#", "");

                var code = hex.substring(0, 5);

                distance = Integer.parseInt(code, 16);

                direction = switch (hex.substring(5,6)) {
                    case "0" -> "R";
                    case "1" -> "D";
                    case "2" -> "L";
                    case "3" -> "U";
                    default -> throw new RuntimeException();
                };
            }
            totalDistance+=distance;

            switch (direction) {
                case "R" -> j+=distance;
                case "L" -> j-=distance;
                case "U" -> i-=distance;
                case "D" -> i+=distance;
                default -> throw new RuntimeException();
            }

            points.add(new Point(i,j));
        }

        long x = 0;
        long y = 0;

        for (int k = 0; k < points.size() - 1; k++) {
            Point pA = points.get(k);
            Point pB = points.get(k+1);

            x += (pA.x * pB.y);
            y += (pA.y * pB.x);
        }

        // I = A - (R / 2) + 1
        var shoelaceArea = Math.abs(x-y) / 2;

        var picksRevised = shoelaceArea - (totalDistance/2) + 1;

        System.out.printf("part %d = %d \n",part, picksRevised+totalDistance);
    }
}
