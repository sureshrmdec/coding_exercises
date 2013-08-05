###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc reverses string 
###--------------------------------------------------------------------------------------------------###

class String
   def reverse_words() 
       chars = self.chars.to_a
       buffer = []

       read_pos = self.length - 1
       write_pos = 0
       while read_pos >= 0
        if (chars[read_pos] == ' ')
            buffer.insert(write_pos, ' ')
            write_pos += 1
            read_pos -= 1
        else 
            word_end = read_pos 
            while (read_pos >= 0 && chars[read_pos] != ' ') 
                read_pos -= 1
            end 
            word_read_pos = read_pos + 1 
            while word_read_pos <= word_end 
                buffer.insert(write_pos, chars[word_read_pos])
                write_pos += 1
                word_read_pos += 1
            end
        end
       end
       buffer.join("")
   end
end

words = "Reversing words in Ruby"
puts words
puts words.reverse_words 
