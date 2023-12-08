# frozen_string_literal: true

class NodeTree
  def initialize(input)
    @input = input
    @node_map = {}
  end

  def self.create(...)
    new(...).create
  end

  def create
    construct_initial_node_tree
    link_nodes
  end

  private

  Node = Struct.new(:val, :left, :right)

  def construct_initial_node_tree
    input_lines.each do |raw_node|
      node = node_creator(raw_node)
      node_map[node.val] = node
    end
  end

  def link_nodes
    node_map.each_value do |v|
      v.left = node_map[v.left]
      v.right = node_map[v.right]
    end
  end

  def node_creator(raw_node)
    node_values = raw_node.gsub(' ', '').split(/\W/)

    Node.new(node_values[0], node_values[2], node_values[3])
  end

  def input_lines
    input.last.split("\n")
  end

  attr_reader :input, :node_map
end

class Solution
  def initialize(nodes, instructions)
    @nodes = nodes
    @instructions = instructions
  end

  private

  def move(node, instruction)
    return nodes[node].left.val if instruction == 'L'

    nodes[node].right.val
  end

  attr_reader :nodes, :instructions
end

class PartOne < Solution
  def solve
    starting_node = 'AAA'
    counter = 0
    instructions.split('').cycle do |i|
      break if solved?(starting_node)

      starting_node = move(starting_node, i)

      counter += 1
    end
    counter
  end

  private

  def solved?(node)
    node == 'ZZZ'
  end
end

class PartTwo < Solution
  def solve
    calc.reduce(1) { |acc, n| acc.lcm(n) }
  end

  private

  def calc
    starting_nodes.each_with_object([]) do |starting_node, routes|
      counter = 0
      instructions.split('').cycle do |i|
        break if solved?(starting_node)

        starting_node = move(starting_node, i)

        counter += 1
      end

      routes << counter
    end
  end

  def starting_nodes
    nodes.keys.filter { |k| k.end_with?('A') }
  end

  def solved?(node)
    node.end_with?('Z')
  end
end

input = File.read('./inputs/input.txt').split("\n\n")
instructions = input.first
raw_nodes = NodeTree.create(input)
part_one = PartOne.new(raw_nodes, instructions)
part_two = PartTwo.new(raw_nodes, instructions)

# part 1
puts "part 1 = #{part_one.solve}"

# part 2
puts "part 2 = #{part_two.solve}"
