def rand_pick(lower, higher)
   (lower + rand * (higher-lower+1)).to_i
end

def shuffle(cards)
   (0...cards.length).each do |j|
      ndx = rand_pick(j, cards.length-1)
      temp = cards[j]
      cards[j] = cards[ndx]
      cards[ndx] = temp #cards[j] is dead
   end
   cards 
end

puts shuffle((0..20).to_a)

