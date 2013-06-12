class MergeTree  
   def self.randomTree(depth, lo, hi)
      return nil if depth <= 0
      data = lo + rand() % (hi - lo + 1)
      Node.new(data, randomTree(depth-1, lo, data), randomTree(depth-1, data, hi));
   end

   def self.validate(lhn, rhn, mna)
      error = false
      if !mna.empty?
         prev = mna.get()
         while !mna.empty?
            if prev > mna.get
               error = true;
               puts "[ERROR] prev: #{prev} , curr: #{mna.get}" 
               break
            end
            prev = mna.get()
            mna.pop()
         end 
         if error
            puts "lhn: #{lhn}"
            puts "rhn: #{rhn}"
         end 
      end 
   end

   def self.run(depth=5, useStream = false)
      lhn = randomTree(depth, 0, 1000)
      rhn = randomTree(depth, 0, 1000)
      if useStream
         lhs = NodeStream.new
         lhs.consume(lhn)
         rhs = NodeStream.new
         rhs.consume(rhn)
         mns = MergeNodeStream.new(lhs, rhs)
         validate(lhn, rhn, mns)
       else
         mnv = MergeNodeVector.new(lhn, rhn)
         validate(lhn, rhn, mnv)
       end
   end 
end

class Node
   attr_accessor :data, :left, :right
   def initialize(data, left=nil, right=nil) 
      @data = data
      @left = left
      @right = right
   end 
   def size
      sz = 1
      sz += @left.size if @left
      sz += @right.size if @right 
      sz 
   end 
   def to_s
      l = @left ? @left.to_s : "X"
      r = @right ? @right.to_s : "X"
      "Node(#{l}, #{@data}, #{r})"
   end
end

class NodeStream
   attr_accessor :node_stack
   def initialize
      @node_stack = []
   end 
   def empty?
      @node_stack.empty?
   end 
   def pop
      node = node_stack[-1] 
      if node
        @node_stack.pop 
        consume(node.right) 
      else 
        nil 
      end
   end 

   def get 
      node = @node_stack[-1] 
      node.nil? ? nil : node.data 
   end 

   def consume(root)
      if root 
         @node_stack.push root 
         consume(root.left) 
      end 
   end
end

   

class MergeNodeStream
   attr_accessor :first, :second 
   def initialize(first, second)
      @first = first 
      @second = second 
   end  

   def empty?
      first.empty? && second.empty?
   end  

   def pop 
      x = first.empty? ? nil : first.get 
      y = second.empty? ? nil : second.get 
      if x && y
         [first.pop, second.pop].min 
      elsif x
         first.pop
      else 
         second.pop
      end
   end 

   def get 
      x = first.empty? ? nil : first.get 
      y = second.empty? ? nil : second.get 
      if x && y
         [x, y].min
      elsif x
         x
      else 
         y
      end
   end 
end



class MergeNodeVector  
   attr_accessor :lhs, :rhv, :nrh, :rhp

   def initialize(lhn, rhn)
      @lhs = NodeStream.new 
      @lhs.consume(lhn)
      @rhp = 0
      @nrh = rhn.size
      @rhv = []
      stream = NodeStream.new
      stream.consume(rhn)
      (0..@nrh).each do |i|
         @rhv << stream.get()
         stream.pop()
      end
   end  

   def empty?
      @lhs.empty? && @rhp == @nrh
   end 

   def pop 
      if !@lhs.empty? && @rhp < @nrh
         if @rhv[@rhp] < @lhs.get()
            @rhp += 1
         else
            @lhs.pop()
         end 
      elsif !@lhs.empty?
         @lhs.pop()
      elsif @rhp < @nrh
         @rhp += 1
      end
   end  

   def get 
      if !@lhs.empty? && @rhp < @nrh
         if @rhv[@rhp] < @lhs.get() 
            return @rhv[@rhp]
         else
            return @lhs.get()  
         end
      elsif !@lhs.empty?
         return @lhs.get() 
      elsif @rhp < @nrh
         return @rhv[@rhp]
      else 
         nil 
      end 
   end
end



MergeTree.run  
