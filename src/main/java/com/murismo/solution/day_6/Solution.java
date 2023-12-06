package com.murismo.solution.day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> input;

    public Solution() {
        try {
            this.input = input(Path.of("src/main/java/com/murismo/solution/day_6/inputs/input.txt").toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void solve() {

        var partOneRaces = createPartOneRaces();
        var partTwoRace = createPartTwoRace();

        System.out.printf("PART 1 = %d\n", raceChecker(partOneRaces));
        System.out.printf("PART 2 = %d\n", raceChecker(partTwoRace));

    }

    private int raceChecker(List<Race> races) {
        return races.stream().map(this::speedCalculator).reduce((a,v) -> a*v).orElseThrow();
    }

    private int speedCalculator(Race race){
        var records = 0;

        for(int t = 0; t <= race.time; t++){
            long holdingTime = race.time - t;
            long remainingTime = race.time - holdingTime;
            long distanceTravelled = remainingTime * holdingTime;
            if(distanceTravelled > race.distance){
                records++;
            }
        }
        return records;
    }

    private List<Race> createPartOneRaces(){

        var races = new ArrayList<Race>();

        var times = input.get(0).split("\\s+");
        var distances = input.get(1).split("\\s+");

        for(int i = 1; i < times.length; i++) {
            races.add(new Race(Long.parseLong(times[i]), Long.parseLong(distances[i])));
        }
        return races;
    }

    private List<Race> createPartTwoRace(){

        var races = new ArrayList<Race>();

        var time = input.get(0).replace(" ", "").replace("Time:", "");
        var distance = input.get(1).replace(" ", "").replace("Distance:", "");

        races.add(new Race(Long.parseLong(time), Long.parseLong(distance)));

        return races;
    }

    private record Race(long time, long distance){}

    private List<String> input(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
