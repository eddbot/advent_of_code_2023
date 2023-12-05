using System.Data;
using System.Globalization;

var file = await File.ReadAllTextAsync("input.txt");
var lines = file.Split("\n\n");
var seeds = lines[0].Split("seeds: ")[1].Split(" ").Select(long.Parse);

var calculator = new SeedCalculator(lines);


var p1 = seeds.ToList().Select(calculator.calculateLocation).ToList().Min();
var p2 = long.MaxValue;


// brute force yolo
seeds.ToList().Chunk(2).ToList().ForEach(chunk =>
{
    Console.WriteLine("new chunk started");
    for (long i = 0; i <= chunk[1]; i++)
    {
        var seed = calculator.calculateLocation(chunk[0] + i);

        if (seed < p2)
        {
            p2 = seed;
        }

    }
});

Console.WriteLine(p1);
Console.WriteLine(p2);






public class SeedCalculator
{
    private readonly SeedMapper _seedToSoil;
    private readonly SeedMapper _soilToFertilizer;
    private readonly SeedMapper _fertilizerToWater;
    private readonly SeedMapper _waterToLight;
    private readonly SeedMapper _lightToTemperature;
    private readonly SeedMapper _temperatureToHumidity;
    private readonly SeedMapper _humidityToLocation;

    public SeedCalculator(string[] lines)
    {

        _seedToSoil = new SeedMapper(lines[1]);
        _soilToFertilizer = new SeedMapper(lines[2]);
        _fertilizerToWater = new SeedMapper(lines[3]);
        _waterToLight = new SeedMapper(lines[4]);
        _lightToTemperature = new SeedMapper(lines[5]);
        _temperatureToHumidity = new SeedMapper(lines[6]);
        _humidityToLocation = new SeedMapper(lines[7]);

    }

    public long calculateLocation(long seed)
    {
        seed = _seedToSoil.Location(seed);
        seed = _soilToFertilizer.Location(seed);
        seed = _fertilizerToWater.Location(seed);
        seed = _waterToLight.Location(seed);
        seed = _lightToTemperature.Location(seed);
        seed = _temperatureToHumidity.Location(seed);
        seed = _humidityToLocation.Location(seed);

        return seed;
    }
}