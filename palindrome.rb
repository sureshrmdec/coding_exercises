class String 
   def valid_palindrom?
     "abcdefghijklmnopqrstuvwxyz".index(self) != nil
   end
   def palindrom?
     leftNdx = 0
     rightNdx = self.length-1
     while leftNdx < rightNdx
       left = self[leftNdx].chr.downcase
       if !left.valid_palindrom?
          leftNdx += 1
          next
       end
       right = self[rightNdx].chr.downcase
       if !right.valid_palindrom?
          rightNdx -= 1
          next
       end
       return false if (left != right) 
       leftNdx += 1
       rightNdx -= 1
     end  
     return true
   end
end
  
puts "A     man, a plan, a canal, Panama!".palindrom?
