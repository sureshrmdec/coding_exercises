#!/usr/bin/env ruby
#
class Dictionary
  attr_accessor :words
  def initialize(file)
    self.words = File.foreach(file).map { |line| line.strip.chars }
  end  
  #
  def exists?(letters)
    self.words.each do |word|
      if word == letters 
        return true 
      end
    end 
    return false
  end 
  #
  def matching_words(letters, max_results=10)
    matching = []
    self.words.each do |word|
      if word == letters || subset?(word, letters)
        value = TileValues.value(word) 
        matching << [word, value] 
      end
    end 
    #matching.sort_by { |letters, value| -value }.slice(0,max_results)
    matching.sort_by { |letters, value| -value }
  end 
  def subset?(word, letters)
    copy_word = word.clone
    letters.each do |l| 
      ndx = copy_word.index(l)
      if ndx
        copy_word.delete_at(ndx)
      end
    end
    copy_word.count == 0
  end
end

class TileValues 
  @@values = {}
  def self.init_values
    @@values[' '] = 0
    @@values['E'] = 1
    @@values['A'] = 1
    @@values['I'] = 1
    @@values['O'] = 1
    @@values['N'] = 1
    @@values['R'] = 1
    @@values['T'] = 1
    @@values['L'] = 1
    @@values['S'] = 1
    @@values['U'] = 1
    @@values['D'] = 2
    @@values['G'] = 2
    @@values['B'] = 3
    @@values['C'] = 3
    @@values['M'] = 3
    @@values['P'] = 3
    @@values['F'] = 4
    @@values['H'] = 4
    @@values['V'] = 4
    @@values['W'] = 4
    @@values['Y'] = 4
    @@values['K'] = 5
    @@values['J'] = 8
    @@values['X'] = 8
    @@values['Q'] = 10
    @@values['Z'] = 10 
  end  
  def self.value(letters)
    init_values if @@values.count == 0
    letters.inject(0) { |sum, letter| sum + @@values[letter] }
  end 

end 

class Tiles
  attr_accessor :alphabets 
  def initialize
    #alphabets = ('A'..'Z').to_a
    self.alphabets = []
    2.times { self.alphabets << ' ' }
    12.times { self.alphabets << 'E' }
    9.times { self.alphabets << 'A' }
    9.times { self.alphabets << 'I' }
    8.times { self.alphabets << 'O' }
    6.times { self.alphabets << 'N' }
    6.times { self.alphabets << 'R' }
    6.times { self.alphabets << 'T' }
    4.times { self.alphabets << 'L' }
    4.times { self.alphabets << 'S' }
    4.times { self.alphabets << 'U' }
    4.times { self.alphabets << 'D' }
    3.times { self.alphabets << 'G' }
    2.times { self.alphabets << 'B' }
    2.times { self.alphabets << 'C' }
    2.times { self.alphabets << 'M' }
    2.times { self.alphabets << 'P' }
    2.times { self.alphabets << 'F' }
    2.times { self.alphabets << 'H' }
    2.times { self.alphabets << 'V' }
    2.times { self.alphabets << 'W' }
    2.times { self.alphabets << 'Y' }
    1.times { self.alphabets << 'K' }
    1.times { self.alphabets << 'J' }
    1.times { self.alphabets << 'X' }
    1.times { self.alphabets << 'Q' }
    1.times { self.alphabets << 'Z' }
  end  

  def has_more?
    !self.alphabets.nil? && self.alphabets.count > 0 
  end 

  def pick_random(count)
    count = [count, self.alphabets.count].min
    subset = Array.new(count)
    (0...count).each do |j| 
      ndx = rand_pick(j, self.alphabets.length-1)
      subset[j] = self.alphabets[ndx]
      self.alphabets[ndx] = self.alphabets[j] #alphabets[j] is dead
    end 
    self.alphabets = self.alphabets.slice(count, self.alphabets.count)
    subset
  end
  #
  def rand_pick(lower, higher)
    (lower + rand * (higher-lower+1)).to_i
  end
end 


class Scrabble
  attr_accessor :dictionary
  attr_accessor :tiles
  def initialize
    self.dictionary = Dictionary.new('dictionary.txt')
    self.tiles = Tiles.new
  end
  def show_words(letters) 
    letters ||= tiles.pick_random(7)
    word = letters.join("")
    puts "#{word} with value #{TileValues.value(letters)} exists in the dictionary" if dictionary.exists?(letters)
    puts "#{word} could be used to make up following words:"
    matching = dictionary.matching_words(letters)
    matching.each do |letters, value| 
      puts "\t#{letters.join('')} #{value}"
    end
  end
end

scrabble = Scrabble.new
scrabble.show_words(ARGV.length > 0 ? ARGV[0].upcase.chars : nil)
