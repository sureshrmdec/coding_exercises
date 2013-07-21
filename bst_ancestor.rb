###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc finds common ancestor for two node values in binary search tree
###--------------------------------------------------------------------------------------------------###

class Node
   attr_accessor :parent
   attr_accessor :value
   attr_accessor :left
   attr_accessor :right

   def initialize(value, parent) 
      self.value = value 
      self.parent = parent
   end

   def print()
      q = []
      q.insert(0, [self, 30]) 
      while !q.empty?
        (p, indent) = q[0]
        q.delete_at(0)
        spaces = " " * indent
        puts "#{spaces}#{p.value}/#{p.parent}" 
        q.insert(0, [p.left, indent-2]) if p.left
        q.insert(0, [p.right, indent+2]) if p.right
      end
      puts "---------------------- #{self.validate?}\n\n"
   end 
   def to_s  
       return self.value.to_s
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
   def common_ancestor(value1, value2)
      if self.value < value1 && self.value < value2 
         if self.left 
             return self.right.common_ancestor(value1, value2) 
         else 
             puts "no left for #{self}"
             return nil 
         end
      elsif self.value > value1 && self.value > value2 
         if self.right
             return self.left.common_ancestor(value1, value2) 
         else
             return nil 
         end
      end 
      return self #found
   end
end

root = Node.new(20, nil)
root.left = Node.new(8, root)
root.right = Node.new(22, root)
root.right.left = Node.new(21, root.right)
root.right.right = Node.new(25, root.right)


root.left.left = Node.new(4, root.left)
root.left.right = Node.new(12, root.left)
root.left.right.left = Node.new(10, root.left.right)
root.left.right.right = Node.new(14, root.left.right)
#puts root.print()
puts "common ancestor #{root.common_ancestor(4, 14)}"
