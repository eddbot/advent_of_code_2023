answer = File.readlines("input.txt").map do |line|

  # part 1
  # seq = line.split.map(&:to_i)

  # part 2
  seq = line.split.map(&:to_i).reverse

  ans = seq.last
  diff = []

  while seq.uniq.size != 1 do
    updated = []
    seq.each_cons(2).each do |pair|

      updated << pair.last - pair.first

    end
    seq = updated
    diff << seq.last

  end
  ans + diff.sum
end.sum

p answer
