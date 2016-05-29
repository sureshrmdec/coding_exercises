###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Generate permutations of a string
###--------------------------------------------------------------------------------------------------###
 
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
#
def perms(str)
    return [""] if str == nil || str.length == 0
    first = str.slice(0, 1)
    words = perms(str.slice(1,str.length))
    perms = []
    for word in words
        (0..word.length).each do |j|
            perms << word.slice(0,j) + first + word.slice(j,word.length)
        end
    end
    perms
end

puts perms("abc").inspect
