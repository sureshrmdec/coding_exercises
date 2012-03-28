###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Given an array of integers, find the logest sequence (i..k) such that j < j+1 & A[j] <= A[j+1]
###--------------------------------------------------------------------------------------------------###

def longest_subseq(input)
   long_seq_len = Array.new(input.length, 1)
   prev_indexes = Array.new(input.length, -1)
   max_len = 1
   longest_seq_end = 0

   (1...input.length).each do |i|
      len = 1
      prev_ndx = -1
      (0...i).each do |j|
         if input[j] <= input[i] && long_seq_len[j]+1 > len
            len = long_seq_len[j]+1
            prev_ndx = j
         end
      end
      long_seq_len[i] = len
      prev_indexes[i] = prev_ndx
      if max_len < len
         max_len = len
         longest_seq_end = i
      end
   end
   output = []
   while longest_seq_end >= 0
      output << input[longest_seq_end]
      longest_seq_end = prev_indexes[longest_seq_end]
   end
   output.reverse
end

raise "Unexpected response" unless [0, 8, 9, 14, 29, 69] == longest_subseq([37, 93, 0, 23, 79, 65, 49, 81, 67, 8, 32, 29, 96, 76, 15, 9, 51, 14, 29, 69])
