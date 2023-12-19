﻿var input = File.ReadAllText("inputs/input.txt").Split("\n\n");

var partMapper = input.Last().Split("\n").Select(part =>
{
    return part
                .Replace("{", "")
                .Replace("}", "")
                .Split(",")
                .ToDictionary(x => x.Split("=").First(), y => int.Parse(y.Split("=").Last()));
});

var instructionMapper = input
                            .First()
                            .Split("\n")
                            .ToDictionary(x => x.Split("{").First(), y => y.Split("{").Last().Replace("}", "").Split(","));

var total = 0;

partMapper.ToList().ForEach(part =>
{
    var catalogueNumber = "in";

    while (catalogueNumber != "A" && catalogueNumber != "R")
    {
        var ratings = instructionMapper[catalogueNumber];

        foreach (var rating in ratings)
        {
            if (!rating.Contains(":"))
            {
                catalogueNumber = rating;
                break;
            }
            else
            {
                var ops = rating.Split(":");
                var partNumber = char.ToString(ops[0][0]);
                var op = char.ToString(ops[0][1]);
                var val = int.Parse(ops[0][2..]);
                var partValue = part[partNumber];
                var match = false;
                if (op == ">")
                {
                    match = partValue > val;
                }
                else
                {
                    match = partValue < val;

                }
                if (match)
                {
                    catalogueNumber = ops.Last();
                    break;

                }
            }
        };
    }

    if (catalogueNumber == "A")
    {
        total += part.Values.Sum();
    }
});

Console.WriteLine($"Part one = {total}");

