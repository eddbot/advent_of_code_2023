# frozen_string_literal: true

class Solution

  def initialize(path)
    @grid = File.read(path, chomp: true).split("\n").map { |line| line.split('') }
  end

  def solve
    "shrug"
  end



  private

  def start_loc
    grid.each_with_index do |line, i|
      line.each_with_index do |pipe, j|
        return [i,j] if pipe == "S"
      end
    end

    raise "no starting location found!"
  end

  attr_reader :grid


end

p Solution.new('test_input.txt').solve
      # S = 💘)
      # | = ↕️ )
      # - = ↔️ )
      # F = ↙️ )
      # L = ↘️ )
      # J = ↗️ )
      # 7 = ↖️ )
      # . = 🟫)
