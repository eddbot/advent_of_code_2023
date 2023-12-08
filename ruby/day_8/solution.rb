input = File.read("input.txt").split("\n\n")

instructions = input.first

Node = Struct.new(:val, :left, :right, :rawL, :rawR)

raw_nodes = input.last.split("\n").each_with_object({}) do |node, hsh|
  n = node.gsub(" ", "").split(/\W/)
  hsh[n[0]] = Node.new(n[0], nil, nil, n[2], n[3])
end

raw_nodes.each do |k,v|
  v.left = raw_nodes[v.rawL]
  v.right = raw_nodes[v.rawR]
end

starting_node = "AAA"

# part 1
counter = 0
instructions.split("").cycle do |i|
  break if starting_node == "ZZZ"

  starting_node = raw_nodes[starting_node].left.val if i == "L"
  starting_node = raw_nodes[starting_node].right.val if i == "R"
  counter += 1
end

puts "part 1 = #{counter}"


# part 2
starting_nodes = raw_nodes.keys.filter {|k| k.end_with?("A") }
target = starting_node.size

routes = []

starting_nodes.each do |starting_node|

counter = 0
instructions.split("").cycle do |i|
  break if starting_node.end_with?("Z")

  starting_node = raw_nodes[starting_node].left.val if i == "L"
  starting_node = raw_nodes[starting_node].right.val if i == "R"

  counter+= 1
  end

routes << counter
counter = 0
end

puts "part 2 = #{routes.reduce(1) {|acc, n| acc.lcm(n) }}"
