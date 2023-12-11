class GridCreator
  def self.create
    part_one = File.readlines("test_input.txt", chomp: true).map do |line|
      line.split("")
    end

    part_one_cpy = []

    part_one.each do |line|
      part_one_cpy << line if line.all? {|space| space == "." }
      part_one_cpy << line
    end


    part_one_cpy_cpy = []
    part_one.size.times do |i|
      str = ""

      part_one_cpy.each do |line|
        str += line[i]
      end

      part_one_cpy_cpy << str if str == "."*part_one_cpy.size
      part_one_cpy_cpy << str
    end

    grid = []

    part_one_cpy.size.times do |i|
      str = ""

      part_one_cpy_cpy.each do |line|
        str += line[i]
      end
      grid << str
    end

    node = 1
    grid.map! do |line|

      chungus = []

      line.split("").each do |c|
        if c == "#"
          chungus << node.to_s
          node+=1
        else
          chungus << "."
        end
      end

      chungus
    end
    [grid, node]
  end
end

grid, max_nodes = GridCreator.create

pairs = []

(1..max_nodes).each do |num|
  (num...(max_nodes)-1).each do |n|
    pairs << [num, n+1]
  end
end

def walk(i,j,grid,en,visited,logger,steps=0)

  min = logger.min || 10000000000000000000
  return -1 if i < 0 || i >= grid.size || j < 0 || j >= grid.first.size
  return -1 if visited[i][j] == true
  return -1 if steps >= min 

  if grid[i][j] == en
    logger << steps
    return
  end

  visited[i][j] = true

  walk(i+1, j,grid,en,visited,logger,steps+1)
  walk(i, j-1,grid,en,visited,logger,steps+1)
  walk(i, j+1,grid,en,visited,logger,steps+1)
end

ans = []

pairs.each do |pair|
  # find i,j of pair.first
  start = pair.first
  target = pair.last

  i_i = 0
  i_j = 0

  grid.each_with_index do |line, i|
    line.each_with_index do |g, j|
      if g.to_i == start
        i_i = i
        i_j = j
      end
    end
  end

  logger = []

  walk(i_i,i_j,grid,target.to_s, Array.new(grid.size) { Array.new(grid.first.size, false) },logger)
  

  ans << logger.min
  p "#{start} #{target}"
end

p ans.sum
