class Node
  attr_accessor :value, :left, :right
  def initialize(value, left=nil, right=nil)
    self.value = value
    self.left = left
    self.right = right
  end

  def traverse1
    current_level = [self]
    while !current_level.empty?
      next_level = []
      for n in current_level
        print(n.value)
        next_level << n.left if n.left
        next_level << n.right if n.right 
      end
      print("\n")
      current_level = next_level
    end
  end 
  #
  def traverse2 
    level = 1
    last = 1
    todo = [[self, level]]
    while !todo.empty?
      n, level = todo.shift
      if last != level
        print("\n")
        last = level 
      end
      print(n.value)
      todo << [n.left,level+1] if n.left
      todo << [n.right,level+1] if n.right 
    end
    print("\n")
  end
end

tree = Node.new(1, Node.new(2, Node.new(4, Node.new(7))), Node.new(3, Node.new(5), Node.new(6)))
$stdout.sync = true
puts ("traverse 1")
tree.traverse1
puts ("\n\ntraverse 2")
tree.traverse2
