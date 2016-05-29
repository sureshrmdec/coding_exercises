# A group of farmers has some elevation data, and we’re going to help them understand how rainfall flows over their farmland. 
# We’ll represent the land as a two-dimensional area of altitudes and use the following model, based on the idea that water flows downhill: 
# 
# If a cell’s four neighboring cells all have higher altitudes, we call this cell a sink; water collects in sinks. 
# Otherwise, water will flow to the neighboring cell with the lowest altitude. If a cell is not a sink, you may assume it has a unique lowest neighbor and that this neighbor will be lower than the cell. 
# 
# Cells that drain into the same sink – directly or indirectly – are said to be part of the same basin. 
# 
# Your challenge is to partition the map into basins. In particular, given a map of elevations, your code should partition the map into basins and output the sizes of the basins, in descending order. 
# 
# Assume the elevation maps are square. Input will begin with a line with one integer, S, the height (and width) of the map. The next S lines will each contain a row of the map, each with S integers – the elevations of the S cells in the row. Some farmers have small land plots such as the examples below, while some have larger plots. However, in no case will a farmer have a plot of land larger than S = 5000. 
# 
# Your code should output a space-separated list of the basin sizes, in descending order. (Trailing spaces are ignored.) 
# 
# While correctness and performance are the most important parts of this problem, a human will be reading your solution, so please make an effort to submit clean, readable code. In particular, do not write code as if you were solving a problem for a competition. 
# 
# A few examples are below. 
# 
# Input: 
# 3 
# 1 5 2 
# 2 4 7 
# 3 6 9 
# 
# 
# Output: 
# 7 2 
# 
# 
# 
# 
# 
# The basins, labeled with A’s and B’s, are: 
# A A B 
# A A B 
# A A A 
# 
# 
# 
# 
# Input: 
# 1 
# 10 
# 
# Output: 
# 1 
# 
# 
# There is only one basin in this case. 
# 
# 
# 
# 
# Input: 
# 5 
# 1 0 2 5 8 
# 2 3 4 7 9 
# 3 5 7 8 9 
# 1 2 5 4 2 
# 3 3 5 2 1 
# 
# Output: 
# 11 7 7 
# 
# 
# 
# 
# 
# 
# The basins, labeled with A’s, B’s, and C’s, are: 
# A A A A A 
# A A A A A 
# B B A C C 
# B B B C C 
# B B C C C 
# 
# 
# 
# Input: 
# 4 
# 0 2 1 3 
# 2 1 0 4 
# 3 3 3 3 
# 5 5 2 1 
# 
# Output: 
# 7 5 4 
# 
# 
# 
# 
# 
# The basins, labeled with A’s, B’s, and C’s, are: 
# A A B B 
# A B B B 
# A B B C 
# A C C C
#
# e.g. ruby basin.rb < basin1.dat
class Point 
  attr_accessor :x
  attr_accessor :y 
  attr_accessor :label
  attr_accessor :count
  @@lookup_coords = {}
  def initialize(x, y, label = '', count = 0) 
    self.x = x
    self.y = y
    self.label = label
    self.count = count 
  end  

  def self.create(x, y) 
    return lookup(Point.new(x, y))
  end 

  def self.lookup(coord) 
    old = @@lookup_coords[coord]
    if old.nil?
      @@lookup_coords[coord] = coord  
      return coord 
    else 
      return old 
    end 
  end
  def left 
    return Point.create(self.x, self.y - 1)
  end
  def right
    return Point.create(self.x, self.y + 1)
  end
  def top
    return Point.create(self.x - 1, self.y)
  end
  def bottom
    return Point.create(self.x + 1, self.y)
  end 

  def hash
    self.x ^ self.y
  end 

  def ==(other)
    self.x == other.x && self.y == other.y
  end 

  def to_s 
    return "#{x},#{y} = #{label}:#{count}\t"
  end
  alias eql? ==
end

class Basin 
  attr_accessor :size
  attr_accessor :area
  attr_accessor :basins
  def initialize(file)
    self.size = file.gets().strip().to_i # size of land  
    self.area = []
    self.basins = []
    (0...size).each do
      area << file.gets().strip().split(/\s+/).map {|x|x.to_i}
    end 
  end 

  def calculate_basins
    next_label = ''
    (0...size).each do |x|
      self.basins[x] = []
      (0...size).each do |y|
        coord = basin_coords(Point.create(x, y))  
        self.basins[x][y] = coord
        if coord.count == 0
          next_label = next_label.length == 0 ? 'A' : next_label.next
          coord.label = next_label
        end
        coord.count += 1
        #puts "\t Found min #{coord} for #{x}, #{y}"
      end 
    end 
  end 


  def coord_value(coord)
    area[coord.x][coord.y] 
  end 

  def basin_coords(coord)
    value = coord_value(coord)
    min = coord 
    # 
    if in_bounds_and_smaller?(coord.left, value)
      min = coord.left 
      value = coord_value(coord.left)  
    end
    if in_bounds_and_smaller?(coord.right, value)
      min = coord.right 
      value = coord_value(coord.right) 
    end
    if in_bounds_and_smaller?(coord.top, value)
      min = coord.top 
      value = coord_value(coord.top) 
    end
    if in_bounds_and_smaller?(coord.bottom, value)
      min = coord.bottom 
      value = coord_value(coord.bottom) 
    end
    return min == coord ? min : basin_coords(min)
  end
  
  def in_bounds?(coord)
    (coord.x >= 0 && coord.x < size) && (coord.y >= 0 && coord.y < size)
  end 

  def in_bounds_and_smaller?(coord, value) 
    in_bounds?(coord) && area[coord.x][coord.y] < value
  end

  def to_s
    str = "#{size}\n"
    (0...size).each do |x|
      (0...size).each do |y|
        str << "#{self.area[x][y]} "
      end 
      str << "\n"
    end 
    str << "\n"
    (0...size).each do |x|
      (0...size).each do |y|
        str << "#{self.basins[x][y]} "
      end 
      str << "\n"
    end 
    str
  end
end 
b = Basin.new(STDIN)
b.calculate_basins
puts b
