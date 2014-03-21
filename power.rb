#
# The power function based on Donald Knuth. 
# First it sets an auxiliary variable y to identity (e.g. 1 for multiplication)
# Then the function scans the binary representation of n on each iteration of the loop. If it finds a 1 then it multiplies by x. 
# For power, on every step of the loop it squares $x.
# Finding a 1 means that the current value of n is not divisible by 2, or in other words, n % 2 == 1.
# Also on every iteration of the loop n gets halved and then we apply floor to the result. When n equals 0, we terminate the loop and return the value of y.
# http://videlalvaro.github.io/2014/03/the-power-algorithm.html
#
class Power 
   def self.repeat(x, n) 
      # doesn't work
      self.generic_pow(x, n, "") {|y, x| y . x }
   end
   def self.times(x, n)
      self.generic_pow(x, n, 0) {|y, x| y + x }
   end
   def self.pow(x, n)
      self.generic_pow(x, n, 1) {|y, x| y * x }
   end
   def self.generic_pow(x, n, id, &block)
      y = id
      while (true)
         t = n % 2
         n = (n/2).floor
         y = block.call(y, x) if (t == 1) 
         break if (n == 0) 
         x = block.call(x, x)
      end
      y
   end
end

puts "Times " + Power.times(ARGV[0].to_i, ARGV[1].to_i).to_s
puts "Power " + Power.pow(ARGV[0].to_i, ARGV[1].to_i).to_s
#puts "Repeat " + Power.repeat("a", ARGV[0].to_i).to_s
