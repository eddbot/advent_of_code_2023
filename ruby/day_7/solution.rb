card_mapper = {
  "A" => 14, 
  "K" => 13, 
  "Q" => 12, 
  "J" => 11, 
  "T" => 10
}

def hand_strength(freq)
  return 7 if freq.size == 1
  return 6 if freq.values.include?(4)
  return 5 if freq.size == 2 && freq.values.include?(3)
  return 4 if freq.size > 2 && freq.values.include?(3)
  return 3 if freq.size == 3 && freq.values.include?(2)
  return 2 if freq.size == 4 && freq.values.include?(1)
  return 1 if freq.size == 5

  raise "you shall not pass"
end


# how can we do this with the jacks?
def hand_strength_2(freq)

  # if the freq does not contain any jacks thennnn, we can just use the original weightings
  jacks = freq.keys.select {|k| k == "J" }.count

  return hand_strength(freq) unless freq.keys.include?("J")

  freq.delete("J")

  # the max size can be 4
  #
  return 7 if freq.size <= 1 # we must be able to make 5 of a kind here
  return 6 if freq.size < 2 && jacks.size > 1
  return 5 if freq.size < 3 && jacks.size > 2
  return 4 if freq.size < 3 && jacks.size > 3
  return 3 if freq.size < 4 && jacks.size > 3
  return 2 if freq.size == 4
  # if there is a jack we must be able to make a pair

  # we just need to find the max num and plus the jacks?
  #
  # if there is a tie breaker then it goes to the highest number it doens't matter about the number thoooo
  #
  # we sort that out when we sort. Just give it to anyone lol


  # highest freq card
  # if we get here we know the freq contains a jack btw

  # [ K K K J J ] -> find highest freq card
  # [ 2 3 4 5 J ] -> find highest card
  # [ 2 2 J 3 3 ] -> find highest card out of the highest freqs




  raise "you shall not pass"
end

card_mapper = {
  "A" => 14, 
  "K" => 13, 
  "Q" => 12, 
  "J" => 11, 
  "T" => 10, 
}

card_mapper_2 = {
  "A" => 14, 
  "K" => 13, 
  "Q" => 12, 
  "T" => 10, 
  "J" => 1, 
}

Hand = Struct.new(:hand_strength, :cards, :weightings, :bid) do
  include Comparable
  def <=>(other)
    if hand_strength == other.hand_strength
      weightings.size.times do |i|
        next if weightings[i] == other.weightings[i]

        return weightings[i] <=> other.weightings[i]
      end

    end
    hand_strength <=> other.hand_strength
  end


end

part_1 = File.readlines("input.txt").map do |line|
  cards = line.split.first.split("")
  bid = line.split.last

  card_freq = cards.tally

  hand_strength = hand_strength(card_freq)

  card_weightings = cards.map {|card| card_mapper.fetch(card, card.to_i) }

  Hand.new(hand_strength: hand_strength, cards: cards, weightings: card_weightings, bid: bid.to_i)
end.sort

part_2 = File.readlines("input.txt").map do |line|
  cards = line.split.first.split("")
  bid = line.split.last

  card_freq = cards.tally

  hand_strength = hand_strength_2(card_freq)

  card_weightings = cards.map {|card| card_mapper_2.fetch(card, card.to_i) }

  Hand.new(hand_strength: hand_strength, cards: cards, weightings: card_weightings, bid: bid.to_i)
end.sort


total = 0

# part_1.each_with_index { |hand,i| total += (hand.bid * (i+1)) }
part_2.each_with_index { |hand,i| total += (hand.bid * (i+1)) }

puts total

