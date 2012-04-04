###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Find longest repeating substring in a string, e.g. for ababab, abab is
###  longest substring.
###--------------------------------------------------------------------------------------------------###

def longest_substr(str)
    lengths = Hash.new {|h,k|h[k] = 0}
    max = 0
    max_substr = ''
    (0...str.length-1).each do |i|
        (i..str.length).each do |j|
            s = str.slice(i, j)
            if s.length > 2
                lengths[s] += 1
                if lengths[s] > max
                    max = lengths[s]
                    max_substr = s
                end
            end
        end
    end
    puts lengths.inspect
    max_substr
end

puts longest_substr("ababab")
