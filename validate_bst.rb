###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc validate binary search tree
###--------------------------------------------------------------------------------------------------###

class Node
   attr_accessor :value
   attr_accessor :left
   attr_accessor :right

   def initialize(value) 
      self.value = value 
   end

   def print(indent=0)
      spaces = " " * indent
      puts "#{spaces}#{value}"
      self.left.print(indent+2) if self.left
      self.right.print(indent+2) if self.right
   end
   def validate?(min=-99999,max=99999)
      if self.value < min || self.value > max
         #puts "************* #{self.value} < #{min} || #{self.value} > #{max}"
         return false
      end
      if self.left && !self.left.validate?(min, self.value)
         return false
      end
      if self.right && !self.right.validate?(self.value, max)
         return false
      end
      return true
   end
end

root = Node.new(10)
root.left = Node.new(5)
root.right = Node.new(20)
root.right.left = Node.new(6)
root.right.right = Node.new(21)

puts "#{root.print} #{root.validate?}\n\n"


root = Node.new(10)
root.left = Node.new(5)
root.right = Node.new(20)
root.right.left = Node.new(15)
root.right.right = Node.new(21)

puts "#{root.print} #{root.validate?}\n\n"
