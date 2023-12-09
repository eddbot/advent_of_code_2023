# frozen_string_literal: true

answer = File.readlines('inputs/input.txt').map do |line|
  # part 1
  # seq = line.split.map(&:to_i)

  # part 2
  seq = line.split.map(&:to_i).reverse

  ans = seq.last
  diff = []

  while seq.uniq.size != 1
    seq = seq.each_cons(2).each_with_object([]) { |pair, arr| arr << pair.last - pair.first }
    diff << seq.last
  end

  ans + diff.sum

end.sum

puts answer
