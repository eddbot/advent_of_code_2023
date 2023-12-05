using System.ComponentModel.DataAnnotations;
using System.Data;

public class SeedMapper
{
    private readonly List<SeedMapperRow> _rows;

    public SeedMapper(string data)
    {

        // first row is name which we don't care about
        _rows = data.Split("\n").ToList().Skip(1).Select(r =>
        {
            var row = r.Split(" ");
            var source = long.Parse(row[1]);
            var dest = long.Parse(row[0]);
            var range = long.Parse(row[2]);

            return new SeedMapperRow(source, source + (range - 1), dest);


        }).ToList();
    }

    public long Location(long inLocation)
    {
        var row = _rows.FirstOrDefault(r => r.Matches(inLocation), new DefaultSeedMapperRow(0, 0, 0));
        return row.OutDest(inLocation);
    }

    private record SeedMapperRow(long min, long max, long destStart)
    {
        public bool Matches(long seed) => seed >= min && seed <= max;

        public long OutDest(long seed) => seed + (destStart - min);

    }

    private record DefaultSeedMapperRow(long min, long max, long destStart) : SeedMapperRow(min, max, destStart)
    {
        public new long OutDest(long seed) => seed;

    }
}