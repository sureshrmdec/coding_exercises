# Permutations = N!
# N Pick K = nPk (5 people sit on 3 chairs) = N! / (N-K)!  
# e.g. 3 letters in alphabets (26) = 26 ** 3
# e.g. 3 letters in alphabets if we need different letters = 26! / (26-3)!
# color code game - blue/yello/white/red/orange/green, pick 4 colors if colors
# cannot be repeated: 6!/(6-4)!
# combinations: How many ways to choose combinations (divide by # of ways you
# can arrange k things)
# n Choose k  = nCk = n!/((n-k)! * k!) -- binomial coefficient -- order doesn't matter
# e.g. handshakes with 4 people = 4C2 = ( 4 2 ) = 4! / ((4-2)! * 2!) = 6
# card game using 36 cards, 4 suits, hand is collection of 9, how many
# hands possible = ( 36 9) = 36! / ((36 - 9)! * 9!) = 
# How many unique ways are there to arrange the letters in the word SONG?
# 4! 
# How many unique ways are there to arrange the letters in the word HATTER?
# Ans: since T is duplicate, we divide the number of permutations we got by
# the number of times we're overcounting each permutation, we get 6! / 2! = 360
# You need to put your reindeer, Rudy, Ezekiel, Gloopin, Bloopin, and Prancer,
# in a single-file line to pull your sleigh. However, Bloopin and Rudy are
# fighting, so you have to keep them apart, or they won't fly.
# Ans:
# Permutations for 5 = 5! = 120
# Let's treat Bloopin and Rudy as one so 4! = 24
# Let's add them twice for both reindeers = 24 + 24 + 48
# Subtract 48 from 120 = 120 = 48 = 72 ways 
# How many numbers between 1 and 100 (inclusive) are divisible by 4 or 5 
# Ans: There are 25 numbers divisible by 4 between 1 and 100 , and
# 20 numbers divisible by 5 between 1 and 100, but we are over counting so we
# need to subtract (5 * 4) or 20 = 25 + 20 - 5
def fact(n)
  if n <= 1
    1
  else
    n * fact(n-1)
  end
end

def permutations(n, k)
  return fact(N) / fact(N-K)
end 

def combinations(n, k)
  return fact(N) / (fact(N-K) * fact(K))
end 

def permutations_with_repeating(n, k)
  return n ** k
end 

def uniq_words(n, k)
  return fact(n) / fact(k)
end 

def div_nums(max, div1, div2, min = 1)
  sum1 = max / div1
  sum2 = max / div2
  sum3 = max / (div1 * div2)
  return sum1 + sum2 - sum3 
end

def perm_with_exception(n)
  return fact(n) - (fact(n-1)*2)
end 


N = ARGV[0].to_i
K = ARGV[1].to_i
L = (ARGV[2] || ARGV[1]).to_i
#puts("factorial #{N} = #{fact(N)}\n\n")
#puts("non-unique permutations #{permutations_with_repeating(N, K)}\n\n")
puts("permutations #{permutations(N, K)}\n\n")
puts("combinations #{combinations(N, K)}\n\n")
puts("div_nums #{div_nums(N, K, L)}\n\n")
puts("uniq_words #{uniq_words(N, K)}\n\n")
puts("perm_with_exception #{perm_with_exception(N)}\n\n")

