def rand_pick(lower, higher)
   (lower + rand * (higher-lower+1)).to_i
end

def picks(original, m)
   subset = Array.new(m)
   array = original.clone
   (0...m).each do |j|
      ndx = rand_pick(j, array.length-1)
      subset[j] = array[ndx]
      array[ndx] = array[j] #array[j] is dead
   end
   subset
end

puts picks((0..20).to_a, 10)

